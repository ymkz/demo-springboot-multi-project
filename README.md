# demo-springboot-multi-project

## Documents

- [データベース](./docs/database/README.md)

## Tasks

### clean

```sh { name=clean }
find . -name '.gradle' -type d -prune -exec rm -rf '{}' +
find . -name 'build' -type d -prune -exec rm -rf '{}' +
find . -name 'coverage' -type d -prune -exec rm -rf '{}' +
find . -name 'dist' -type d -prune -exec rm -rf '{}' +
find . -name 'node_modules' -type d -prune -exec rm -rf '{}' +
find . -name 'pnpm-lock.yaml' -type f -prune -exec rm -rf '{}' +
find . -name 'tsconfig.tsbuildinfo' -type f -prune -exec rm -rf '{}' +
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

### gen-db-docs

```sh { name=gen-db-docs }
tbls doc mysql://app:app@localhost:3306/demo docs/database/schema --force
```
