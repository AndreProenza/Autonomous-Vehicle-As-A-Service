#!/bin/bash

MSG='Produce Autonomous Vehicle'

#Produce 10 different Autonomous Vehicles (AV's)

echo $MSG ': {"id": 20,"brand": "BMW","model": "M1"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 20,"brand": "BMW","model": "M1"}'

echo $MSG ': {"id": 21,"brand": "Audi","model": "RS3"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 21,"brand": "Audi","model": "RS3"}'

echo $MSG ': {"id": 22,"brand": "Tesla","model": "Model 3"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 22,"brand": "Tesla","model": "Model 3"}'

echo $MSG ': {"id": 23,"brand": "VW","model": "Passat"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 23,"brand": "VW","model": "Passat"}'

echo $MSG ': {"id": 24,"brand": "BMW","model": "i8"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 24,"brand": "BMW","model": "i8"}'

echo $MSG ': {"id": 25,"brand": "BMW","model": "i3"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 25,"brand": "BMW","model": "i3"}'

echo $MSG ': {"id": 26,"brand": "Nissan","model": "Juke"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 26,"brand": "Nissan","model": "Juke"}'

echo $MSG ': {"id": 27,"brand": "Nissan","model": "Leaf"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 27,"brand": "Nissan","model": "Leaf"}'

echo $MSG ': {"id": 28,"brand": "Audi","model": "Q5"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 28,"brand": "Audi","model": "Q5"}'

echo $MSG ': {"id": 29,"brand": "Tesla","model": "Model S"}'
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' \
-H 'accept: */*' -H 'Content-Type: application/json' \
-d '{"id": 29,"brand": "Tesla","model": "Model S"}'

