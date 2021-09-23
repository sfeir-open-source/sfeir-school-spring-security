package org.sfeir.springsecurityschool.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UuidLookUpService {

    public Map<String,Map<String,String>> uuidValues;

    public UuidLookUpService() {
        this.uuidValues = new HashMap<>();
        this.uuidValues.put("bb829bb5-a46a-42f6-bc35-03b942b4ad68",Collections.singletonMap("name","toto"));
    }

    public Optional<String> lookupValue(String uriTemplate, String uuid){
       return Optional.ofNullable(uuidValues.get(uriTemplate)).map(uuidMap -> uuidMap.get(uuid));
    }

    public String addValue(String name, String value){
        String uuid = UUID.randomUUID().toString();
        if (uuidValues.containsKey(uuid)) {
            uuidValues.get(uuid).putIfAbsent(name, value);
        }else{
            uuidValues.put(uuid,Collections.singletonMap(name, value));
        }
        return uuid;
    }


    public URI unuuidify(UriTemplate uriTemplate, String uri) {
        log.debug("unuuidify(uri:'{}')", uri);
        UriTemplate rewritten = new UriTemplate(uriTemplate.toString());
        return uuidValues.keySet().stream().filter(uri::contains).findFirst().map(uuid -> rewritten.expand(uuidValues.get(uuid))).orElse(null);
    }
}
