package org.sfeir.springsecurityschool.filter;

import lombok.extern.slf4j.Slf4j;
import org.sfeir.springsecurityschool.service.UuidLookUpService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class RequestUnuuidifierFilter extends OncePerRequestFilter {

    private final UuidLookUpService uuidLookUpService;
    private Map<UriTemplate,String> templates;

    public RequestUnuuidifierFilter(UuidLookUpService uuidLookUpService) {
        this.uuidLookUpService = uuidLookUpService;
        templates = new HashMap<>();
        templates.put(new UriTemplate("/hello/{name}"),"GET");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Filtering the request with URI {}", httpServletRequest.getRequestURI());

        String requestURI = httpServletRequest.getRequestURI();
        log.debug("Trying to translate the request URI {}", requestURI);
        UriTemplate uriTemplate = getMatchingUriTemplate(requestURI,httpServletRequest.getMethod());

        if (uriTemplate == null) {
            log.debug("No matching uri template so we continue the filterChain");
            filterChain.doFilter(httpServletRequest, httpServletResponse);

        } else {
            log.debug("One uri template is matching the uri, so we try to unuuidify the uri");

            URI reWritten = uuidLookUpService.unuuidify(uriTemplate,requestURI);

            if (reWritten != null) {
                httpServletRequest.getRequestDispatcher(reWritten.toString()).forward(httpServletRequest, httpServletResponse);

            } else {
                httpServletResponse.sendError(403);
            }
        }
        log.debug("End of RequestUnuuidifierFilter.doFilter");
    }

    private UriTemplate getMatchingUriTemplate(String requestURI,String verb) {
        for (Map.Entry<UriTemplate,String> template : templates.entrySet()) {
            if (template.getKey().matches(requestURI) && template.getValue().matches(verb)) {
                return template.getKey();
            }
        }
        return null;
    }
}
