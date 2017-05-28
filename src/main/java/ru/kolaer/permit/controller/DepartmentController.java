package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.service.DepartmentPageService;

import java.util.Collections;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/department")
@Slf4j
public class DepartmentController {

    private final DepartmentPageService departmentPageService;

    @Autowired
    public DepartmentController(DepartmentPageService departmentPageService) {
        this.departmentPageService = departmentPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<DepartmentEntity> departmentPage = this.departmentPageService.getAll(number, pageSize);

        final ModelAndView modelAndView = new ModelAndView("/department/view");
        modelAndView.addObject("departmentPage", departmentPage);
        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editDepartment(@RequestParam("id") Integer id) {
        final DepartmentEntity dep = this.departmentPageService.getById(id);

        final ModelAndView modelAndView = new ModelAndView("/department/edit");
        modelAndView.addObject("department", dep);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addDepartmentView() {
        final ModelAndView modelAndView = new ModelAndView("/department/add");
        modelAndView.addObject("department", new DepartmentEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addDepartment(DepartmentEntity departmentEntity) {
        departmentEntity.setId(null);
        departmentEntity.setEmployees(Collections.emptyList());

        this.departmentPageService.add(departmentEntity);

        return "redirect:/department";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateDepartment(DepartmentEntity departmentEntity) {
        this.departmentPageService.update(departmentEntity);
        return "redirect:/department";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String removeDepartment(@RequestParam("id") Integer id) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(id);
        this.departmentPageService.delete(departmentEntity);
        return "redirect:/department";
    }

}
