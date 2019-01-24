package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.service.impl.OrderService;
import by.htp.ticketoffice.service.impl.ShowInformationAboutStationService;

import javax.servlet.http.HttpServletRequest;



public class OrderCommand implements BasicCommand {

	@Override
	public String performAction(HttpServletRequest request) throws CommandException {
		String page = null;
		boolean result = false;

		result = OrderService.getInstance().doService(request);

		if (result) {
			page = JspPageName.SUCCESSFUL_ORDER_PAGE;
		} else {
			ShowInformationAboutStationService.getInstance().doService(request);
			page = JspPageName.SINGLE_STATION_PAGE;
		}
		return page;
	}
}
