package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dao.WorkEventDao;
import ru.kolaer.permit.dto.ExtendedPermitDto;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.PermitPageService;
import ru.kolaer.permit.service.PermitStatusHistoryPageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping("/permit")
@Slf4j
public class PermitController extends BaseController{

    private final PermitPageService permitPageService;
    private final WorkEventDao workEventDao;
    private final PermitStatusHistoryPageService permitStatusHistoryPageService;

    public PermitController(@Value("${default.login}") String defaultLogin,
                            PermitPageService permitPageService,
                            EmployeePageService employeePageService,
                            WorkEventDao workEventDao, PermitStatusHistoryPageService permitStatusHistoryPageService) {
        super(defaultLogin, employeePageService);
        this.permitPageService = permitPageService;
        this.workEventDao = workEventDao;
        this.permitStatusHistoryPageService = permitStatusHistoryPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndexPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize,
                                     @RequestParam(value = "sort", defaultValue = "0") Integer sort,
                                     @RequestParam(value = "search", defaultValue = "") String search) {
        final Page<ShortPermitEntity> all = this.permitPageService.getShortAll(number, pageSize, sort, search);

        final ModelAndView view = this.createDefaultView("/permit/view");
        view.addObject("permitPage", all);
        view.addObject("typeSort", sort);
        view.addObject("search", search);
        return view;
    }

