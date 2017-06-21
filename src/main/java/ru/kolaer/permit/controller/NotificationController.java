package ru.kolaer.permit.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.kolaer.permit.dto.Page;
import ru.kolaer.permit.entity.NotificationEntity;
import ru.kolaer.permit.service.EmployeePageService;
import ru.kolaer.permit.service.NotificationPageService;

/**
 * Created by danilovey on 20.06.2017.
 */
@Controller
@RequestMapping("/notification")
public class NotificationController extends BaseController {
    public NotificationController(@Value("${default.login}") String defaultLogin,
                                  EmployeePageService employeePageService,
                                  NotificationPageService notificationPageService) {
        super(defaultLogin, employeePageService, notificationPageService);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getStartPage(@RequestParam(value = "page", defaultValue = "1") Integer number,
                                     @RequestParam(value = "pagesize", defaultValue = "15") Integer pageSize){

        final Page<NotificationEntity> notificationPage = this.notificationPageService.getByEmployeeId(number, pageSize,
                this.getAuthEmployee().getId());

        notificationPage.getData().forEach(n -> n.setRead(true));

        this.notificationPageService.updateAll(notificationPage.getData());

        final ModelAndView modelAndView = this.createDefaultView("/notification/view");
        modelAndView.addObject("notifications", notificationPage);
        return modelAndView;
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public String readAndRedirect(@RequestParam(value = "id") Long id){
        NotificationEntity notification = this.notificationPageService.getById(id);

        notification.setRead(true);

        this.notificationPageService.update(notification);

        switch (notification.getType()) {
            case NEED_APPROVE_STATUS:
            case APPROVE_STATUS: return "redirect:/permit/view/history?id=" + notification.getEventFromId().toString();
            default: return "redirect:/notification";
        }
    }
}
