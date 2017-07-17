package by.rdtc.library.controller;

import java.util.Map;

import by.rdtc.library.bean.User;
import by.rdtc.library.controller.command.Command;
import by.rdtc.library.controller.util.RequestParser;

public final class Controller {
	private static Controller instance;
	private static final String STATUS = "status";
	private static final String GUEST = "guest";
	private final String paramDelimeter = "&";

	private final CommandProvider provider = new CommandProvider();

	public String executeTask(String request) {
		String response;
		String commandName;
		Command executionCommand;
		String type;

		if (!request.contains(paramDelimeter)) {
			response = "Invalid request";
			return response;
		}

		commandName = request.substring(0, request.indexOf(paramDelimeter));
		Map<String, String> paramsMap = RequestParser.getParameters(request);

		if (paramsMap.get(STATUS) == null) {
			type = GUEST;
		} else {
			type = paramsMap.get(STATUS);
		}

		executionCommand = provider.getCommand(type, commandName);

		if (executionCommand == null) {
			response = "Not allowed to execute procedure";
		} else {
			response = executionCommand.execute(paramsMap);
		}
		return response;
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}
}
