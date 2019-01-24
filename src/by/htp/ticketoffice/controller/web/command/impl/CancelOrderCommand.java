package by.htp.ticketoffice.controller.web.command.impl;

import by.htp.ticketoffice.exceptions.CommandException;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.servlet.util.JspPageName;
import by.htp.ticketoffice.service.impl.CancelOrderService;
import by.htp.ticketoffice.service.impl.ShowOrdersOfOneClientService;

import javax.servlet.http.HttpServletRequest;

public class CancelOrderCommand implements BasicCommand {

    @Override
    public String performAction(HttpServletRequest request) throws CommandException {
        String page = null;
        boolean result = false;

        result = CancelOrderService.getInstance().doService(request);
        if (result) {
            page = JspPageName.SHOPPING_CART_PAGE;
            ShowOrdersOfOneClientService.getInstance().doService(request);
        }
        return page;
    }
}