    @RequestMapping(value = "update/work", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateWorkPage(WorkPermitEntity workPermitEntity) {
        final WorkPermitEntity updatable = this.permitPageService.update(workPermitEntity);

        return "redirect:/permit/edit/work?id=" + updatable.getId();
    }

    @RequestMapping(value = "update/people", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateWorkPage(PeoplePermitEntity peoplePermitEntity) {
        final PeoplePermitEntity updatable = this.permitPageService.update(peoplePermitEntity);

        return "redirect:/permit/edit/people?id=" + updatable.getId();
    }

    @RequestMapping(value = "update/event", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateWorkPage(EventPermitEntity eventPermitEntity) {
        final EventPermitEntity updatable = this.permitPageService.update(eventPermitEntity);

        return "redirect:/permit/edit/event?id=" + updatable.getId();
    }

    @RequestMapping(value = "add/work", method = RequestMethod.GET)
    public ModelAndView getWorkAddPage() {
        final ModelAndView view = this.createDefaultView("/permit/add/work");
        view.addObject("workPermitEntity", new WorkPermitEntity());
        return view;
    }

    @RequestMapping(value = "download/excel", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadToExcel(@RequestParam("id")Integer id, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        final File template = this.permitPageService.printPermitToExcel(id);
        if(template != null) {
            final String serialNumber = URLEncoder.
                    encode(this.permitPageService.getSerialNumber(id),"UTF-8")
                    .replaceAll("/|\\+", "%20");

            String browserType = request.getHeader("User-Agent");

            response.setContentType("application/vnd.ms-excel; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            if(browserType.contains("IE")||browserType.contains("Chrome")) {
                response.setHeader("Content-Disposition", "attachment; filename="
                        + serialNumber.replaceAll("/", "-")
                        + ".xlsx");
            } else {
                response.setHeader("Content-Disposition","attachment; filename*=UTF-8''"
                        + serialNumber.replaceAll("/", "-")
                        + ".xlsx");
            }

            response.setContentLength(Long.valueOf(template.length()).intValue());

            InputStream inputStream = new BufferedInputStream(new FileInputStream(template));

            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @RequestMapping(value = "delete/executor", method = RequestMethod.GET)
    public String deleteExecutor(@RequestParam("id")Integer id,
                                       @RequestParam("executor")Integer executor) {
        this.permitPageService.deleteExecutor(id, executor);

        return "redirect:/permit/edit/people?id=" + id;
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deletePermit(@RequestParam("id")Integer id) {
        final PermitEntity removePermit = new PermitEntity();
        removePermit.setId(id);

        this.permitPageService.delete(removePermit, this.getAuthEmployee());

        return "redirect:/permit";
    }

    @RequestMapping(value = "cancel", method = RequestMethod.GET)
    public String cancelPermit(@RequestParam("id")Integer id) {
        this.permitPageService.cancel(id, this.getAuthEmployee());

        return "redirect:/permit";
    }

    @RequestMapping(value = "add/work", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addPermitWork(WorkPermitEntity workPermitEntity) {
        boolean hasError = false;

        final ModelAndView view = this.createDefaultView("/permit/add/work");
        view.addObject("workPermitEntity", workPermitEntity);

        if(!StringUtils.hasText(workPermitEntity.getSerialNumber())) {
            hasError = true;
            view.addObject("serialError", "Серийный номер не может быть пустым!");
        }

        if(!StringUtils.hasText(workPermitEntity.getName())) {
            hasError = true;
            view.addObject("nameError", "Наименование не может быть пустым!");
        }

        if(workPermitEntity.getStartWork() == null) {
            hasError = true;
            view.addObject("startWorkError", "Укажите дату начала работ!");
        }

        if(workPermitEntity.getEndWork() == null) {
            hasError = true;
            view.addObject("endWorkError", "Укажите дату завершения работ!");
        }

        if(hasError) {
            view.addObject("workPermitEntity", workPermitEntity);
            return view;
        }

        final PermitEntity permitEntity = this.permitPageService.add(workPermitEntity,
                this.getAuthEmployee());

        return this.getEventEditPage(permitEntity.getId());
    }

    @RequestMapping(value = "edit/work", method = RequestMethod.GET)
    public ModelAndView getWorkEditPage(@RequestParam(value = "id") Integer id) {
        final WorkPermitEntity workPermitEntity= this.permitPageService.getWorkById(id);

        final ModelAndView view = this.createDefaultView("/permit/edit/work");
        view.addObject("workPermitEntity", workPermitEntity);
        return view;
    }

    @RequestMapping(value = "view/work", method = RequestMethod.GET)
    public ModelAndView getWorkViewPage(@RequestParam(value = "id") Integer id) {
        final WorkPermitEntity workPermitEntity= this.permitPageService.getWorkById(id);

        final ModelAndView view = this.createDefaultView("/permit/view/work");
        view.addObject("workPermitEntity", workPermitEntity);
        return view;
    }

    @RequestMapping(value = "edit/event", method = RequestMethod.GET)
    public ModelAndView getEventEditPage(@RequestParam(value = "id") Integer id) {
        final List<WorkEvent> workEvents = this.workEventDao.findByIdPermit(id, false);

        final EventPermitEntity eventPermitEntity = new EventPermitEntity();
        eventPermitEntity.setId(id);
        eventPermitEntity.setWorkEvents(workEvents);

        final ModelAndView view = this.createDefaultView("/permit/edit/event");
        view.addObject("eventPermitEntity", eventPermitEntity);
        view.addObject("employees", this.employeePageService.getAll());
        return view;
    }

    @RequestMapping(value = "view/event", method = RequestMethod.GET)
    public ModelAndView getEventViewPage(@RequestParam(value = "id") Integer id) {
        final List<WorkEvent> workEvents = this.workEventDao.findByIdPermit(id, false);

        final EventPermitEntity eventPermitEntity = new EventPermitEntity();
        eventPermitEntity.setId(id);
        eventPermitEntity.setWorkEvents(workEvents);

        final ModelAndView view = this.createDefaultView("/permit/view/event");
        view.addObject("eventPermitEntity", eventPermitEntity);
        return view;
    }

    @RequestMapping(value = "edit/people", method = RequestMethod.GET)
    public ModelAndView getPeopleEditPage(@RequestParam(value = "id") Integer id) {
        final PeoplePermitEntity peoplePermitEntity = this.permitPageService.getPeopleById(id);

        final ModelAndView view = this.createDefaultView("/permit/edit/people");
        view.addObject("peoplePermitEntity", peoplePermitEntity);
        view.addObject("employees", this.employeePageService.getAll());
        return view;
    }

    @RequestMapping(value = "view/people", method = RequestMethod.GET)
    public ModelAndView getPeopleViewPage(@RequestParam(value = "id") Integer id) {
        final PeoplePermitEntity peoplePermitEntity = this.permitPageService.getPeopleById(id);

        final ModelAndView view = this.createDefaultView("/permit/view/people");
        view.addObject("peoplePermitEntity", peoplePermitEntity);
        return view;
    }

    @RequestMapping(value = "view/history", method = RequestMethod.GET)
    public ModelAndView getHistoryViewPage(@RequestParam(value = "id") Integer id) {
        final List<PermitStatusHistoryEntity> statuses = this.permitStatusHistoryPageService.getAllByPermitId(id);

        final ModelAndView view = this.createDefaultView("/permit/view/history");
        view.addObject("statuses", statuses);
        view.addObject("permitId", id);
        return view;
    }

    @RequestMapping(value = "edit/history", method = RequestMethod.GET)
    public ModelAndView getHistoryEditPage(@RequestParam(value = "id") Integer id) {
        final List<PermitStatusHistoryEntity> statuses = this.permitStatusHistoryPageService.getAllByPermitId(id);

        final ModelAndView view = this.createDefaultView("/permit/edit/history");
        view.addObject("statuses", statuses);
        view.addObject("permitId", id);
        return view;
    }

    @RequestMapping(value = "action/need/approve", method = RequestMethod.GET)
    public String setNeedApproveStatus(@RequestParam(value = "id") Integer id) {
        this.permitPageService.setStatus(id, PermitPageService.NEED_APPROVE_STATUS, this.getAuthEmployee());

        return "redirect:/permit";
    }

    @RequestMapping(value = "action/approve", method = RequestMethod.GET)
    public String setApproveStatus(@RequestParam(value = "id") Integer id) {
        this.permitPageService.setStatus(id, PermitPageService.APPROVE_STATUS, this.getAuthEmployee());

        return "redirect:/permit";
    }

    @RequestMapping(value = "action/end", method = RequestMethod.GET)
    public String setEndStatus(@RequestParam(value = "id") Integer id) {
        this.permitPageService.endPermit(id, this.getAuthEmployee());

        return "redirect:/permit";
    }


    @RequestMapping(value = "action/permit", method = RequestMethod.GET)
    public String setPermit(@RequestParam(value = "id") Integer id) {
        this.permitPageService.setStatus(id, PermitPageService.PERMIT_STATUS, this.getAuthEmployee());

        return "redirect:/permit";
    }

    @RequestMapping(value = "action/extend", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateWorkPage(ExtendedPermitDto extendedPermitDto) {
        final boolean updatable = this.permitPageService.extendPermit(extendedPermitDto.getId(),
                extendedPermitDto.getExtendedPermit(),
                this.getAuthEmployee());

        return "redirect:/permit";
    }
}
