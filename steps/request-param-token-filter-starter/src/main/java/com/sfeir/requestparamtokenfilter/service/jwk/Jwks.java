package com.sfeir.requestparamtokenfilter.service.jwk;

import lombok.Data;

import java.util.List;

@Data
public class Jwks {

  private List<Jwk> keys;
}
