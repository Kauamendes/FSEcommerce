import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  stages: [
    { duration: '10s', target: 20 }, // Ramp-up
    { duration: '20s', target: 50 }, // Stress
    { duration: '10s', target: 0 },  // Ramp-down
  ],
};

// Obtém o token antes de iniciar os usuários virtuais
export function setup() {
  const loginRes = http.post('http://localhost:8080/auth/login', JSON.stringify({
    email: 'admin@tenantA.com',
    password: '123'
  }), { headers: { 'Content-Type': 'application/json' } });

  return { token: loginRes.json('token') };
}

export default function (data) {
  const params = {
    headers: {
      'Authorization': `Bearer ${data.token}`,
      'Content-Type': 'application/json',
    },
  };

  // Testa o isolamento na busca de pedidos do usuário autenticado
  const res = http.get('http://localhost:8080/pedidos/usuario-autenticado', params);

  check(res, {
    'status é 200': (r) => r.status === 200,
    'dados pertencem ao tenant': (r) => r.json().every(p => p.usuarioId !== undefined),
    'tempo < 200ms': (r) => r.timings.duration < 200,
  });

  sleep(0.5);
}