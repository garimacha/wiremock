import com.jayway.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * this test class start and stops the server after the execution
 */

public class MockServiceIT extends startService {


    //Before class will ONLY be used if the server is not running explicitly
    @BeforeClass
    public static void startService() {
        server.start();
    }

    @AfterClass
    public static void stopService() {
        server.stop();
    }

    @Test
    public void nabbitResponse() throws IOException {
        //this is calling the stub after the service is started
        getStub();

        Response response = given()
                .queryParams("city", "Redmond",
                             "region", "WA",
                             "country", "US",
                             "postal", "98052")
                .log()
                .all()
                .when()
                .get("http://localhost:8000/api/tax/");

        assertEquals(200, response.getStatusCode());
        assertEquals("0.5", response.jsonPath().getString("totalAmount"));
    }

}
