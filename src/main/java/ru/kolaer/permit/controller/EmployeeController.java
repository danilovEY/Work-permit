package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.DepartmentEntity;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.DepartmentPageService;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.PostPageService;

import java.util.List;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/employee")
@Slf4j
public class EmployeeController extends BaseController {

    private final EmployeePageService employeePageService;
    private final DepartmentPageService departmentPageService;
    private final PostPageService postPageService;

    @Autowired
    public EmployeeController(@Value("${default.login}") String defaultLogin,
                              EmployeePageService employeePageService,
                              DepartmentPageService departmentPageService,
                              PostPageService postPageService) {
        super(defaultLogin, employeePageService);
        this.employeePageService = employeePageService;
        this.departmentPageService = departmentPageService;
        this.postPageService = postPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<EmployeeEntity> employeePage = this.employeePageService.getAll(number, pageSize);

        final ModelAndView page = this.createDefaultView("/employee/view");
        page.addObject("employeesPage", employeePage);
        return page;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editEmployeeView(@RequestParam(value = "id") Integer id){
        final EmployeeEntity employee = this.employeePageService.getById(id);
        final List<DepartmentEntity> departmentEntities = this.departmentPageService.getAll();
        final List<PostEntity> postEntities = this.postPageService.getAll();

        final ModelAndView page = this.createDefaultView("/employee/edit");
        page.addObject("employee", employee);
        page.addObject("posts", postEntities);
        page.addObject("departments", departmentEntities);
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEmployeeView() {
        final EmployeeEntity employee = new EmployeeEntity();
        final List<DepartmentEntity> departmentEntities = this.departmentPageService.getAll();
        final List<PostEntity> postEntities = this.postPageService.getAll();

        final ModelAndView page = this.createDefaultView("/employee/add");
        page.addObject("employee", employee);
        page.addObject("posts", postEntities);
        page.addObject("departments", departmentEntities);
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addDepartment(EmployeeEntity employeeEntity) {
        employeeEntity.setId(null);

        if(!StringUtils.hasText(employeeEntity.getPassword())){
            employeeEntity.setPassword(employeeEntity.getPersonnelNumber().toString());
        }

        if(!StringUtils.hasText(employeeEntity.getUsername())){
            employeeEntity.setUsername(employeeEntity.getPersonnelNumber().toString());
        }

        this.employeePageService.add(employeeEntity);

        return "redirect:/employee";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateEmployee(EmployeeEntity employeeEntity) {

        if(!StringUtils.hasText(employeeEntity.getPassword())){
            employeeEntity.setPassword(employeeEntity.getPersonnelNumber().toString());
        }

        if(!StringUtils.hasText(employeeEntity.getUsername())){
            employeeEntity.setUsername(employeeEntity.getPersonnelNumber().toString());
        }

        this.employeePageService.update(employeeEntity);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String removeEmployee(@RequestParam("id") Integer id) {
        final EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setId(id);

        this.employeePageService.delete(employeeEntity);
        return "redirect:/employee";
    }

}
