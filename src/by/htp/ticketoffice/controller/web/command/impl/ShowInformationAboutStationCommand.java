package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.service.impl.ShowInformationAboutStationService;

import javax.servlet.http.HttpServletRequest;

public class ShowInformationAboutStationCommand implements BasicCommand {

    @Override
    public String performAction(HttpServletRequest request) throws CommandException {

        boolean result = false;
        String page = null;

        result = ShowInformationAboutStationService.getInstance().doService(request);
        if (result) {
            page = JspPageName.SINGLE_STATION_PAGE;
        }
        return page;
    }

}