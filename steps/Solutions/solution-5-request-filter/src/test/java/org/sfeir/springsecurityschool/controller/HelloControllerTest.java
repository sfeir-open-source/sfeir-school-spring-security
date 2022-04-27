package org.sfeir.springsecurityschool.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Sébastien DE SANTIS, SFEIR Benelux
 * @version 1.0
 * @since 4/21/22
 */
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void givenAnAnonymousUser_whenGetHello_thenOk() throws Exception {
    mockMvc.perform(get("/hello"))
      .andExpect(status().isForbidden());
  }
  @Test
  public void givenAnAnonymousUserNamedToto_whenGetHello_thenForbidden() throws Exception {
    mockMvc.perform(get("/hello").header("magic-number", "42"))
      .andExpect(status().isOk());
  }
}
