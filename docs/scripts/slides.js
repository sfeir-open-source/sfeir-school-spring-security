// One method per module
function schoolSlides() {
  return ['00-school/00-TITLE.md', '00-school/01-speaker-ajoly.md', '00-school/02-speaker-omar.md', '02-Authorization/05-authorization.md'];
}

function introSlides() {
  return ['intro/00-TITLE.md'];
}

function contentSlides() {
  return ['05-OAuth2/01-oauth2.md', 'exo1'];
}

function closureSlides() {
  return ['intro/00-TITLE.md'];
}

function formation() {
  return [
    //
    ...schoolSlides(), //
    ...introSlides(), //
    ...contentSlides(), //
    ...closureSlides() //
  ].map(slidePath => {
    return {path: slidePath};
  });
}

export function usedSlides() {
  return formation();
}
