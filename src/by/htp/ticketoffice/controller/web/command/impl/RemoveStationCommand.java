package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.service.impl.RemoveStationService;

import javax.servlet.http.HttpServletRequest;

public class RemoveStationCommand implements BasicCommand {

    @Override
    public String performAction(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = RemoveStationService.getInstance().doService(request);
        if (result) {
            page = JspPageName.MAIN_PAGE;
        }
        return page;
    }
}