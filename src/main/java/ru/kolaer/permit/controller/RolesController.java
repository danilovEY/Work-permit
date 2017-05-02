package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.FullRoleEntity;
import ru.kolaer.permit.service.FullRolePageService;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/roles")
@Slf4j
public class RolesController {

    private final FullRolePageService fullRolePageService;

    @Autowired
    public RolesController(FullRolePageService fullRolePageService) {
        this.fullRolePageService = fullRolePageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<FullRoleEntity> rolesEntities = this.fullRolePageService.getAll(number, pageSize);

        final ModelAndView page = new ModelAndView("roles");
        page.addObject("rolePage", rolesEntities);
        return page;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addDepartment(FullRoleEntity roleEntity) {
        roleEntity.setId(null);

        this.fullRolePageService.add(roleEntity);

        return "redirect:/roles";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateDepartment(FullRoleEntity roleEntity) {
        this.fullRolePageService.update(roleEntity);
        return "redirect:/roles";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String removeDepartment(FullRoleEntity roleEntity) {
        this.fullRolePageService.delete(roleEntity);
        return "redirect:/roles";
    }


}
