package by.rdtc.library.server;

import java.util.concurrent.Callable;

import by.rdtc.library.controller.Controller;

public class Performer implements Callable<String> {
	private  String request;
	
	public Performer(String request){
		this.request=request;
	}
	
	@Override
	public String call() throws Exception {
		Controller controller=Controller.getInstance();
		String response=controller.executeTask(request);
		return response;
	}

}
