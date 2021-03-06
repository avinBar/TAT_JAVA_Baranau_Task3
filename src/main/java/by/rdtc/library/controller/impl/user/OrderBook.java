package by.rdtc.library.controller.impl.user;


import java.util.Map;

import org.apache.log4j.Logger;

import by.rdtc.library.controller.Controller;
import by.rdtc.library.controller.command.Command;
import by.rdtc.library.service.ServiceFactory;
import by.rdtc.library.service.exception.ServiceException;
import by.rdtc.library.service.iface.OrderService;

public class OrderBook implements Command {
	private static final String ID_USER = "idUser";
	private static final String ID_BOOK="idBook";
	
	private static final Logger log = Logger.getLogger(OrderBook.class);
	
	@Override
	public String execute(Map<String,String> params) {
		int idUser;
		int idBook;
		
		String response=null;
		
		ServiceFactory serviceFactory=ServiceFactory.getInstance();
		OrderService orderService=serviceFactory.getOrderService();
		try {
			idUser=Integer.parseInt(params.get(ID_USER));
			idBook=Integer.parseInt(params.get(ID_BOOK));
			orderService.orderBook(idUser,idBook);
			response="The book is ordered";
		} catch (ServiceException e) {
			log.error(e);
			response="Error during order book procedure";
		}catch (NumberFormatException e) {
			log.error(e);
			response = "Invalid parameters";
		}
		return response;
	}
}
