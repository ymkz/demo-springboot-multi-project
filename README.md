# demo-springboot-multi-project

## TODO

- application.yaml の設定
- profile の分割（local を用意する）
- build.gradle の共通部分をルートに移譲
- フォーマッタを整備

## Tasks

### clean

```sh { name=clean }
find . -name '.gradle' -type d -prune -exec rm -rf '{}' +
find . -name 'build' -type d -prune -exec rm -rf '{}' +
```

### up

```sh { name=up }
docker compose up -d --wait --wait-timeout=60
```

### down

```sh { name=down }
docker compose down
```

### exec-mysql

```sh { name=exec-mysql }
docker compose exec demo-db mysql demo -uapp -papp
```
