# 単体テストの実装方針

`org.junit.jupiter.api.Assertions`を利用してください。
`import static`では`*`を利用せず、利用するメソッドをimportすることを明示してください。
`ParameterizedTest`を積極的に利用してください。
