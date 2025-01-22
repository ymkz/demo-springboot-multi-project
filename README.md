# demo-springboot-multi-project

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
docker compose exec db mysql local_db -ulocal_user -plocal_pass
```
