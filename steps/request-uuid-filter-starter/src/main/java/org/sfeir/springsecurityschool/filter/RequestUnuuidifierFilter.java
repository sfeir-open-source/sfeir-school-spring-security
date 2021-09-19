package org.sfeir.springsecurityschool.filter;

import lombok.extern.slf4j.Slf4j;
import org.sfeir.springsecurityschool.service.UuidLookUpService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class RequestUnuuidifierFilter extends OncePerRequestFilter {

    private final UuidLookUpService uuidLookUpService;
    private Map<UriTemplate,String> templates;

    public RequestUnuuidifierFilter(UuidLookUpService uuidLookUpService) {
        this.uuidLookUpService = uuidLookUpService;
        templates = new HashMap<>();
        //TODO ajouter les url a traiter
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.debug("Filtering the request with URI {}", httpServletRequest.getRequestURI());

        //TODO ajouter la logique de filtre uuid ici
    }

  /**
   * Retourne l'uri template matchant la requete si possible, à passer au @UuidLookUpService
   * @param requestURI
   * @param verb
   * @return
   */
    private UriTemplate getMatchingUriTemplate(String requestURI,String verb) {
        for (Map.Entry<UriTemplate,String> template : templates.entrySet()) {
            if (template.getKey().matches(requestURI) && template.getValue().matches(verb)) {
                return template.getKey();
            }
        }
        return null;
    }
}
