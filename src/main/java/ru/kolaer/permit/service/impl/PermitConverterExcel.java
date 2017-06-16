package ru.kolaer.permit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.TypeEvent;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dao.WorkEventDao;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.PermitConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 06.06.2017.
 */
@Service
@Slf4j
public class PermitConverterExcel implements PermitConverter<File> {

    private final String pathTemplatePermit;
    private final PermitPageDao permitPageDao;
    private final WorkEventDao workEventDao;

    public PermitConverterExcel(@Value("${permit.template.path}") String pathTemplatePermit,
                                PermitPageDao permitPageDao,
                                WorkEventDao workEventDao) {
        this.pathTemplatePermit = pathTemplatePermit;
        this.permitPageDao = permitPageDao;
        this.workEventDao = workEventDao;
    }

    @Override
    public File convertPermit(Integer idPermit) {
        final WorkPermitEntity workPermit = this.permitPageDao.findWorkById(idPermit);
        final PeoplePermitEntity peoplePermit = this.permitPageDao.findPeopleById(idPermit);
        final List<WorkEvent> workEvents = this.workEventDao.findByIdPermit(idPermit, false);

        final File fileTemplate = new File(System.getProperty("permit.home") + "/" + pathTemplatePermit);

        log.debug(fileTemplate.getAbsolutePath());

        if(!fileTemplate.exists()) {
            log.error("File template is not exist!");
            return fileTemplate;
        }

        final File tempDir = new File(System.getProperty("permit.home") + "/" + "temp");
        if(!tempDir.exists() && !tempDir.mkdir())
            return fileTemplate;

        final String copyTemplateFilePath = tempDir.getAbsoluteFile() + "/" + UUID.randomUUID().toString();
        final File copyTemplateFile = new File(copyTemplateFilePath + ".xlsx");
        try {
            Files.copy(fileTemplate.toPath(), copyTemplateFile.toPath());
        } catch (IOException e) {
            log.error("File can't copy!", e);
            return fileTemplate;
        }


        try {
            if(!copyTemplateFile.exists())
                return fileTemplate;

            final XSSFWorkbook myExcelBook = new XSSFWorkbook(copyTemplateFile);
            final XSSFSheet permitSheet = myExcelBook.getSheet("Бланк");

            final SimpleDateFormat ruDateFormatter = new SimpleDateFormat("«dd» MMMM yyyy г.", new Locale("ru"));
            final SimpleDateFormat ruDateWorkFormatter = new SimpleDateFormat("hh час. mm мин. «dd» MMMM yyyy г.", new Locale("ru"));

            final String dateWrite = ruDateFormatter.format(workPermit.getDateWritePermit());
            final String dateLimit = ruDateFormatter.format(workPermit.getEndWork());// : "«___» _______ 20 _г.";
            final String startWork = ruDateWorkFormatter.format(workPermit.getStartWork());
            final String endWork = ruDateWorkFormatter.format(workPermit.getEndWork());
            final String extendedWork = ruDateWorkFormatter.format(workPermit.getExtendedPermit());

            AtomicInteger rowNumberForExecutors = new AtomicInteger(0);
            AtomicInteger rowNumberForBeginEvent = new AtomicInteger(0);
            AtomicInteger rowNumberForProcEvent = new AtomicInteger(0);
            AtomicInteger rowNumberForSpecialEvent = new AtomicInteger(0);

            permitSheet.rowIterator().forEachRemaining(row -> {
                row.forEach(col -> {
                    String colValue = col.getStringCellValue();

                    if(colValue.contains("#serial_number#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#serial_number#", workPermit.getSerialNumber()));
                    }

                    if(colValue.contains("#date_write#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#date_write#", dateWrite));
                    }

                    if(colValue.contains("#date_limit#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#date_limit#", dateLimit));
                    }

                    if(colValue.contains("#initials_and_post_supervisor#")) {
                        String initialsAndPost = Optional.ofNullable(peoplePermit.getResponsibleSupervisor()).map(emp ->
                                emp.getInitials() + ", "
                                        + emp.getPost().getName() + " "
                                        + emp.getPost().getRang() + " "
                                        + emp.getPost().getTypeRang()
                        ).orElse("");

                        col.setCellValue(colValue = colValue.replaceAll("#initials_and_post_supervisor#", initialsAndPost));
                    }

                    if(colValue.contains("#initials_and_post_executor#")) {
                        String initialsAndPost = Optional.ofNullable(peoplePermit.getResponsibleExecutor()).map(emp ->
                                emp.getInitials() + ", "
                                        + emp.getPost().getName()
                                        + " " + emp.getPost().getRang()
                                        + " " + emp.getPost().getTypeRang()
                        ).orElse("");

                        col.setCellValue(colValue = colValue.replaceAll("#initials_and_post_executor#", initialsAndPost));
                    }

                    if(colValue.contains("#permit_name#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#permit_name#", workPermit.getName()));
                    }

                    if(colValue.contains("#permit_place#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#permit_place#", workPermit.getPlaceWork()));
                    }

                    if(colValue.contains("#executors#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#executors#", ""));
                        rowNumberForExecutors.set(row.getRowNum() + 2);
                    }

