package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.service.DepartmentPageService;
import ru.kolaer.permit.service.EmployeePageService;

import java.util.Collections;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/department")
@Slf4j
public class DepartmentController extends BaseController {

    private final DepartmentPageService departmentPageService;

    @Autowired
    public DepartmentController(@Value("${default.login}") String defaultLogin,
                                DepartmentPageService departmentPageService,
                                EmployeePageService employeePageService) {
        super(defaultLogin, employeePageService);
        this.departmentPageService = departmentPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<DepartmentEntity> departmentPage = this.departmentPageService.getAll(number, pageSize);

        final ModelAndView modelAndView = this.createDefaultView("/department/view");
        modelAndView.addObject("departmentPage", departmentPage);
        return modelAndView;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editDepartment(@RequestParam("id") Long id) {
        final DepartmentEntity dep = this.departmentPageService.getById(id);

        final ModelAndView modelAndView = this.createDefaultView("/department/edit");
        modelAndView.addObject("department", dep);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addDepartmentView() {
        final ModelAndView modelAndView = this.createDefaultView("/department/add");
        modelAndView.addObject("department", new DepartmentEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addDepartment(DepartmentEntity departmentEntity) {
        departmentEntity.setId(null);
        departmentEntity.setEmployees(Collections.emptyList());

        final ModelAndView hasErrorPage = this.checkDepartment(this.createDefaultView("/department/add"),
                departmentEntity);
        if(hasErrorPage != null)
            return hasErrorPage;

        this.departmentPageService.add(departmentEntity);

        return this.getStartPage(1, 15);
    }

    private ModelAndView checkDepartment(ModelAndView modelAndView, DepartmentEntity departmentEntity) {
        boolean hasErrors = false;

        if(!StringUtils.hasText(departmentEntity.getName())) {
            hasErrors = true;
            modelAndView.addObject("nameError", "Наименование не может быть пустым!");
        } else if(this.departmentPageService.existDepartment(departmentEntity)) {
            hasErrors = true;
            modelAndView.addObject("nameError", "Такое наименование уже существует!");
        }

        if(!StringUtils.hasText(departmentEntity.getAbbreviatedName())) {
            hasErrors = true;
            modelAndView.addObject("abbreviatedNameError", "Краткое наименование не может быть пустым!");
        }

        if(hasErrors) {
            modelAndView.addObject("department", departmentEntity);
            return modelAndView;
        }

        return null;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView updateDepartment(DepartmentEntity departmentEntity) {
        final ModelAndView hasErrorPage = this.checkDepartment(this.createDefaultView("/department/edit"),
                departmentEntity);
        if(hasErrorPage != null)
            return hasErrorPage;

        this.departmentPageService.update(departmentEntity);
        return this.getStartPage(1, 15);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String removeDepartment(@RequestParam("id") Long id) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(id);
        this.departmentPageService.delete(departmentEntity);
        return "redirect:/department";
    }

}
