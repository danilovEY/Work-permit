package ru.kolaer.permit.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.kolaer.permit.entity.enums.TypeEvent;
import ru.kolaer.permit.dao.PermitPageDao;
import ru.kolaer.permit.dao.WorkEventDao;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.PermitConverter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.apache.poi.ss.usermodel.CellType.BLANK;

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
    public File convertPermit(Long idPermit) {
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
            if (!copyTemplateFile.exists())
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

                    if (colValue.contains("#serial_number#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#serial_number#",
                                getNotNullString(workPermit.getSerialNumber())));
                    }

                    if (colValue.contains("#date_write#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#date_write#", dateWrite));
                    }

                    if (colValue.contains("#date_limit#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#date_limit#", dateLimit));
                    }

                    if (colValue.contains("#initials_and_post_supervisor#")) {
                        String initialsAndPost = employeeToString(peoplePermit.getResponsibleSupervisor());

                        col.setCellValue(colValue = colValue.replaceAll("#initials_and_post_supervisor#", initialsAndPost));
                    }

                    if (colValue.contains("#initials_and_post_executor#")) {
                        String initialsAndPost = employeeToString(peoplePermit.getResponsibleExecutor());

                        col.setCellValue(colValue = colValue.replaceAll("#initials_and_post_executor#", initialsAndPost));
                    }

                    if (colValue.contains("#permit_name#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#permit_name#",
                                getNotNullString(workPermit.getName())));
                    }

                    if (colValue.contains("#permit_place#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#permit_place#",
                                getNotNullString(workPermit.getPlaceWork())));
                    }

                    if (colValue.contains("#executors#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#executors#", " "));
                        rowNumberForExecutors.set(row.getRowNum() + 2);
                    }

                    if (colValue.contains("#content_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#content_work#",
                                getNotNullString(workPermit.getContentWork())));
                    }

                    if (colValue.contains("#condition_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#condition_work#",
                                getNotNullString(workPermit.getConditionWork())));
                    }

                    if (colValue.contains("#start_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#start_work#", startWork));
                    }

                    if (colValue.contains("#end_work#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#end_work#", endWork));
                    }

                    if (colValue.contains("#retaining_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#retaining_system#",
                                getNotNullString(workPermit.getRetaining())));
                    }

                    if (colValue.contains("#position_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#position_system#",
                                getNotNullString(workPermit.getPosition())));
                    }

                    if (colValue.contains("#safety_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#safety_system#",
                                getNotNullString(workPermit.getSafety())));
                    }

                    if (colValue.contains("#rescue_system#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#rescue_system#",
                                getNotNullString(workPermit.getRescue())));
                    }

                    if (colValue.contains("#materials#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#materials#",
                                getNotNullString(workPermit.getMaterials())));
                    }

                    if (colValue.contains("#instruments#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#instruments#",
                                getNotNullString(workPermit.getInstruments())));
                    }

                    if (colValue.contains("#adaptations#")) {
                        col.setCellValue(colValue = colValue.replaceAll("#adaptations#",
                                getNotNullString(workPermit.getAdaptations())));
                    }

                    if (colValue.contains("#begin_event#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#begin_event#", " "));
                        rowNumberForBeginEvent.set(row.getRowNum() + 2);
                    }

                    if (colValue.contains("#proc_event#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#proc_event#", " "));
                        rowNumberForProcEvent.set(row.getRowNum() + 2);
                    }

                    if (colValue.contains("#special_event#")) {
                        col.setCellValue(colValue = colValue.replaceFirst("#special_event#", " "));
                        rowNumberForSpecialEvent.set(row.getRowNum() + 2);
                    }

                    if (colValue.contains("#permit_writer#")) {
                        String initialsAndPost = employeeToString(peoplePermit.getWriter()) + ", "
                                        + dateWrite;
                        col.setCellValue(colValue = colValue.replaceAll("#permit_writer#", initialsAndPost));
                    }

                    if (colValue.contains("#permit_accept#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_accept#", " "));
                    }
                    if (colValue.contains("#permit_briefing_held#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_briefing_held#", " "));
                    }
                    if (colValue.contains("#permit_briefing_accept#")) {
                        //TODO: add
                        col.setCellValue(colValue = colValue.replaceAll("#permit_briefing_accept#", " "));
                    }

                    if (colValue.contains("#permit_extended#")) {
                        if (workPermit.getEndWork().before(workPermit.getExtendedPermit())) {
                            col.setCellValue(colValue = colValue.replaceAll("#permit_extended#", extendedWork));
                        } else {
                            col.setCellValue(colValue = colValue.replaceAll("#permit_extended#", " "));
                        }
                    }

                    if(col.getStringCellValue().isEmpty()) {
                        col.setCellType(BLANK);
                    }
                });
            });

            int rowNumberExecutors = rowNumberForExecutors.get();

            if (rowNumberExecutors > 0 && Optional.ofNullable(peoplePermit.getExecutors())
                    .orElse(Collections.emptyList()).size() > 0) {
                for (int i = 0; i < peoplePermit.getExecutors().size(); i++) {
                    final EmployeeEntity employee = peoplePermit.getExecutors().get(i);


                    if (i + 1 < peoplePermit.getExecutors().size()) {
                        copyRow(myExcelBook, permitSheet, rowNumberExecutors, rowNumberExecutors + 1);

                        rowNumberForBeginEvent.incrementAndGet();
                        rowNumberForProcEvent.incrementAndGet();
                        rowNumberForSpecialEvent.incrementAndGet();
                    }

                    XSSFRow row = permitSheet.getRow(rowNumberExecutors);
                    if (row == null) {
                        continue;
                    }

                    row.cellIterator().forEachRemaining(cell -> {
                        String colValue = Optional.ofNullable(cell.getStringCellValue())
                                .orElse("");

                        if (cell.getStringCellValue().contains("#value1#")) {
                            colValue = colValue.replaceAll("#value1#", employee.getInitials());
                        }

                        if(colValue == null || colValue.isEmpty()){
                            cell.setCellType(BLANK);
                        } else {
                            cell.setCellValue(colValue);
                        }
                    });

                    rowNumberExecutors += 1;

                }
            } else {
                XSSFRow row = permitSheet.getRow(rowNumberExecutors);
                if (row != null) {
                    row.cellIterator().forEachRemaining(cell -> {
                        String colValue = Optional.ofNullable(cell.getStringCellValue())
                                .orElse("");

                        if (colValue.contains("#value1#")) {
                            colValue = colValue.replaceAll("#value1#", " ");
                        }

                        if(colValue == null || colValue.isEmpty()){
                            cell.setCellType(BLANK);
                        } else {
                            cell.setCellValue(colValue);
                        }
                    });
                }
            }

            log.debug("WORK_EVENT: BEGIN");
            final List<WorkEvent> beginEvents = Optional.ofNullable(workEvents)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(event -> !event.getRemoved())
                    .filter(event -> event.getTypeEvent() == TypeEvent.BEGIN)
                    .collect(Collectors.toList());
            int rowAdd = this.insertEvents(rowNumberForBeginEvent.get(), beginEvents, permitSheet);

            log.debug("WORK_EVENT: POC");
            final List<WorkEvent> procEvents = Optional.ofNullable(workEvents)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(event -> !event.getRemoved())
                    .filter(event -> event.getTypeEvent() == TypeEvent.PROCESS)
                    .collect(Collectors.toList());
            System.out.println("POC: " + (rowNumberForProcEvent.get() + rowAdd));
            rowAdd = this.insertEvents(rowNumberForProcEvent.get() + rowAdd, procEvents, permitSheet);

            log.debug("WORK_EVENT: SPEC");
            final List<WorkEvent> specialEvents = Optional.ofNullable(workEvents)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(event -> !event.getRemoved())
                    .filter(event -> event.getTypeEvent() == TypeEvent.SPECIAL)
                    .collect(Collectors.toList());
            System.out.println("SPEC: " + (rowNumberForSpecialEvent.get() + rowAdd));
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



                final XSSFRow row = sheet.getRow(rowNumber);
                if(row == null)
                    continue;

                log.debug("GET ROW: {}", rowNumber);

                if(i + 1 < events.size()) {
                    log.debug("COPY ROW: {}, {}", rowNumber, rowNumber + 1);
                    copyRow(sheet.getWorkbook(), sheet, rowNumber, rowNumber + 1);

                    rowNumber += 1;
                }

                final int index = i + 1;

                row.cellIterator().forEachRemaining(cell -> {
                    String colValue = Optional.ofNullable(cell.getStringCellValue())
                            .orElse("");

                    if (colValue.contains("#value1#")) {
                        colValue = colValue.replaceAll("#value1#", String.valueOf(index));
                    }

                    if(colValue.contains("#value2#")) {
                        colValue = colValue.replaceAll("#value2#", event.getName());
                    }

                    if(colValue.contains("#value3#")) {
                        colValue = colValue.replaceAll("#value3#",
                                new SimpleDateFormat("dd.MM.yyyy hh:mm")
                                        .format(event.getLimitDate()));
                    }

                    if(colValue.contains("#value4#")) {
                        colValue = colValue.replaceAll("#value4#", Optional
                                .ofNullable(event.getEmployeesEntity())
                                .orElse(Collections.emptyList()).stream()
                                .map(this::employeeToString)
                                .collect(Collectors.joining(";\n")));
                    }

                    if(colValue.isEmpty()){
                        cell.setCellType(BLANK);
                    } else {
                        cell.setCellValue(colValue);
                    }
                });
            }
        } else {
            Optional.ofNullable(sheet.getRow(rowNumber))
                    .map(XSSFRow::cellIterator)
                    .ifPresent(cellIterator ->
                            cellIterator.forEachRemaining(cell -> cell.setCellType(BLANK)));
            //rowNumber -= 1;
        }

        return rowNumber - oldRowNumber;

    }

    private String getNotNullString(String str) {
        return Optional.ofNullable(str)
                .filter(s ->  !s.isEmpty())
                .orElse(" ");
    }

    private String employeeToString(EmployeeEntity employeeEntity) {
        return Optional.ofNullable(employeeEntity).map(emp ->
                emp.getInitials() + ", "
                        + emp.getPost().getName() + " "
                        + Optional.ofNullable(emp.getPost().getRang())
                        .map(rang -> rang + " " + emp.getPost().getTypeRang())
                        .orElse("")
        ).orElse(" ");
    }

    private static void copyRow(XSSFWorkbook workbook, XSSFSheet worksheet, int sourceRowNum, int destinationRowNum) {
        // Get the source / new row
        XSSFRow newRow = worksheet.getRow(destinationRowNum);
        XSSFRow sourceRow = worksheet.getRow(sourceRowNum);

        if(sourceRow == null) {
            log.error("ROW NULL: {}", sourceRowNum);
            return;
        }

        worksheet.shiftRows(destinationRowNum, worksheet.getLastRowNum(), 1);

        // If the row exist in destination, push down all rows by 1 else create a new row
        if (newRow == null) {
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
            newCell.setCellType(oldCell.getCellTypeEnum());

            // Set the cell data value
            switch (oldCell.getCellTypeEnum()) {
                case BLANK:
                    newCell.setCellValue(oldCell.getStringCellValue());
                    break;
                case BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case STRING:
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
