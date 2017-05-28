package ru.kolaer.permit.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.*;
import ru.kolaer.permit.service.PermitPageService;
import ru.kolaer.permit.service.PermitStatusHistoryPageService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping("/permit")
public class PermitController {

    private final PermitPageService permitPageService;
    private final PermitStatusHistoryPageService permitStatusHistoryPageService;

    public PermitController(PermitPageService permitPageService,
                            PermitStatusHistoryPageService permitStatusHistoryPageService) {
        this.permitPageService = permitPageService;
        this.permitStatusHistoryPageService = permitStatusHistoryPageService;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ModelAndView getIndexPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize) {
        final Page<ShortPermitEntity> all = this.permitPageService.getShortAll(number, pageSize);

        final List<PermitStatusHistoryEntity> lastStatusByIdPermitRange = this.permitStatusHistoryPageService
                .getLastStatusByIdPermitRange(all.getData().stream()
                        .map(ShortPermitEntity::getId)
                        .collect(Collectors.toList()));

        final ModelAndView view = new ModelAndView("/permit/view");
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

    @RequestMapping(value = "edit/work", method = RequestMethod.GET)
    public ModelAndView getWorkEditPage(@RequestParam(value = "id") Integer id) {
        final WorkPermitEntity workPermitEntity= this.permitPageService.getWorkById(id);

        final ModelAndView view = new ModelAndView("/permit/edit/work");
        view.addObject("workPermitEntity", workPermitEntity);
        return view;
    }

    @RequestMapping(value = "edit/event", method = RequestMethod.GET)
    public ModelAndView getEventEditPage(@RequestParam(value = "id") Integer id) {
        final EventPermitEntity eventPermitEntity = this.permitPageService.getEventById(id);

        final ModelAndView view = new ModelAndView("/permit/edit/event");
        view.addObject("eventPermitEntity", eventPermitEntity);
        return view;
    }

    @RequestMapping(value = "edit/people", method = RequestMethod.GET)
    public ModelAndView getPeopleEditPage(@RequestParam(value = "id") Integer id) {
        final PeoplePermitEntity peoplePermitEntity = this.permitPageService.getPeopleById(id);

        final ModelAndView view = new ModelAndView("/permit/edit/people");
        view.addObject("peoplePermitEntity", peoplePermitEntity);
        return view;
    }

}
