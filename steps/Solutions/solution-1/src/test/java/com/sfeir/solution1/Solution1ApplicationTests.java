package com.sfeir.solution1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class Solution1ApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void getAnonymousdUser_whenNavigateToPublic_thenDisplayData() throws Exception {
    String contentAsString = mvc.perform(get("/public"))
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();

    assertEquals("Everyone can see this ;)", contentAsString);
  }
  @Test
  void getAuthenticatedUser_whenNavigateToPrivate_thenDisplayData() throws Exception {
    String contentAsString = mvc.perform(get("/private").with(httpBasic("student","student")))
      .andExpect(status().isOk())
      .andReturn().getResponse().getContentAsString();

    assertEquals("Welcome student! Your roles are VISITOR.", contentAsString);
  }

}
