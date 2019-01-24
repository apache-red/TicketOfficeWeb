package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.service.impl.AddNewStationService;
import by.htp.ticketoffice.service.impl.GoAddStationService;

import javax.servlet.http.HttpServletRequest;

public class AddNewStationCommand implements BasicCommand {

    @Override
    public String performAction(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = AddNewStationService.getInstance().doService(request);

        if (result) {
            page = JspPageName.MAIN_PAGE;
        } else {
            GoAddStationService.getInstance().doService(request);
            page = JspPageName.ADD_NEW_STATION_PAGE;
        }
        return page;
    }


}