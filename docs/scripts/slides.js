// One method per module
function schoolSlides() {
  return [
    '00-presentation/00-presentation.md',
    '00-presentation/01-ajy.md',
    // '00-presentation/02-oci.md',
    '00-presentation/03-mfe.md',
    '00-presentation/90-presentation-participant.md',
    '00-presentation/99-wifi.md'
  ];
}

function introSlides() {
  return [
    '01-introduction/00-title.md',
    '01-introduction/01-introduction.md'
  ];
}

function configurationSlides() {
  return [
    '03-configurations/00-title.md',
    '03-configurations/000-definition-title.md',
    '03-configurations/001-definition.md',
    '03-configurations/010-project-creation-title.md',
    '03-configurations/011-project-creation.md',
    '03-configurations/020-user-management-title.md',
    '03-configurations/021-user-management.md',
    '03-configurations/030-password-management-title.md',
    '03-configurations/031-password-management.md',
    '03-configurations/040-authentication-provider-manager-title.md',
    '03-configurations/041-authentication-provider-manager.md',
    '03-configurations/060-filters-title.md',
    '03-configurations/061-filters.md',
    '03-configurations/070-security-context-title.md',
    '03-configurations/071-security-context.md',
    '03-configurations/080-form-login-title.md',
    '03-configurations/085-dynamic-form-login.md',
  ]
}

function autorisationSlides() {
  return [
    '05-oauth/00-access-decision-manager-title.md',
    '05-oauth/010-access-decision-manager.md',
    '05-oauth/020-title.md',
    '05-oauth/025-oauth2.md',
    '05-oauth/030-oauth2-part2.md',
  ]
}

function formation() {
  return [
    ...schoolSlides(),
    ...introSlides(),
    ...configurationSlides(),
    ...autorisationSlides()
  ].map(slidePath => {
    return { path: slidePath };
  });
}

export function usedSlides() {
  return formation();
}
