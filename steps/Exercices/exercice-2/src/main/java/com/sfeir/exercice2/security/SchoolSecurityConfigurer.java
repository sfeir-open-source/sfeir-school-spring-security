package com.sfeir.exercice2.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SchoolSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();
        // TODO // 1. L'url /public est accessible par tout le monde (authentifié ou non)

        // TODO // 2. L'url /anonyme est accessible par tout le monde, à condition d'être authentifié

        // TODO // 3. L'url /private n'est accessible que par les utilisateurs authentifiés et ayant un rôle ADMIN ou SFEIR

        // TODO // 4. L'url /forbidden et /access-denied ne sont accessibles par personne

        // TODO // 5.a. Les urls commencant par /admin sont accessibles seulement par les utilisateurs ayant le rôle ADMIN...
        // TODO // 5.b. ... sauf si l'url contient forbidden, dans ce cas elle est inaccessible (ex: admin/a/forbidden/b/c)

        // TODO // 6.a. Les urls commencant par /sfeir sont accessibles par les utilisateurs ayant un rôle ADMIN ou SFEIR...
        // TODO // 6.b. ... sauf si elles commencent par /sfeir/special: elles sont accessibles seulement par les utilisateurs SFEIR mais pas les utilisateurs ADMIN

        // TODO // 7. Les urls contenant les codes us, au, ca ou uk sont visibles par tout le monde (par exemple /resource/us/view ou /uk/a/b/c)

        // TODO // 8. Les resources dont:
        // TODO //    - l'url commence par /resource/sensitive,
        // TODO //    - le chemin a pour taille 3 (exemple: s'applique à /resource/sensitive/exemple mais pas à /resource/sensitive ou à /resource/sensitive/test1/test2)
        // TODO //    - la methode HTTP est POST
        // TODO // ne sont pas accessibles

        // TODO // 9. Toutes les autres requêtes sont accessibles pour les personnes authentifiées
    }
}
