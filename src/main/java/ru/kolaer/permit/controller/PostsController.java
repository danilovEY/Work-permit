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
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.PostPageService;

import java.util.Collections;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/post")
@Slf4j
public class PostsController {

    private final PostPageService postPageService;

    @Autowired
    public PostsController(PostPageService postPageService) {
        this.postPageService = postPageService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<PostEntity> employeePage = this.postPageService.getAll(number, pageSize);

        final ModelAndView page = new ModelAndView("/post/view");
        page.addObject("postPage", employeePage);
        return page;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ModelAndView editPost(@RequestParam("id") Integer id) {
        final PostEntity dep = this.postPageService.getById(id);

        final ModelAndView modelAndView = new ModelAndView("/post/edit");
        modelAndView.addObject("post", dep);
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPostView() {
        final ModelAndView modelAndView = new ModelAndView("/post/add");
        modelAndView.addObject("post", new PostEntity());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String addDepartment(PostEntity postEntity) {
        postEntity.setId(null);
        postEntity.setEmployees(Collections.emptyList());

        this.postPageService.add(postEntity);

        return "redirect:/post";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public String updateDepartment(PostEntity postEntity) {
        this.postPageService.update(postEntity);
        return "redirect:/post";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String removeDepartment(@RequestParam("id") Integer id) {
        final PostEntity postEntity = new PostEntity();
        postEntity.setId(id);

        this.postPageService.delete(postEntity);
        return "redirect:/post";
    }


}
