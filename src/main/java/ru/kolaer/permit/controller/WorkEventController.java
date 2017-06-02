package ru.kolaer.permit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.TypeEvent;
import ru.kolaer.permit.dao.WorkEventDao;
import ru.kolaer.permit.entity.PermitEntity;
import ru.kolaer.permit.entity.WorkEvent;
import ru.kolaer.permit.service.EmployeePageService;

import java.util.Collections;

/**
 * Created by Danilov on 01.06.2017.
 */
@Controller
@RequestMapping("event")
public class WorkEventController extends BaseController {

    private final WorkEventDao workEventDao;

    public WorkEventController(@Value("${default.login}") String defaultLogin,
                               EmployeePageService employeePageService,
                               WorkEventDao workEventDao) {
        super(defaultLogin, employeePageService);
        this.workEventDao = workEventDao;
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public ModelAndView getEventEditPage(@RequestParam(value = "permit") Integer idPermit,
                                         @RequestParam(value = "type", required = false) String typeEvent) {
        final PermitEntity permitEntity = new PermitEntity();
        permitEntity.setId(idPermit);

        final WorkEvent workEvent = new WorkEvent();
        workEvent.setTypeEvent(TypeEvent.valueOf(typeEvent));
        workEvent.setPermitId(idPermit);
        workEvent.setPermit(permitEntity);
        workEvent.setEmployeesEntity(Collections.emptyList());

        final ModelAndView view = this.createDefaultView("/event/add");
        view.addObject("workEvent", workEvent);
        view.addObject("employees", this.employeePageService.getAll());
        return view;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView getEventEditPage(@RequestParam(value = "id") Integer id) {
        final WorkEvent workEvent = this.workEventDao.findById(id, false);

        final ModelAndView view = this.createDefaultView("/event/edit");
        view.addObject("workEvent", workEvent);
        view.addObject("employees", this.employeePageService.getAll());
        return view;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateWorkPage(WorkEvent workEvent) {
        final WorkEvent updatable = this.workEventDao.update(workEvent);

        return "redirect:/event/edit?id=" + updatable.getId();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addWorkEvent(WorkEvent workEvent) {
        final WorkEvent updatable = this.workEventDao.persist(workEvent);

        return "redirect:/event/edit?id=" + updatable.getId();
    }


    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteEmployeeFromWorkEvent(@RequestParam(value = "id") Integer id) {

        WorkEvent workEvent = this.workEventDao.findById(id, false);
        workEvent.setId(id);

        final int permitId = workEvent.getPermitId();

        this.workEventDao.delete(workEvent, true);
        return "redirect:/permit/edit/event?id=" + permitId;
    }

    @RequestMapping(value = "delete/employee", method = RequestMethod.GET)
    public String deleteEmployeeFromWorkEvent(@RequestParam(value = "id") Integer id,
                                                    @RequestParam(value = "employee") Integer idEmployee) {
        this.workEventDao.deleteEmployee(id, idEmployee);
        return "redirect:/event/edit?id=" + id;
    }

}
