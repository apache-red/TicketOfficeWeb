package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.service.impl.RemoveFromBlacklistService;
import by.htp.ticketoffice.service.impl.ShowBlacklistService;

import javax.servlet.http.HttpServletRequest;



public class RemoveFromBlacklistCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {

		RemoveFromBlacklistService.getInstance().doService(request);
		ShowBlacklistService.getInstance().doService(request);

		return JspPageName.BLACKLIST_PAGE;
	}
}
