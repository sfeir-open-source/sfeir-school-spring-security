package com.sfeir.solution3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Solution3ApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  public void getAnonymousUser_whenGetPublic_thenResponseIsOK() throws Exception {
    mvc.perform(get("/public"))
      .andExpect(status().isOk());
  }

  @Test
  public void getAnonymousUser_whenGetPrivate_thenForbidden() throws Exception {
    mvc.perform(get("/private"))
      .andExpect(status().isUnauthorized());
  }

  @Test
  public void getAuthenticatedUser_whenGetPrivate_thenOk() throws Exception{
    String content = mvc.perform(get("/private")
        .with(httpBasic("admin", "admin")))
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();

    assertEquals("Welcome admin!", content);
  }
}
