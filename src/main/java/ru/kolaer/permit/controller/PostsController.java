package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.EmployeeEntity;
import ru.kolaer.permit.entity.PostEntity;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.PostPageService;

/**
 * Created by danilovey on 20.04.2017.
 */
@Controller
@RequestMapping("/posts")
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

        final ModelAndView page = new ModelAndView("posts");
        page.addObject("postPage", employeePage);
        return page;
    }

}
