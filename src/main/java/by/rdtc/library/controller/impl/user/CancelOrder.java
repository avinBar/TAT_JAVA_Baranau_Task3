package by.rdtc.library.controller.impl.user;

import java.util.Map;

import org.apache.log4j.Logger;

import by.rdtc.library.controller.Controller;
import by.rdtc.library.controller.command.Command;
import by.rdtc.library.service.ServiceFactory;
import by.rdtc.library.service.exception.ServiceException;
import by.rdtc.library.service.iface.OrderService;

public class CancelOrder implements Command {
	private static final String ID_ORDER = "idOrder";
	private static final String ID_USER = "idUser";
	
	private static final Logger log = Logger.getLogger(CancelOrder.class);

	@Override
	public String execute(Map<String, String> params) {
		int idUser;
		int idOrder;

		String response = null;


		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		OrderService orderService = serviceFactory.getOrderService();
		try {
			idUser=Integer.parseInt(params.get(ID_USER));
			idOrder =Integer.parseInt(params.get(ID_ORDER));
			orderService.cancelOrder(idUser,idOrder);
			response = "Order is closed successfully";
		} catch (ServiceException e) {
			log.error(e);
			response = "Error during cancel order procedure";
		} catch (NumberFormatException e) {
			log.error(e);
			response = "Invalid parameters";
		}
		return response;
	}

}
