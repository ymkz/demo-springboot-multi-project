/**
 * Generated by orval v7.4.1 🍺
 * Do not edit manually.
 * API仕様書
 * OpenAPI spec version: 1.0.0
 */
import type { ProblemDetailProperties } from './problemDetailProperties'

export interface ProblemDetail {
  type?: string
  title?: string
  status?: number
  detail?: string
  instance?: string
  properties?: ProblemDetailProperties
}
