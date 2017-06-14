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
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.PostPageService;

import java.util.Collections;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/post")
@Slf4j
public class PostsController extends BaseController {

    private final PostPageService postPageService;

    @Autowired
    public PostsController(@Value("${default.login}") String defaultLogin,
                           PostPageService postPageService,
                           EmployeePageService employeePageService) {
        super(defaultLogin, employeePageService);
        this.postPageService = postPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<PostEntity> employeePage = this.postPageService.getAll(number, pageSize);

        final ModelAndView page = this.createDefaultView("/post/view");
        page.addObject("postPage", employeePage);
        return page;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editPost(@RequestParam("id") Integer id) {
        final PostEntity dep = this.postPageService.getById(id);

        final ModelAndView modelAndView = this.createDefaultView("/post/edit");
        modelAndView.addObject("post", dep);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPostView() {
        final ModelAndView modelAndView = this.createDefaultView("/post/add");
        modelAndView.addObject("post", new PostEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView addDepartment(PostEntity postEntity) {
        postEntity.setId(null);
        postEntity.setEmployees(Collections.emptyList());

        ModelAndView returnedView = this.checkPost(this.createDefaultView("/post/add"),
                postEntity);
        if(returnedView != null)
            return returnedView;

        this.postPageService.add(postEntity);

        return this.getStartPage(1, 15);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ModelAndView updateDepartment(PostEntity postEntity) {
        ModelAndView returnedView = this.checkPost(this.createDefaultView("/post/edit"),
                postEntity);
        if(returnedView != null)
            return returnedView;

        this.postPageService.update(postEntity);

        return this.getStartPage(1, 15);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String removeDepartment(@RequestParam("id") Integer id) {
        final PostEntity postEntity = new PostEntity();
        postEntity.setId(id);

        this.postPageService.delete(postEntity);
        return "redirect:/post";
    }

    private ModelAndView checkPost(ModelAndView modelAndView, PostEntity postEntity) {
        boolean hasErrors = false;

        if(!StringUtils.hasText(postEntity.getName())) {
            hasErrors = true;
            modelAndView.addObject("nameError", "Наименование не может быть пустым!");
        } else if(this.postPageService.existPost(postEntity)) {
            hasErrors = true;
            modelAndView.addObject("nameError", "Такое наименование уже существует!");
        }

        if(!StringUtils.hasText(postEntity.getAbbreviatedName())) {
            hasErrors = true;
            modelAndView.addObject("abbreviatedNameError", "Краткое наименование не может быть пустым!");
        }

        if(hasErrors) {
            modelAndView.addObject("post", postEntity);
            return modelAndView;
        }

        return null;
    }


}
