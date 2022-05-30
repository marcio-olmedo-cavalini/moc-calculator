package br.com.moc.calculatorrest.filter;

import br.com.moc.calculatorrest.constant.Constants;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UUID uniqueId = UUID.randomUUID();
        MDC.put(Constants.REQUEST_ID, uniqueId.toString());

        log.info("[REST|SESSION-LOG] -{}- Request IP address is {}", uniqueId, servletRequest.getRemoteAddr());
        log.info("[REST|SESSION-LOG] -{}- Request content type is {}", uniqueId, servletRequest.getContentType());

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
                httpServletResponse
        );
        ((HttpServletResponse) servletResponse).addHeader(Constants.REQUEST_ID, uniqueId.toString());
        //filterChain.doFilter(servletRequest, responseWrapper);
        filterChain.doFilter(servletRequest, servletResponse);

        responseWrapper.setHeader(Constants.REQUEST_ID, uniqueId.toString());
        responseWrapper.copyBodyToResponse();
        log.info("[REST|SESSION-LOG] -{}- Response header is set with uuid {}", uniqueId, responseWrapper.getHeader(Constants.REQUEST_ID));
    }
}