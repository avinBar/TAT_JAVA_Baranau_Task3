package by.rdtc.library.controller.impl.admin;

import java.util.Map;

import org.apache.log4j.Logger;

import by.rdtc.library.controller.command.Command;
import by.rdtc.library.service.ServiceFactory;
import by.rdtc.library.service.exception.ServiceException;
import by.rdtc.library.service.iface.AdminService;

public class UnbanUser implements Command {
	private static final String LOGIN = "login";
	
	private static final Logger log = Logger.getLogger(UnbanUser.class);
	
	@Override
	public String execute(Map<String,String> params) {
		String login=null;
		
		String response=null;
		
		ServiceFactory sF=ServiceFactory.getInstance();
		AdminService adminService=sF.getAdminService();
		try {
			login=params.get(LOGIN);
			adminService.unbanUser(login);
			response="User "+login+" is unbanned";
		} catch (ServiceException e) {
			log.error(e);
			response="Error during unban procedure";
		}
		return response;
	}
}
