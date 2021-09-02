// One method per module
function schoolSlides() {
  return ['00-presentation/00-presentation.md'];
}

function introSlides() {
  return [
    '01-introduction/00-title.md',
    '01-introduction/01-introduction.md'
  ];
}

function definitionsSlides() {
  return [
    '02-definitions/00-title.md',
    '02-definitions/010-security-title.md',
    '02-definitions/011-security.md',
    '02-definitions/020-spring-security-title.md',
    '02-definitions/021-spring-security.md'
  ]
}

function configurationSlides() {
  return [
    '03-configurations/00-title.md',
    '03-configurations/010-project-creation-title.md',
    '03-configurations/011-project-creation.md',
    '03-configurations/020-user-management-title.md',
    '03-configurations/021-user-management.md',
    '03-configurations/030-password-management-title.md',
    '03-configurations/031-password-management.md',
    '03-configurations/040-authentication-provider-title.md',
    '03-configurations/041-authentication-provider.md',
    '03-configurations/050-authentication-manager-title.md',
    '03-configurations/051-authentication-manager.md',
    '03-configurations/060-filters-title.md', // TODO to rename (file and string)
    '03-configurations/061-filters.md', // TODO to rename (file and string)
    '03-configurations/070-security-context-title.md',
    '03-configurations/071-security-context.md',
    '03-configurations/080-form-login-title.md', // TODO to rename (file and string)
    '03-configurations/081-form-login.md', // TODO to rename (file and string)
    '03-configurations/090-access-decision-manager-title.md', // TODO to rename (file and string)
    '03-configurations/091-access-decision-manager.md' // TODO to rename (file and string)
  ]
}

function oauthSlides() {
  return [
    '05-oauth/00-title.md',
    '05-oauth/010-oauth2.md',
    '05-oauth/011-resource-server.md',
    '05-oauth/020-authorization-server.md',
  ]
}

function formation() {
  return [
    ...schoolSlides(),
    ...introSlides(),
    ...definitionsSlides(),
    ...configurationSlides(),
    ...oauthSlides()
  ].map(slidePath => {
    return { path: slidePath };
  });
}

export function usedSlides() {
  return formation();
}
