package com.uv.project.shared.domain;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class JwtConverter {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter scopeConverter = new JwtGrantedAuthoritiesConverter();
        scopeConverter.setAuthorityPrefix("SCOPE_");

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> scopes = scopeConverter.convert(jwt);

            // Extraer roles desde realm_access
            Map<String, Object> realmAccess = jwt.getClaim("realm_access");
            Collection<GrantedAuthority> roles = List.of();
            if (realmAccess != null && realmAccess.containsKey("roles")) {
                roles = ((List<?>) realmAccess.get("roles")).stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());
            }

            // Extraer client_id (azp) y agregarlo como autoridad
            String clientId = jwt.getClaimAsString("azp");
            Collection<GrantedAuthority> clientAuthorities = List.of();
            if (clientId != null) {
                clientAuthorities = List.of(new SimpleGrantedAuthority("CLIENT_" + clientId));
            }

            return Stream.of(scopes, roles, clientAuthorities)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        });

        return converter;
    }
}
