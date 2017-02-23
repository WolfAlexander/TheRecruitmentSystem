package egdeapp.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RoutingLogFilter extends ZuulFilter {
    private final Logger log = LoggerFactory.getLogger(RoutingLogFilter.class);

    @Override
    public String filterType() {
        return "routing";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();

        log.info("REQUEST: " + request.getScheme().toUpperCase() + " request made by " + request.getRemoteAddr()+ " routed to " + request.getRequestURI());

        return null;
    }
}
