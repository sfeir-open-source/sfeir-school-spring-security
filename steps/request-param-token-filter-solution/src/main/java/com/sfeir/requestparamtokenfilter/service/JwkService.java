package com.sfeir.requestparamtokenfilter.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwkService {

    public Optional<Jwk> getKey(String keyId){
        //would get the keys from any available provider
        return Optional.empty();
    }

}
