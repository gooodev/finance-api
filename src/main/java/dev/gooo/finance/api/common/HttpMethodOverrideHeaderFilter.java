package dev.gooo.finance.api.common;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class HttpMethodOverrideHeaderFilter extends OncePerRequestFilter {

    private static final String X_HTTP_METHOD_OVERRIDE_HEADER_KEY = "X-HTTP-Method-Override";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String headerValue = request.getHeader(X_HTTP_METHOD_OVERRIDE_HEADER_KEY);
        if (RequestMethod.POST.name().equals(request.getMethod()) && StringUtils.hasLength(headerValue)) {
            String method = headerValue.toUpperCase(Locale.ENGLISH);
            HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
            filterChain.doFilter(wrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest reqeust, String method) {
            super(reqeust);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }
}
