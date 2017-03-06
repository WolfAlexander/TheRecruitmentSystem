package edgeapp.filter;

import com.netflix.zuul.context.RequestContext;
import egdeapp.filter.ReceivedRequestLogging;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;
import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Checking init values of the filter and testing log output
 */
public class ReceivedRequestLoggingTest {
    private ReceivedRequestLogging filter = new ReceivedRequestLogging();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void checkReceivedRequestLogger(){
        assertEquals("pre", filter.filterType());
    }

    @Test
    public void checkFilterOrder(){
        assertEquals(filter.filterOrder(), 0);
    }

    @Test
    public void shouldFilterTest(){
        assertTrue(filter.shouldFilter());
    }

    @Test
    public void testRunFilter(){
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getMethod()).thenReturn("GET");
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        when(request.getRequestURI()).thenReturn("/test");

        RequestContext context = mock(RequestContext.class);
        when(context.getRequest()).thenReturn(request);
        RequestContext.testSetCurrentContext(context);

        filter.run();
        this.outputCapture.expect(Matchers.containsString("GET request received from 127.0.0.1 to /test"));
    }
}
