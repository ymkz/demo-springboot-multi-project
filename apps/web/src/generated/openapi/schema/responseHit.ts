/**
 * Generated by orval v7.4.1 🍺
 * Do not edit manually.
 * API仕様書
 * OpenAPI spec version: 1.0.0
 */
import type { ResponseHitStatus } from './responseHitStatus'

export interface ResponseHit {
  /** 書籍ID */
  id?: number
  /** ISBN-13 */
  isbn?: string
  /** タイトル */
  title?: string
  /** 価格 */
  price?: number
  /** ステータス */
  status?: ResponseHitStatus
  /** 出版日時:ISO8601 */
  publishedAt?: string
  /** 著者ID */
  authorId?: number
  /** 著者名 */
  authorName?: string
  /** 出版社ID */
  publisherId?: number
  /** 出版社名 */
  publisherName?: string
}
