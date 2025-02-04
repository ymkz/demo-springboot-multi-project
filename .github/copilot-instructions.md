# 単体テストの実装方針

## Javaの場合

`org.junit.jupiter.api.Assertions`を利用してください。
`import static`では`*`を利用せず、利用するメソッドをimportすることを明示してください。
`ParameterizedTest`を積極的に利用してください。

## TypeScriptの場合

`vitest`を利用してください。
`describe`を積極的に利用してください。
`test`を積極的に利用してください。
`expect`を積極的に利用してください。
`test.each`を積極的に利用してください。
