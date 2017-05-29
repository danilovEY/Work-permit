package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.dto.RoleNameDto;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.FullRoleEntity;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.FullRolePageService;

import java.util.List;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController extends BaseController {

    private final FullRolePageService fullRolePageService;
    private final RoleNameDto roleNameDto;
    private final EmployeePageService employeePageService;

    @Autowired
    public RoleController(@Value("${default.login}") String defaultLogin,
                          FullRolePageService fullRolePageService,
                          RoleNameDto roleNameDto,
                          EmployeePageService employeePageService) {
        super(defaultLogin, employeePageService);
        this.fullRolePageService = fullRolePageService;
        this.roleNameDto = roleNameDto;
        this.employeePageService = employeePageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<FullRoleEntity> rolesEntities = this.fullRolePageService.getAll(number, pageSize);

        final ModelAndView page = this.createDefaultView("/role/view");
        page.addObject("rolePage", rolesEntities);
        page.addObject("roleNameMap", this.roleNameDto.getRoleNameMap());
        return page;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editRoleView(@RequestParam(value = "id") Integer id){
        final FullRoleEntity role = this.fullRolePageService.getById(id);
        final List<EmployeeEntity> employeeEntities = this.employeePageService.getAll();

        final ModelAndView page = this.createDefaultView("/role/edit");
        page.addObject("role", role);
        page.addObject("roleNameMap", this.roleNameDto.getRoleNameMap());
        page.addObject("employees", employeeEntities);
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addEmployeeView() {
        final FullRoleEntity role = new FullRoleEntity();
        final List<EmployeeEntity> employeeEntities = this.employeePageService.getAll();

        final ModelAndView page = this.createDefaultView("/role/add");
        page.addObject("role", role);
        page.addObject("roleNameMap", this.roleNameDto.getRoleNameMap());
        page.addObject("employees", employeeEntities);
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addDepartment(FullRoleEntity roleEntity) {
        roleEntity.setId(null);

        this.fullRolePageService.add(roleEntity);

        return "redirect:/role";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateDepartment(FullRoleEntity roleEntity) {
        this.fullRolePageService.update(roleEntity);
        return "redirect:/role";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String removeDepartment(@RequestParam("id") Integer id) {
        final FullRoleEntity fullRoleEntity = new FullRoleEntity();
        fullRoleEntity.setId(id);

        this.fullRolePageService.delete(fullRoleEntity);
        return "redirect:/role";
    }


}
