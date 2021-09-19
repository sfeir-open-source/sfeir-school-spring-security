package com.sfeir.requestparamtokenfilter.service.jwk;

import lombok.Data;

@Data
public class Jwk {

  private String kty;
  private String alg;
  private String kid;
  private String use;
  private String e;
  private String n;

}
