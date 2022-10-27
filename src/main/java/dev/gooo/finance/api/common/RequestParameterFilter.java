package dev.gooo.finance.api.common;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.CaseFormat;

@Component
public class RequestParameterFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Map<String, String[]> requestParamMap = request.getParameterMap().entrySet().stream()
                .collect(Collectors.toMap(e -> CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, e.getKey()),
                        e -> e.getValue()));
        HttpRequestParameterWrapper wrapper = new HttpRequestParameterWrapper(request, requestParamMap);
        filterChain.doFilter(wrapper, response);
    }

    private static class HttpRequestParameterWrapper extends HttpServletRequestWrapper {
        private final Map<String, String[]> requestParams;

        public HttpRequestParameterWrapper(HttpServletRequest request, Map<String, String[]> requestParams) {
            super(request);
            this.requestParams = requestParams;
        }

        @Override
        public String[] getParameterValues(String name) {
            return this.requestParams.get(name);
        }
    }
}
