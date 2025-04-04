/**
 * Generated by orval v7.4.1 🍺
 * Do not edit manually.
 * API仕様書
 * OpenAPI spec version: 1.0.0
 */
import type { CreateBookBodyStatus } from './createBookBodyStatus'

export interface CreateBookBody {
  /** ISBN-13 */
  isbn: string
  /** 書籍タイトル */
  title: string
  /**
   * 価格
   * @minimum 0
   */
  price?: number
  /** ステータス */
  status: CreateBookBodyStatus
  /** 出版日時:ISO8601 */
  publishedAt?: string
}
