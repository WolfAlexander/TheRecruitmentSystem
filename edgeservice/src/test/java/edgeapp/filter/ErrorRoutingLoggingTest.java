package edgeapp.filter;

import com.netflix.zuul.context.RequestContext;
import egdeapp.filter.ErrorRoutingLogging;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.boot.test.rule.OutputCapture;

import java.util.regex.Matcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Checking init values of the filter and testing log output
 */
public class ErrorRoutingLoggingTest {
    private ErrorRoutingLogging filter = new ErrorRoutingLogging();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Test
    public void checkFilterType(){
        assertEquals("error", filter.filterType());
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
    public void testRun(){
        Throwable throwable = new RuntimeException();

        RequestContext context = mock(RequestContext.class);
        when(context.getThrowable()).thenReturn(throwable);
        RequestContext.testSetCurrentContext(context);

        filter.run();
        outputCapture.expect(Matchers.containsString("Zuul routing exception: " + throwable));
    }
}
