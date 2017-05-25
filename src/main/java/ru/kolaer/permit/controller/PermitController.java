package ru.kolaer.permit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.dto.ShortPermitDto;
import ru.kolaer.permit.entity.PermitEntity;
import ru.kolaer.permit.service.PermitPageService;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping("/permit")
public class PermitController {

    private final PermitPageService permitPageService;

    public PermitController(PermitPageService permitPageService) {
        this.permitPageService = permitPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndexPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize) {
        final Page<PermitEntity> all = this.permitPageService.getAll(number, pageSize);
        final ModelAndView view = new ModelAndView("/permit/view");
        view.addObject("permitPage", all);
        return view;
    }

}
