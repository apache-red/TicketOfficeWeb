package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.service.impl.GoEditStationService;

import javax.servlet.http.HttpServletRequest;

public class GoEditStationCommand implements BasicCommand {

    @Override
    public String performAction(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = GoEditStationService.getInstance().doService(request);
        if (result) {
            page = JspPageName.EDIT_STATION_PAGE;
        }
        return page;
    }
}