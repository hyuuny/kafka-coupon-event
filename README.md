# 🧸 Redis 및 Kafka를 활용한 쿠폰 선착순 발급 이벤트 시스템

## 요구사항

선착순 `100명`에게 할인쿠폰을 제공하는 이벤트를 진행하고자 한다.

- 선착순 `100명`에게만 지급되어야 한다.
- `101개` 이상이 지급되면 안된다.
- 순각적으로 몰리는 트래픽을 버틸 수 있어야 한다.

<br>

## 퀵 스타트

### 도커 설치

```shell
brew install docker
brew link docker

docker version
```

### 도커 MySQL 실행 명령어
```shell
docker pull mysql
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=1234 --name mysql mysql
docker ps

-- mysql 접속
docker exec -it mysql bash
```


### MySQL 데이터 베이스 생성 명령어
```shell
mysql -u root -p
create database coupon_event;
use coupon_event;
```

### 도커 Redis 실행 명령어
```shell
docker pull redis
docker run --name myredis -d -p 6379:6379 redis
docker ps

-- 레디스 cli 접속
docker exec -it f8f990128b53(Redis 컨테이너 아이디) redis-cli
INCR coupon_count (숫자를 1씩 증가시키고, 증가된 값을 반환)
flushall (데이터 초기화)
```