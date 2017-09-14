import com.github.tomakehurst.wiremock.WireMockServer;
import wiremock.org.apache.http.HttpStatus;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

/**
 * this the where the service starts on the defined port
 */
public class startService {

    //starting the service on port
    public static final WireMockServer server = new WireMockServer(options().port(8000));
    public static final String URL = "/api/tax/";


    public static void main(String[] args) {

        //forming the request and the response
        getStub();
        //starting the server
        server.start();

        System.out.println("Server started");
        //server.stop();

    }

    public static void getStub() {

        server.stubFor(
                get(urlPathMatching(URL))
                        .withQueryParam("city", equalTo("Redmond"))
                        .withQueryParam("region", equalTo("WA"))
                        .withQueryParam("postal", equalTo("98052"))
                        .withQueryParam("country", equalTo("US"))

                        .willReturn(
                                aResponse() //response has to be the
                                        .withHeader("Content-Type", "application/json")
                                        .withStatus(HttpStatus.SC_OK)
                                        .withBody("{\n" +
                                                          "  \"taxAmount\": 0,\n" +
                                                          "  \"totalAmount\": 0.5,\n" +
                                                          "  \"subTotalAmount\": 0.5\n" +
                                                          "}")
                        ));

    }
}

//do some stuff
//server.stubFor(post(urlEqualTo("/localsave")).willReturn(aResponse().withStatus(HttpStatus.SC_OK)));
