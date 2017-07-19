package by.rdtc.library.server;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ServerTest {
	private Server server;

	@Test(dataProvider = "dataForServerTest")
	public void tstServerGetResponse(String request, String expResponse) {
		String response = server.getResponse(request);
		Assert.assertEquals(response, expResponse);
	}

	@DataProvider(parallel = true)
	public Object[][] dataForServerTest() {
		return new Object[][] { new Object[] { "sign_in&login=donald44&password=Me12345", "Welcome donald44" },
				new Object[] { "register&login=alfred&password=12345Va&name=Jack&surname=Forester",
						"Successful registration" },
				new Object[] { "show_profile&id=8&status=user",
						"login=orderuser\nname=EDITED\nsurname=EDITED\ntype=user" },
				new Object[] { "add_book&title=WorldWide&author=Gibbons&idUser=1&status=admin", "Welcome kuzorka" },
				new Object[] { "ban_user&idUser=1&status=admin&login=kuzorka", "User kuzorka is banned" },
				new Object[] { "delivery_order&idUser=1&status=admin&idOrder=1", "Book is delivered" }, };
	}

	@BeforeClass
	public void beforeClass() {
		server = Server.getInstance();
	}

	@AfterClass
	public void afterClass() {
		server = null;
	}
}
