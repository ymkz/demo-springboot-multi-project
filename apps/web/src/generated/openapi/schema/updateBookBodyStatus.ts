/**
 * Generated by orval v7.4.1 🍺
 * Do not edit manually.
 * API仕様書
 * OpenAPI spec version: 1.0.0
 */

/**
 * ステータス
 */
export type UpdateBookBodyStatus = (typeof UpdateBookBodyStatus)[keyof typeof UpdateBookBodyStatus]

// eslint-disable-next-line @typescript-eslint/no-redeclare
export const UpdateBookBodyStatus = {
  UNPUBLISHED: 'UNPUBLISHED',
  PUBLISHED: 'PUBLISHED',
  OUT_OF_PRINT: 'OUT_OF_PRINT',
} as const
