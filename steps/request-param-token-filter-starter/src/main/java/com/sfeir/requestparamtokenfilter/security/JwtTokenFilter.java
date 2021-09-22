package com.sfeir.requestparamtokenfilter.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    public static final String TOKEN = "token";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //on check si un token est présent dans l'url
        //pas de token = pas de filtrage
        if (StringUtils.isNotBlank(request.getParameter(TOKEN))) {
            //TODO Créée un object Authentication à partir du token
            //TODO Utiliser notre AuthenticationProvider pour valider cet objet authentication
            //TODO on a créer notre authentification, maintenant a quel composant doit-on la passer pour que spring security soit au courant ?
            //TODO un truc super important a faire pour ne pas casser la chaine de responsabilité
        } else {
            //TODO un truc super important a faire pour ne pas casser la chaine de responsabilité
        }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        //TODO on veut que seulement /hello soit gérer par ce filtre, ajouter la condition nécessaire
    }
}