                    if(colValue.contains("#content_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#content_work#", workPermit.getContentWork()));
                    }

                    if(colValue.contains("#condition_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#condition_work#", workPermit.getConditionWork()));
                    }

                    if(colValue.contains("#start_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#start_work#", startWork));
                    }

                    if(colValue.contains("#end_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#end_work#", endWork));
                    }

                    if(colValue.contains("#retaining_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#retaining_system#", workPermit.getRetaining()));
                    }

                    if(colValue.contains("#position_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#position_system#", workPermit.getPosition()));
                    }

                    if(colValue.contains("#safety_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#safety_system#", workPermit.getSafety()));
                    }

                    if(colValue.contains("#rescue_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#rescue_system#", workPermit.getRescue()));
                    }

                    if(colValue.contains("#materials#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#materials#", workPermit.getMaterials()));
                    }

                    if(colValue.contains("#instruments#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#instruments#", workPermit.getInstruments()));
                    }

                    if(colValue.contains("#adaptations#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#adaptations#", workPermit.getAdaptations()));
                    }

                    if(colValue.contains("#begin_event#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#begin_event#", ""));
                        rowNumberForBeginEvent.set(row.getRowNum() + 2);
                    }

                    if(colValue.contains("#proc_event#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#proc_event#", ""));
                        rowNumberForProcEvent.set(row.getRowNum() + 2);
                    }

                    if(colValue.contains("#special_event#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#special_event#", ""));
                        rowNumberForSpecialEvent.set(row.getRowNum() + 2);
                    }

                    if(colValue.contains("#permit_writer#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_writer#", ""));
                    }

                    if(colValue.contains("#permit_accept#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_accept#", ""));
                    }
                    if(colValue.contains("#permit_briefing_held#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_briefing_held#", ""));
                    }
                    if(colValue.contains("#permit_briefing_accept#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_briefing_accept#", ""));
                    }

                    if(colValue.contains("#permit_extended#")) {
                        if(workPermit.getEndWork().before(workPermit.getExtendedPermit())) {
                            col.setCellValue(colValue = colValue.replaceAll("#permit_extended#", extendedWork));
                        } else {
                            col.setCellValue(colValue = colValue.replaceAll("#permit_extended#", ""));
                        }
                    }
                });
            });

            int rowNumberExecutors = rowNumberForExecutors.get();

            if(rowNumberExecutors > 0 && Optional.ofNullable(peoplePermit.getExecutors())
                    .orElse(Collections.emptyList()).size() > 0) {
                for(int i = 0; i < peoplePermit.getExecutors().size(); i++) {
                    final EmployeeEntity employee = peoplePermit.getExecutors().get(i);

                    permitSheet.getRow(rowNumberExecutors)
                            .getCell(0)
                            .setCellValue(employee.getInitials());

                    if(i + 1 != peoplePermit.getExecutors().size()) {
                        copyRow(myExcelBook, permitSheet, rowNumberExecutors, rowNumberExecutors++);
                        rowNumberForBeginEvent.incrementAndGet();
                    }
                }
            }

            final List<WorkEvent> beginEvents = Optional.ofNullable(workEvents)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(event -> !event.getRemoved())
                    .filter(event -> event.getTypeEvent() == TypeEvent.BEGIN)
                    .collect(Collectors.toList());

            int rowAdd = this.insertEvents(rowNumberForBeginEvent.get(), beginEvents, permitSheet);

            final List<WorkEvent> procEvents = Optional.ofNullable(workEvents)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(event -> !event.getRemoved())
                    .filter(event -> event.getTypeEvent() == TypeEvent.PROCESS)
                    .collect(Collectors.toList());

            rowAdd = this.insertEvents(rowNumberForProcEvent.get() + rowAdd, procEvents, permitSheet);

            final List<WorkEvent> specialEvents = Optional.ofNullable(workEvents)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(event -> !event.getRemoved())
                    .filter(event -> event.getTypeEvent() == TypeEvent.SPECIAL)
                    .collect(Collectors.toList());

            rowAdd = this.insertEvents(rowNumberForSpecialEvent.get() + rowAdd, specialEvents, permitSheet);

            final File saveTemplate = new File(copyTemplateFilePath);

            myExcelBook.write(new FileOutputStream(saveTemplate));
            myExcelBook.close();

            try {
                saveTemplate.deleteOnExit();
            } catch (SecurityException ex) {
                log.error("Can't delete file: {}", saveTemplate.getAbsoluteFile(), ex);
            }

            return copyTemplateFile;
        } catch (IOException | InvalidFormatException e) {
            log.error("File not read!", e);
            return null;
        }
    }

    private int insertEvents(int rowNumber, List<WorkEvent> events, XSSFSheet sheet) {

        final int oldRowNumber = rowNumber;
        if(rowNumber > 0 && events.size() > 0) {
            for(int i = 0; i < events.size(); i++ ) {
                final WorkEvent event = events.get(i);

                if(i + 1 < events.size()) {
                    log.info("ROW COPY: {}, {}", rowNumber, rowNumber +1);
                    copyRow(sheet.getWorkbook(), sheet, rowNumber, rowNumber);
                }

                log.info("ROW GET: {}", rowNumber);
                final XSSFRow row = sheet.getRow(rowNumber);
                if(row == null)
                    continue;
                row.getCell(0).setCellValue(rowNumber);

                rowNumber += 1;
                //TODO: BUG!
                /*row.getCell(0).setCellValue(i + 1);
                row.getCell(1).setCellValue(event.getName());
                row.getCell(5).setCellValue(new SimpleDateFormat("dd.MM.yyyy hh:mm").format(event.getLimitDate()));
                row.getCell(7).setCellValue(Optional.ofNullable(event.getEmployeesEntity())
                        .orElse(Collections.emptyList()).stream()
                        .map(emp -> emp.getInitials() + "\n" + emp.getPost().getName()
                                + " " + emp.getPost().getRang() + " " + emp.getPost().getTypeRang())
                        .collect(Collectors.joining(", ")));*/
            }
        }

        return rowNumber - oldRowNumber - 1;
    }

    private static void copyRow(XSSFWorkbook workbook, XSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
        // Get the source / new row
        XSSFRow newRow = worksheet.getRow(destinationRowNum);
        XSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow != null) {
            worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);
        } else {
            newRow = worksheet.createRow(destinationRowNum);
        }

        // Loop through source columns to add to new row
        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
            // Grab a copy of the old/new cell
            XSSFCell oldCell = sourceRow.getCell(i);
            XSSFCell newCell = newRow.createCell(i);

            // If the old cell is null jump to next cell
            if (oldCell == null) {
                newCell = null;
                continue;
            }

            // Copy style from old cell and apply to new cell
            XSSFCellStyle newCellStyle = workbook.createCellStyle();
            newCellStyle.cloneStyleFrom(oldCell.getCellStyle());
            ;
            newCell.setCellStyle(newCellStyle);

            // If there is a cell comment, copy
            if (oldCell.getCellComment() != null) {
                newCell.setCellComment(oldCell.getCellComment());
            }

            // If there is a cell hyperlink, copy
            if (oldCell.getHyperlink() != null) {
                newCell.setHyperlink(oldCell.getHyperlink());
            }

            // Set the cell data type
            newCell.setCellType(oldCell.getCellType());

            // Set the cell data value
            switch (oldCell.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
            }
        }

        // If there are are any merged regions in the source row, copy to new row
        for (int i = 0; i < worksheet.getNumMergedRegions(); i++) {
            CellRangeAddress cellRangeAddress = worksheet.getMergedRegion(i);
            if (cellRangeAddress.getFirstRow() == sourceRow.getRowNum()) {
                CellRangeAddress newCellRangeAddress = new CellRangeAddress(newRow.getRowNum(),
                        (newRow.getRowNum() +
                                (cellRangeAddress.getLastRow() - cellRangeAddress.getFirstRow()
                                )),
                        cellRangeAddress.getFirstColumn(),
                        cellRangeAddress.getLastColumn());
                worksheet.addMergedRegion(newCellRangeAddress);
            }
        }
    }
}
