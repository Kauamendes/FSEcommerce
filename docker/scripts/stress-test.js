import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '10s', target: 20 }, // Ramp-up
    { duration: '20s', target: 50 }, // Stress
    { duration: '10s', target: 0 },  // Ramp-down
  ],
};

// Pega a URL do ambiente ou usa o padrão para Docker no Windows
const BASE_URL = __ENV.BASE_URL || 'http://host.docker.internal:8080';

export function setup() {
  const loginRes = http.post(`${BASE_URL}/auth/login`, JSON.stringify({
    email: 'admin@fashion.com', // Use os e-mails que funcionaram no Cypress
    password: '123'
  }), { headers: { 'Content-Type': 'application/json' } });

  check(loginRes, { 'Login com sucesso': (r) => r.status === 200 });

  return { token: loginRes.json('token') };
}

export default function (data) {
  const params = {
    headers: {
      'Authorization': `Bearer ${data.token}`,
      'Content-Type': 'application/json',
    },
  };

  const res = http.get(`${BASE_URL}/pedidos/usuario-autenticado`, params);

  check(res, {
    'status é 200': (r) => r.status === 200,
    'tempo < 200ms': (r) => r.timings.duration < 200,
  });

  sleep(0.5);
}