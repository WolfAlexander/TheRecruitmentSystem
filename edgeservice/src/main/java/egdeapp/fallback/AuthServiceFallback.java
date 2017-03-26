package egdeapp.fallback;

import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hystrix fallback for Authentication Service
 * This class represents a default response to a request to the authentication service
 * when service is unavailable
 */
public class AuthServiceFallback implements ZuulFallbackProvider {
    /**
     * Defines which route in properties this fallback should react to
     * @return name of the route
     */
    @Override
    public String getRoute() {
        return "auth-service";
    }

    /**
     * Defines response when fallback initiated
     * @return custom fallback response
     */
    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.GONE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 410;
            }

            @Override
            public String getStatusText() throws IOException {
                return "GONE";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                JSONObject response = new JSONObject();
                response.put("status", getStatusCode());
                response.put("message", "Authentication service is unavailable!");

                return new ByteArrayInputStream(response.toString().getBytes("utf-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
    }
}
