 _____         _   _         _____             _         
|     |___ ___| |_|_|___ ___|   __|___ ___ _ _|_|___ ___ 
|   --| .'|  _|   | |   | . |__   | -_|  _| | | |  _| -_|
|_____|__,|___|_|_|_|_|_|_  |_____|___|_|  \_/|_|___|___|
                        |___|                            

This service demonstrates a Spring-Boot, Redis-backed service for caching
Account entities.

BUILD the Docker image:

mvn spring-boot:build-image

// Run using Docker

docker network create redis-net

docker run --network redis-net --name redis -p 6379:6379 redis redis-server --requirepass "SUPER_SECRET_PASSWORD"

docker run --network redis-net -p 8080:8080  -e SPRING_REDIS_HOST=redis caching-service:0.0.1-SNAPSHOT


-or-

Run using docker compose

-Caching service with single redis standalone instance

docker compose -f ./caching-redis-standalone.yaml up

-Caching service with Redis  (https://redis.io/topics/cluster-tutorial)

spin up a 6 node redis cluster and configure the caching service to use it

docker compose -f ./caching-redis-cluster.yaml up