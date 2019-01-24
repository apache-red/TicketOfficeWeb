package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.service.impl.AddNewRouteService;
import by.htp.ticketoffice.service.impl.RecordAllRoutesService;

import javax.servlet.http.HttpServletRequest;

public class AddNewRouteCommand implements BasicCommand {


        @Override
        public String performAction(HttpServletRequest request) throws CommandException {
            boolean result = false;

            result = AddNewRouteService.getInstance().doService(request);

            if (result) {
                RecordAllRoutesService.getInstance().doService(request);
            }
            return JspPageName.MAIN_PAGE;
        }

}
