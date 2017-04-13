package ru.kolaer.permit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndexPage() {

        final ModelAndView view = new ModelAndView("index");
        return view;
    }

}
