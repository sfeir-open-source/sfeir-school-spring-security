package org.sfeir.springsecurityschool.route;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
public class Route {

  private String method;
  private String uri;
  private String scope;

  public boolean isMethod(String pMethod){
    return StringUtils.equalsIgnoreCase(method,pMethod);
  }

  public boolean isUri(String pUri){
    return StringUtils.equalsIgnoreCase(uri,pUri);
  }
}
