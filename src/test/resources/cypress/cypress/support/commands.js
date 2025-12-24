Cypress.Commands.add('login', (email, senha) => {
  return cy.request({
    method: 'POST',
    url: '/auth/login',
    body: { email, senha }
  }).then(res => res.body.token);
});

Cypress.Commands.add('requestAs', (method, url, token, body = {}) => {
  return cy.request({
    method: method,
    url: url,
    headers: { Authorization: `Bearer ${token}` },
    body: body,
    failOnStatusCode: false
  });
});