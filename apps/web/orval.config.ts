import { defineConfig } from 'orval'

export default defineConfig({
  apifetch: {
    input: {
      target: '../api/spec/openapi.json',
    },
    output: {
      target: './src/generated/openapi/fetch/index.ts',
      schemas: './src/generated/openapi/schema',
      mode: 'single',
      client: 'fetch',
      baseUrl: '${process.env.API_URL}',
      biome: true,
    },
  },
})
