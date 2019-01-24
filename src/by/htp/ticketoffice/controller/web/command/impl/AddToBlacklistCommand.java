package by.htp.ticketoffice.controller.web.command.impl;

import javax.servlet.http.HttpServletRequest;


import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.service.impl.AddToBlacklistService;
import by.htp.ticketoffice.service.impl.ShowCorrectClientsService;

public class AddToBlacklistCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {

		AddToBlacklistService.getInstance().doService(request);
		ShowCorrectClientsService.getInstance().doService(request);

		return JspPageName.ALL_CLIENTS_PAGE;
	}

}
