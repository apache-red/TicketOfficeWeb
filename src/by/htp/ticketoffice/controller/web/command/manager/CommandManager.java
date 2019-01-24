package by.htp.ticketoffice.controller.web.command.manager;

import by.htp.ticketoffice.controller.web.command.manager.util.CommandName;
import by.htp.ticketoffice.controller.web.command.BasicCommand;
import by.htp.ticketoffice.controller.web.command.impl.*;

import java.util.HashMap;
import java.util.Map;


public final class CommandManager {

    private static final CommandManager instance = new CommandManager();
    private Map<CommandName, BasicCommand> commands = new HashMap<>();

    public CommandManager() {

        commands.put(CommandName.ADD_NEW_ROUTE, new AddNewRouteCommand());
        commands.put(CommandName.ADD_NEW_STATION, new AddNewStationCommand());
        commands.put(CommandName.SHOW_INFORMATION_ABOUT_STATION, new ShowInformationAboutStationCommand());
        commands.put(CommandName.EDIT_STATION, new EditStationCommand());
        commands.put(CommandName.REMOVE_STATION, new RemoveStationCommand());
        commands.put(CommandName.GO_ADD_STATION, new GoAddStationCommand());
        commands.put(CommandName.GO_EDIT_STATION, new GoEditStationCommand());

        commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
        commands.put(CommandName.REGISTRATION, new RegistrationCommand());
        commands.put(CommandName.ORDER, new OrderCommand());
        commands.put(CommandName.ADD_TO_BLACKLIST, new AddToBlacklistCommand());
        commands.put(CommandName.REMOVE_FROM_BLACKLIST, new RemoveFromBlacklistCommand());
        commands.put(CommandName.CANCEL_ORDER, new CancelOrderCommand());
        commands.put(CommandName.LOG_OUT, new LogOutCommand());
        commands.put(CommandName.CHOOSE_LANGUAGE, new LanguageCommand());
        commands.put(CommandName.SHOW_CORRECT_CLIENTS, new ShowCorrectClientsCommand());
        commands.put(CommandName.SHOW_BLACKLIST, new ShowBlacklistCommand());
        commands.put(CommandName.SHOW_ORDERS, new ShowOrdersOfOneClientCommand());
        commands.put(CommandName.GO_TO_ADMIN_PAGE, new GoToAdminPageCommand());
        commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
    }

    public static CommandManager getInstance() {
        return instance;
    }

    public BasicCommand getCommand(String commandName) {
        CommandName name = CommandName.valueOf(commandName.toUpperCase());
        BasicCommand command;
        if (name != null) {
            command = commands.get(name);
        } else {
            command = commands.get(CommandName.NO_SUCH_COMMAND);
        }
        return command;
    }
}
