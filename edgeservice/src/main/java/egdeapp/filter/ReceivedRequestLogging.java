package egdeapp.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Zuul Filter that will log request in "pre" stage
 */
@Component
public class ReceivedRequestLogging extends ZuulFilter {
    private static final Logger log = LoggerFactory.getLogger(ReceivedRequestLogging.class);

    /**
     * Filter type a point on Zuul request/response lifecycle
     * @return type if filter
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * Order of running i relation to other filters
     * @return order number
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * Indicates if filter should run or not
     * @return boolean true if filter enabled and should run, false is disabled
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Task that filter should perform
     * @return
     */
    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        log.info(request.getMethod().toUpperCase() + " request received from " + request.getRemoteAddr() + " to " + request.getRequestURI());

        return null;
    }
}
