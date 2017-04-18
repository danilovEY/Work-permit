package ru.kolaer.permit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Created by danilovey on 13.04.2017.
 */
@Controller
@RequestMapping
@Slf4j
public class AuthController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam(value = "access", defaultValue = "false") Boolean access,
                                     @RequestParam(value = "logout", required = false) String logout,
                                     @RequestParam(value = "error", defaultValue = "false") Boolean error) {
        final ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("access", access);
        modelAndView.addObject("logout", logout != null);
        modelAndView.addObject("error", error);
        return modelAndView;
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
        .ifPresent(auth -> new SecurityContextLogoutHandler().logout(request, response, auth));
        return new ModelAndView("redirect:/login?logout");
    }
}
