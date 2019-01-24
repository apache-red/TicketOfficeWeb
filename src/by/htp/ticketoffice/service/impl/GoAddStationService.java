package by.htp.ticketoffice.service.impl;

import by.htp.ticketoffice.controller.web.servlet.util.RequestParameterName;
import by.htp.ticketoffice.service.DBService;

import javax.servlet.http.HttpServletRequest;

public class GoAddStationService implements DBService {

    private static final GoAddStationService instance = new GoAddStationService();

    public static GoAddStationService getInstance() {
        return instance;
    }

    @Override
    public boolean doService(HttpServletRequest request) {
        boolean result = false;
        String idRoute = null;

        idRoute = request.getParameter(RequestParameterName.ID_ROUTE);

        if (idRoute != null) {
            request.setAttribute(RequestParameterName.ID_ROUTE, idRoute);
            result = true;
        }
        return result;
    }

}
