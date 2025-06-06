/**
 * Generated by orval v7.4.1 🍺
 * Do not edit manually.
 * API仕様書
 * OpenAPI spec version: 1.0.0
 */
import type { UpdateBookBodyStatus } from './updateBookBodyStatus'

export interface UpdateBookBody {
  /** ISBN-13 */
  isbn?: string
  /** 書籍タイトル */
  title?: string
  /**
   * 価格
   * @minimum 0
   */
  price?: number
  /** ステータス */
  status?: UpdateBookBodyStatus
  /** 出版日時:ISO8601 */
  publishedAt?: string
}
