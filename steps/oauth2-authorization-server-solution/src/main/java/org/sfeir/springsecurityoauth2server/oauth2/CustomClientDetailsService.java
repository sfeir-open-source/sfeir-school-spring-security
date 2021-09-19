package org.sfeir.springsecurityoauth2server.oauth2;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * dummy client details service, return always the same client. just there to show how to implement a non in memory config
 */
@Service
public class CustomClientDetailsService implements ClientDetailsService {

    private final Map<String,ClientDetails> clients;

    public CustomClientDetailsService() {
        clients = new HashMap<>();
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("dummy-client");
        // $2y$12$1Fu.bPjFQnCcbUdmicp4SOjnTRaG.HVfAItFTnwj0yVyeeV0PxeCu  ==> dummy-password
        clientDetails.setClientSecret("$2y$12$1Fu.bPjFQnCcbUdmicp4SOjnTRaG.HVfAItFTnwj0yVyeeV0PxeCu");
        clientDetails.setAccessTokenValiditySeconds(600);
        clientDetails.setRefreshTokenValiditySeconds(1200);
        clientDetails.setAuthorizedGrantTypes(Collections.singletonList("client_credentials"));
        clientDetails.setScope(Arrays.asList("read","write"));
        clientDetails.setResourceIds(Collections.singletonList("dummy-api"));
        clientDetails.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("user")));
        //for authorization code grant
        clientDetails.setRegisteredRedirectUri(null);
        clients.put(clientDetails.getClientId(),clientDetails);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (clients.containsKey(clientId)){
            return clients.get(clientId);
        }else{
            throw new NoSuchClientException("No client with id " + clientId);
        }

    }
}
