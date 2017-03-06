package edgeapp.filter;

import com.netflix.zuul.context.RequestContext;
import egdeapp.filter.ReceivedRequestLogging;
import egdeapp.filter.ResponseLogging;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResponseLoggingTest {
    private final ResponseLogging filter = new ResponseLogging();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void checkReceivedRequestLogger(){
        assertEquals("post", filter.filterType());
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
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(response.getStatus()).thenReturn(200);
        when(response.getContentType()).thenReturn("application/json");

        RequestContext context = mock(RequestContext.class);
        when(context.getResponse()).thenReturn(response);
        RequestContext.testSetCurrentContext(context);



        filter.run();
        this.outputCapture.expect(Matchers.containsString("Response with status 200, with content type application/json"));
    }
}
