package ru.kolaer.permit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by danilovey on 12.04.2017.
 */
@Controller
public class HelloWorldController {

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String getIndex(Model model) {
        model.addAttribute("test", "Hello world!");
        return "hello";
    }

}
