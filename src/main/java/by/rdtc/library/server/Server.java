package by.rdtc.library.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Server {
	private static Server instance;
	private final static int THREAD_COUNT = 5;
	private final ExecutorService exService = Executors.newFixedThreadPool(THREAD_COUNT);

	private static final Logger log = Logger.getLogger(Server.class);

	private Server() {

	}

	public static Server getInstance() {
		if (instance == null) {
			instance = new Server();
		}
		return instance;
	}

	public String getResponse(String request) {
		String response ="";
		try {
			Future<String> future = exService.submit(new Performer(request));
			response=future.get();
		} catch (InterruptedException e) {
			log.error(e);
		}
		return response;
	}
}
