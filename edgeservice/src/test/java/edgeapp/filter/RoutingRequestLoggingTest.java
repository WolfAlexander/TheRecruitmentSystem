package edgeapp.filter;

import com.netflix.zuul.context.RequestContext;
import egdeapp.filter.RoutingRequestLogging;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoutingRequestLoggingTest {
    private RoutingRequestLogging filter = new RoutingRequestLogging();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void checkFilterType(){
        assertEquals("routing", filter.filterType());
    }

    @Test
    public void checkFilterOrder(){
        assertEquals(0, filter.filterOrder());
    }

    @Test
    public void shouldFilterTest(){
        assertTrue(filter.shouldFilter());
    }

    @Test
    public void testRunFilter(){
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getScheme()).thenReturn("https");
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        when(request.getRequestURI()).thenReturn("/test");

        RequestContext context = mock(RequestContext.class);
        when(context.getRequest()).thenReturn(request);
        RequestContext.testSetCurrentContext(context);

        filter.run();
        outputCapture.expect(Matchers.containsString("Routing HTTPS request made by 127.0.0.1 to /test"));
    }
}
