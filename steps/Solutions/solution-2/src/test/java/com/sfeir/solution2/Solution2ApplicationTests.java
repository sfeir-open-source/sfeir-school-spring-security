package com.sfeir.solution2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class Solution2ApplicationTests {

  @Autowired
  private MockMvc mvc;

  // STEP 1
  @Test
  public void getUnAuthentifiedUser_whenGetPublic_thenReturnOK() throws Exception {
    mvc.perform(get("/public"))
      .andExpect(status().isOk());
  }
  // STEP 2
  @Test
  public void getUnAuthentifiedUser_whenGetAnonyme_thenReturnUnAuthorized() throws Exception {
    mvc.perform(get("/anonyme"))
      .andExpect(status().isUnauthorized());
  }
  @Test
  @WithMockUser( username = "student")
  public void getAnonymousUser_whenGetAnonyme_thenReturnOK() throws Exception {
    mvc.perform(get("/anonyme"))
      .andExpect(status().isOk());
  }
  // STEP 3
  @Test
  public void getUnAuthentifiedUser_whenGetPrivate_thenReturnUnAuthorized() throws Exception {
    mvc.perform(get("/private"))
      .andExpect(status().isUnauthorized());
  }
  @Test
  @WithMockUser( username = "anonymous" )
  public void getStudentUser_whenGetPrivate_thenReturnUnAuthorized() throws Exception {
    mvc.perform(get("/private"))
      .andExpect(status().isForbidden());
  }
  @Test
  @WithMockUser( username = "pink_panther", roles = "SFEIR")
  public void getSfeirUser_whenGetPrivate_thenReturnOK() throws Exception {
    mvc.perform(get("/private"))
      .andExpect(status().isOk());
  }
  @Test
  @WithMockUser( username = "god", roles = "ADMIN" )
  public void getAdminUser_whenGetPrivate_thenReturnOK() throws Exception {
    mvc.perform(get("/private"))
      .andExpect(status().isOk());
  }
  // STEP 4
  @Test
  public void getAnonymousUser_whenGetForbiddenOrDenied_thenReturnUnauthorised() throws Exception{
    mvc.perform(get("/forbidden"))
      .andExpect(status().isUnauthorized());
    mvc.perform(get("/access-denied"))
      .andExpect(status().isUnauthorized());
  }
  @Test
  @WithMockUser(username = "toto", roles = "ADMIN")
  public void getAnAdminUser_whenGetForbiddenOrDenied_thenReturnForbidden() throws Exception{
    mvc.perform(get("/forbidden"))
      .andExpect(status().isForbidden());
    mvc.perform(get("/access-denied"))
      .andExpect(status().isForbidden());
  }
  @Test
  @WithMockUser(username = "patoche", roles = "SFEIR")
  public void getASfeirUser_whenGetForbiddenOrDenied_thenReturnForbidden() throws Exception{
    mvc.perform(get("/forbidden"))
      .andExpect(status().isForbidden());
    mvc.perform(get("/access-denied"))
      .andExpect(status().isForbidden());
  }
  // STEP 5
  @Test
  @WithMockUser(username = "Amos", roles = "Roci-crew")
  public void getANonAdminUser_whenGetForbiddenURL_thenReturnForbidden() throws Exception {
    mvc.perform(get("/admin/forbidden/release/protomolecule"))
      .andExpect(status().isForbidden());
    mvc.perform(get("/admin/something"))
      .andExpect(status().isForbidden());
  }
  @Test
  @WithMockUser(username = "Dt Miller", roles = "ADMIN")
  public void getAnAdminUser_whenGetForbiddenURL_thenReturnForbiddenOrNot() throws Exception {
    mvc.perform(get("/admin/forbidden/release/protomolecule"))
      .andExpect(status().isForbidden());
    mvc.perform(get("/admin/something"))
      .andExpect(status().isOk());
  }
  // STEP 6
  @Test
  @WithMockUser(username = "Tom", roles = "SFEIR")
  public void getASfeirUser_whenGetSfeirUrl_ThenReturnOk() throws Exception {
    mvc.perform(get("/sfeir/special"))
      .andExpect(status().isOk());
    mvc.perform((get("/sfeir/something")))
      .andExpect(status().isOk());
  }
  @Test
  @WithMockUser(username = "Harry", roles = "ADMIN")
  public void getAnAdminUser_whenGetSfeirUrl_ThenReturnOkOrNot() throws Exception {
    mvc.perform(get("/sfeir/special"))
      .andExpect(status().isForbidden());
    mvc.perform((get("/sfeir/something")))
      .andExpect(status().isOk());
  }
  // STEP 7
  @Test
  public void getAnonymousUser_whenGetLangUrl_ThenReturnOk() throws Exception{
    mvc.perform(get("/test/ca/dosomething"))
      .andExpect(status().isOk());
    mvc.perform(get("/a/b/uk/dosomething"))
      .andExpect(status().isOk());
  }
  // STEP 8
  @Test
  @WithMockUser(username = "Herlock")
  public void getAUser_whenGetResourceUrl_thenReturnOkOrNot() throws Exception{
    mvc.perform(post("/resource/sensitive/nop"))
      .andExpect(status().isForbidden());
    mvc.perform(post("/resource/sensitive")
        .with(csrf()))
      .andExpect(status().isOk());
  }

}
