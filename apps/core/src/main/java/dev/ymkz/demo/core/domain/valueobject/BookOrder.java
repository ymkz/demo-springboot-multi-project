package dev.ymkz.demo.core.domain.valueobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

public enum BookOrder {
  @JsonProperty("+price")
  PRICE_ASC("+price", "価格の昇順"),

  @JsonProperty("-price")
  PRICE_DESC("-price", "価格の降順"),

  @JsonProperty("+published_at")
  PUBLISHED_AT_ASC("+published_at", "出版日の昇順"),

  @JsonProperty("-published_at")
  PUBLISHED_AT_DESC("-published_at", "出版日の降順");

  private final String _value;

  BookOrder(String value, String description) {
    _value = value;
  }

  public static BookOrder fromString(String value) {
    return Arrays.stream(values())
      .filter(order -> order._value.equals(value))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("Invalid BookOrder value: " + value));
  }

  /**
   * SQL用のカラム名とソート順を返す
   *
   * <p>
   * 例: +price -> price ASC, -price -> price DESC
   *
   * <p>
   * MyBatisのmapperがGetterとして認識するため、prefixをgetに必要がある
   */
  public String getOrderBy() {
    var symbol = _value.substring(0, 1);
    var target = _value.substring(1);
    var order = symbol.equals("+") ? "ASC" : "DESC";
    return target + " " + order;
  }
}
