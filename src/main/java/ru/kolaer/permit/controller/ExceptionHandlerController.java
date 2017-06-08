package ru.kolaer.permit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.service.EmployeePageService;

/**
 * Created by danilovey on 08.06.2017.
 */
@Controller
public class ExceptionHandlerController extends BaseController {

    public ExceptionHandlerController(@Value("${default.login}") String defaultLogin,
                                      EmployeePageService employeePageService) {
        super(defaultLogin, employeePageService);
    }

    @RequestMapping("/404")
    public ModelAndView notFountPage() {
        return this.createDefaultView("/404");
    }

    @RequestMapping("/403")
    public ModelAndView notAccessPage() {
        return this.createDefaultView("/403");
    }
}
