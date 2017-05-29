package ru.kolaer.permit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.service.EmployeePageService;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping("/index")
public class IndexController extends BaseController {

    public IndexController(@Value("${default.login}") String defaultLogin,
                           EmployeePageService employeePageService) {
        super(defaultLogin, employeePageService);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndexPage() {

        final ModelAndView view = this.createDefaultView("index");
        return view;
    }

}
