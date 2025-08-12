# データベーススキーマのドキュメントの更新手順

## スキーマを出力して更新する

```sh { name=update-db-document }
REPOSITORY_ROOT=$(git rev-parse --show-toplevel)

tbls doc mysql://demo_user:${DEMODB_DEMOUSER_PASSWORD}@localhost:3306/demo_db "${REPOSITORY_ROOT}/document/database/schema" --force
```

上記コマンドを実行することでスキーマの情報が[document/database/README.md](../database/README.md)に出力されます。
