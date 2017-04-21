package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.DepartmentPageService;
import ru.kolaer.permit.service.PostPageService;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/departments")
@Slf4j
public class DepartmentsController {

    private final DepartmentPageService departmentPageService;

    @Autowired
    public DepartmentsController(DepartmentPageService departmentPageService) {
        this.departmentPageService = departmentPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<DepartmentEntity> departmentPage = this.departmentPageService.getAll(number, pageSize);

        final ModelAndView page = new ModelAndView("departments");
        page.addObject("departmentPage", departmentPage);
        return page;
    }

}
