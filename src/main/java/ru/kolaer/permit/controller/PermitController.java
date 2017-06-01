package ru.kolaer.permit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dao.WorkEventDao;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.PermitPageService;
import ru.kolaer.permit.service.PermitStatusHistoryPageService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping("/permit")
public class PermitController extends BaseController{

    private final PermitPageService permitPageService;
    private final PermitStatusHistoryPageService permitStatusHistoryPageService;
    private final WorkEventDao workEventDao;

    public PermitController(@Value("${default.login}") String defaultLogin,
                            PermitPageService permitPageService,
                            PermitStatusHistoryPageService permitStatusHistoryPageService,
                            EmployeePageService employeePageService,
                            WorkEventDao workEventDao) {
        super(defaultLogin, employeePageService);
        this.permitPageService = permitPageService;
        this.permitStatusHistoryPageService = permitStatusHistoryPageService;
        this.workEventDao = workEventDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndexPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize) {
        final Page<ShortPermitEntity> all = this.permitPageService.getShortAll(number, pageSize);

        final List<PermitStatusHistoryEntity> lastStatusByIdPermitRange = this.permitStatusHistoryPageService
                .getLastStatusByIdPermitRange(all.getData().stream()
                        .map(ShortPermitEntity::getId)
                        .collect(Collectors.toList()));

        final ModelAndView view = this.createDefaultView("/permit/view");
        view.addObject("permitPage", all);
        view.addObject("permitStatus", lastStatusByIdPermitRange);
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

    @RequestMapping(value = "delete/executor", method = RequestMethod.GET)
    public String deleteExecutor(@RequestParam("id")Integer id,
                                       @RequestParam("executor")Integer executor) {
        this.permitPageService.deleteExecutor(id, executor);

        return "redirect:/permit/edit/people?id=" + id;
    }

    @RequestMapping(value = "add/work", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addPermitWork(WorkPermitEntity workPermitEntity) {
        final  PermitEntity permitEntity = new PermitEntity();
        permitEntity.setSerialNumber(workPermitEntity.getSerialNumber());
        permitEntity.setName(workPermitEntity.getName());
        permitEntity.setPlaceWork(workPermitEntity.getPlaceWork());
        permitEntity.setContentWork(workPermitEntity.getContentWork());
        permitEntity.setConditionWork(workPermitEntity.getConditionWork());
        permitEntity.setStartWork(workPermitEntity.getStartWork());
        permitEntity.setEndWork(workPermitEntity.getEndWork());
        permitEntity.setMaterials(workPermitEntity.getMaterials());
        permitEntity.setInstruments(workPermitEntity.getInstruments());
        permitEntity.setAdaptations(workPermitEntity.getAdaptations());
        permitEntity.setRetaining(workPermitEntity.getRetaining());
        permitEntity.setPosition(workPermitEntity.getPosition());
        permitEntity.setSafety(workPermitEntity.getSafety());
        permitEntity.setRescue(workPermitEntity.getRescue());

        permitEntity.setWriter(this.getAuthEmployee());

        final PermitStatusHistoryEntity createNewPermit = new PermitStatusHistoryEntity();
        createNewPermit.setEmployee(permitEntity.getWriter());
        createNewPermit.setPermit(permitEntity);
        createNewPermit.setPermitId(permitEntity.getId());
        createNewPermit.setStatusDate(new Date());
        createNewPermit.setStatus("Редактирование");

        permitEntity.setPermitStatusHistories(Collections.singletonList(createNewPermit));

        this.permitPageService.add(permitEntity);

        return "redirect:/permit/edit/work?id=" + permitEntity.getId();
    }

    @RequestMapping(value = "edit/work", method = RequestMethod.GET)
    public ModelAndView getWorkEditPage(@RequestParam(value = "id") Integer id) {
        final WorkPermitEntity workPermitEntity= this.permitPageService.getWorkById(id);

        final ModelAndView view = this.createDefaultView("/permit/edit/work");
        view.addObject("workPermitEntity", workPermitEntity);
        return view;
    }

    @RequestMapping(value = "edit/event", method = RequestMethod.GET)
    public ModelAndView getEventEditPage(@RequestParam(value = "id") Integer id) {
        final List<WorkEvent> workEvents = this.workEventDao.findByIdPermit(id);

        final EventPermitEntity eventPermitEntity = new EventPermitEntity();
        eventPermitEntity.setId(id);
        eventPermitEntity.setWorkEvents(workEvents);

        final ModelAndView view = this.createDefaultView("/permit/edit/event");
        view.addObject("eventPermitEntity", eventPermitEntity);
        view.addObject("employees", this.employeePageService.getAll());
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

}
