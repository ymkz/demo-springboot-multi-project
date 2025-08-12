package dev.ymkz.demo.core.domain.event;

public enum EventCode {
    DATABASE_ACCESS_FAILED("de1001", "データベースへのアクセスに失敗しました");

    private final String code;
    private final String message;

    EventCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }
}
