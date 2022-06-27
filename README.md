# Autonomous-Vehicle-As-A-Service (AVaaS)

## Introduction

The autonomous vehicle as a service (AVaaS) is a cloud-based system that aims at the integration between car
manufacturers, autopilot developers, users, and other external service providers to provide value added services of
intelligence quotient (IQ), ethical quotient (EQ), and adversity quotient (AQ).

In this sense, AVaaS do not perform auto pilot functions, such as driving a car autonomously, but provides the
IQ, EQ and AQ value added services to customers. The autonomous driving capabilities are usually deployed locally
in the cars, while AVaaS are extended capabilities that could be deployed in the cloud (or hybrid environments).

<img src="https://user-images.githubusercontent.com/78174997/168272522-a4e064a5-9b09-400f-9906-d9615e6c68ef.jpg">

---

# Table of Contents
1. [Introduction](#introduction)
2. [Core Concepts](#core-concepts)
3. [Participants](#participants)
4. [System of Interest](#system-of-interest)
5. [Use Cases](#use-cases)
6. [Architecture](#architecture)
7. [Use Cases Sequence Diagrams](#use-cases-sequence-diagrams) 
8. [CRUD Implementation](#crud-implementation)
9. [Use Cases Implementation](#use-cases-implementation)
10. [Kafka Implementation](#kafka-implementation)
11. [BPMN Files](#bpmn-files)
12. [Used Technologies](#used-technologies)
13. [AVaaS API](#avaas-api)
14. [Requirements](#requirements)
15. [References](#references)
16. [Authors](#authors)

---

## Core concepts 

| Concept                          | Definition                
|:--------------------------------:|:---------------------------------
|Autonomous Vehicle (AV)           | Are cars that employ sensor, computer, machine learning, or artificial intelligence (AI)-based systems to support automated driving decisions regarding steering, changes in speeds, and monitoring the environment. 
|Auto Pilot (APILOT)               | A subset of an AV, including only the machine learning, or artificial intelligence (AI)-based systems to support automated driving decisions regarding steering, changes in speeds, and monitoring the environment.
|Car manufacturer                  | A company that produces a pre-installed AV containing (a) sensors to monitor the environment, (b) computers providing processing power, and (c) steering and speed change capabilities provided to the computers. For example, Toyota, BMW, or other.
|APILOT developer                  |  Company that develops, and then, provides the APILOT service to be deployed in a pré installed AV. For example, Waymo, Aurora, or other.
|External service provider         | A company providing specialized additional services that are mandatory to offer IQ, EQ and AQ.
|AVaaS user                        | The customer of AVaaS that uses the AVaaS provided services
|AVaaS Employee                    | Internal AVaaS resource that oversees AVaaS’ business processes management and execution.

---

## Participants

The following participants are considered in this project accordingly with the previous core concepts:

<img src="https://user-images.githubusercontent.com/78174997/168423748-39b6d5fa-c4a3-4ef1-bc74-c8761e307363.png" width="630" height="320"/>

--- 

## System of Interest 

The system of interest developed in this project is the following. AVaaS system is responsible to mediate the 
depicted participants accordingly with the use cases described in the use cases section.

<img src="https://user-images.githubusercontent.com/78174997/168423860-db74fb48-6409-4cb8-90a4-8468fe0db114.png" width="630" height="320"/>

---

## Use Cases

The following six use cases are considered in the project:

### User subscribing/unsubscribing to AvaaS

- A user decides to subscribe or unsubscribe the AVaaS IQ, EQ, AQ service. For that purpose, the AVaaS employee 
requests information, validates the user information and then subscribes or unsubscribes the user and informs the 
user about the access to the service.

<img src="https://user-images.githubusercontent.com/78174997/168424002-643fc0a2-171f-4f3a-90ed-0d6f840f1f2e.png" width="500" height="270"/>

### Car manufacturer entering/removing/updating to AVaaS catalog

- The car manufacturer company can add, remove, or update its cars offer to AVaaS catalog. No validation is performed 
by the AVaaS employees. The car manufacturer is responsible to the quality of the information provided. Adding, 
removing, or updating the catalog represents a topic Kafka provisioning operation

<img src="https://user-images.githubusercontent.com/78174997/168424715-69e511b3-2dd9-4095-beca-bb59c465a3a8.png" width="500" height="270"/>



### APILOT developer entering/removing/updating to AVaaS catalog

- The APILOT developer company can add, remove, or update its APILOT systems offer to AVaaS catalog. No validation 
is performed by the AVaaS employees. The APILOT developer is responsible to the quality of the information provided.
Adding, removing, or updating the catalog represents a topic Kafka provisioning operation

<img src="https://user-images.githubusercontent.com/78174997/168424065-cf877263-ad1a-4c63-863b-93b255f6f2a8.png" width="500" height="270"/>

### User buying/selling a car 

- A previous subscribed AVaaS user can buy or sell a car that is previously provided in the catalog by a car manufacturer. 
For that purpose, the AVaaS employee requests information, validates the user information and then assign or unassign 
the car to a AVaaS user and then informs the user and the car manufacturer

<img src="https://user-images.githubusercontent.com/78174997/168424101-6bd045cd-0ff4-4a06-8c0c-aedc8cd5fb83.png" width="500" height="270"/>

### User selecting/unselecting an APILOT to a car

- A previous subscribed AVaaS user, with a car, can select or unselect a APILOT that is previously provided in the 
catalog by a APILOT developer. For that purpose, the AVaaS employee requests information, validates the user 
information and then assign or unassign the APILOT to a AVaaS user car and then informs the user and the APILOT 
developer.

<img src="https://user-images.githubusercontent.com/78174997/168424116-f9930d23-d5ce-4dc6-b5df-81e968e24093.png" width="500" height="270"/>

### IQ, EQ, AQ Autonomous driving

- AVaaS is responsible to observe all the relevant events produces by cars, send it to the APILOT and then consume 
the high-level events that can produce IQ, EQ an AQ events. For that end, external services could be triggered

<img src="https://user-images.githubusercontent.com/78174997/168424141-1c65c562-f3f6-44b6-9143-e4ffb0ed75d4.png" width="500" height="270"/>

---

## Architecture

The following diagrams help to better understand the AVaaS system.

In the diagram below you can view in detail the relationship between tables and their attributes.

![image](https://user-images.githubusercontent.com/78174997/176013468-6c522b24-0afe-4a64-b1e7-72adec338663.png)

---

## Use Cases Sequence Diagrams 

To better ilustrate our AVaaS implementation logic, we drew a sequence diagram for each use case. We present them below


#### User subscribing/unsubscribing to AvaaS

<img src="https://user-images.githubusercontent.com/78174997/176016429-4e0ead88-02ac-4be3-8fa5-50841501df33.png" width="520" height="300"/>


#### Car manufacturer entering/removing/updating to AVaaS catalog

<img src="https://user-images.githubusercontent.com/78174997/176017699-57b7b212-a133-4e12-9ad2-7c709cc8c7a7.png" width="520" height="200"/>


#### APILOT developer entering/removing/updating to AVaaS catalog

<img src="https://user-images.githubusercontent.com/78174997/176016583-1962ed1b-8c23-4759-a8ba-2c8dc44870ae.png" width="520" height="200"/>


#### User buying/selling a car

<img src="https://user-images.githubusercontent.com/78174997/176016610-22d4837c-d3eb-4037-950e-a043c0e8bc9b.png" width="520" height="300"/>


#### User selecting/unselecting an APILOT to a car

<img src="https://user-images.githubusercontent.com/78174997/176016646-31ea3895-0fc4-4c47-8ece-2c2440363d5d.png" width="520" height="300"/>


#### IQ, EQ, AQ Autonomous driving

<img src="https://user-images.githubusercontent.com/78174997/176017513-90758b1d-f8aa-4db7-93dc-8a13921bda04.png" width="520" height="300"/>

---

## CRUD Implementation

We implemented the `CRUD` operations for each table shown above in the Architecture diagram.
The implementation was supported by the `Quarkus Java framework`.
The AVaaS system interacts with an already configured `MySQL AWS RDS` database.

The source code is located in the `avaas/` folder.

Run the AVaaS system:

```code
./mvnw quarkus:dev
```

To verify the application's functionality and test the `CRUD` operations, please use
`Swagger UI` which allows to visualize and interact with the API’s resources.

Access this url address: 

[localhost:4080/q/swagger-ui/](https://localhost:4080/q/swagger-ui/)

There you can test the following API resources:
- APilot Resource (CRUD)
- APilot Devloper Resource (CRD)
- AV Resource (CRUD)
- Carmaker Resource (CRD)
- Employee Resource (CRUD)
- User Resource (CRUD)
- Purchase Info Resource (R)  (will be explained ahead)
- Av Kafka Resource (C)       (will be explained ahead)

##### Note: We populated all the tables with some records. We suggest that before testing any CRUD operation, you first verify which records already exist in the tables. You can query all records in the AV table by following these instructions: For example, access the url above, hit "AV Resource", choose the operation "GET /av/all", hit "Try it out", and execute

---

## Use Cases Implementation

We implemented the different use cases using REST endpoints that access the microservices of the AVaaS system. We assume that the "Employee" entity handles the users' requests, validates them, executes the API requests and responds to the client.

We developed 4 microservices:

- User Subscription Service
- Car Manufacturer Service
- APilot Developer Service
- Purchase Service

For the first use case, `User subscribing/unsubscribing to AVaaS`, we developed the 
`User Subscription Service` which presents two endpoints, one for subscribing and the 
other for unsubscribing users to the AVaaS system. The validations are performed by the 
"Employee" entity, which interacts with the AVaaS system

For the second and third use cases, `Car manufacturer entering/removing/updating to 
AVaaS catalog` and `APILOT developer entering/removing/updating to AVaaS catalog`, 
we developed two services: `Car Manufacturer Service` and `APilot Developer Service` in 
which, each of them presents three endpoints, one that inserts an AV, APilot into the 
AVaaS system, another that removes the inserted AV, APilot, and another that modifies 
the AV, APilot model that was inserted previously.

For the third and fourth use cases, `User buying/selling a car` and `User 
selecting/unselecting an APILOT to a car`, we developed a the `Purchase Service` 
that presents four endpoints. Two endpoints are related to buying and selling an AV, the 
other two are related to selecting and deselecting APilots. 

Regarding the two endpoints related to `uying and selling an AV` one endpoint 
associates a user to a given AV on the AVaaS system, representing the purchase of an AV, 
the other removes an AV associated to a user, representing the sale of an AV. 

Regarding the two endpoints related to the `election and deselection of APilots` one of 
them assigns an APilot to a given user and AV, the other deselects an APilot from an AV 
and user.

To verify the application's functionality and test the microservices, please use
`Swagger UI`in the url mentioned previously.

For the last use case, IQ, EQ, AQ Autonomous driving, we will it all detailed below 
in `Kafka Implementation`

---

## Kafka Implementation

To simulate an `AV Manufacturer` introducing a new car model to the AVaaS system, we created an AV producer and consumer high availability mechanism. For that, an `AV Manufacturer` will introduce that data into this resource `Av Kafka Resource`, then the data will be consumed by a kafka topic in `quarkus`. We used the `SmallRye Reactive Messaging` framework supported by `Quarkus` which provides support for `Apache Kafka`.

To execute this mechanism, a Kafka broker is required. To start it, first run locally
zookeeper and kafka.

Open a terminal and run:
```code
# Start the ZooKeeper service
# Note: Soon, ZooKeeper will no longer be required by Apache Kafka.
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

Open another terminal and run:
```code
# Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties
```

##### Note: Apache kafka is required to be installed in your machine.
##### Donwload it here: [Apache Kafka](https://kafka.apache.org/quickstart)

After having executed the previous commands, lets now run the application:

Run the AVaaS system:
```code
./mvnw quarkus:dev
```

To verify the AV producer and consumer functionality, open `Swagger UI` in the
following url address:

[localhost:4080/q/swagger-ui/](https://localhost:4080/q/swagger-ui/)

Access the url above, hit `Av Kafka Resource`, choose the operation `POST /kafka/produce/av`, hit `Try it out`, input some date like the following

```code
 {
  “id”: 20,
  “brand”: “BMW”,
  “model”: i8”
 }
```
and execute.

Check the application’s terminal. The data is being consumed by the application. It
should appear something like this:
```code
Consumed an AV (Autonomous Vehicle): Id = 20 | Brand = BMW | Model = M1
```

You can run the same process via command line:
```code
curl -X 'POST' 'http://localhost:4080/kafka/produce/av' -H 'accept: /' -H
'Content-Type: application/json' -d '{"id": 20,"brand": "BMW","model": "M1"}'
```
To test the producer and consumer mechanism with more data, we created a bash script
`produceAVs.sh` which produces 10 AV's.

Run:
```code
bash produceAVs.sh
```
Check the AV's being consumed in the application terminal.

## IQ, EQ, AQ Autonomous driving

To fulfill the last use case, we used kafka to `consume` data from `topics`, process that data, 
and produce data for another topic to consume until finally a `GUI` can consume the last 
`topic`.

We will explain later how the process works. 
If you are testing the application, please `run the ZooKeeper service` followed by the `Kafka 
broker service`, as shown above.

##### Do this if not already running.

After they are up and running, create the following `topics`:
- av-event
- apilot
- av-result.

These topics will be produced by command line, and the data produced to the topic `av-event` will be produced by the `event producer tool`. 

The topics will have a `replication factor of 1`. We will use `1 partition` for each one of them, 
which means we could have only one 1 Kafka consumer

Create the `av-event` by running:
```code
$ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --
partitions 1 --topic av-event
```

Create the `apilot` by running:
```code
$ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --
partitions 1 --topic apilot
```

Create the `av-result` by running:
```code
$ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --
partitions 1 --topic av-result
```

To list the topics run:
```code
$ bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

Run again the AVaaS system:
```code
./mvnw quarkus:dev
```

Let's now run the producer tool to produce some random AV_Events to the `av-event` topic:

An example of a JSON message produced by the event producer tool:
```code
{"AV_Event":
         {
             "TimeStamp":"2022-02-04 14:49:07.401",
             "AV_ID":"av-event",
             "Speed":"49",
             "BatteryLevel":"10",
             "DriverTirenessLevel":"85",
             "Location":"38.735330392 -9.13666612",
             "EnvironmentalLightning":"Good",
             "RainConditions":"Light Rain",
             "FogConditions":"None",
             "TractionWheelsLevel":"25"
         }
}
```

Find the jar `AVaaSSimulator.jar` located in the `avaas/` folder.

To run the `AV_Events` producer tool:
```code
$ java -jar AVaaSSimulator.jar --broker-list localhost:9092 --throughput 3 --typeMessage 
JSON
```

Now, we will explain the different phases of the use case

For the `Mediation to APILOT`, we want to be able to `mediate between AV events` and 
`APILOT`. We consume each `AV_Event` by processing the content and setting it up to 
produce to the `apilot topic`, which will be useful for the `APilot (simulation) functionality`.

For the `APILOT functionality (simulation)` we want to be able to pilot the AV. For that, we 
process the previously consumed `AV_Event` data and send it to the `apilot` topic, with the 
following restrictions

| Restriction                      | Detail                
|:--------------------------------:|:---------------------------------
|Time Stamp                        |
|AV Id                             |
|Speed                             | if the speed is greater than 50 we apply brakes
|Battery Level                     | if the battery level is lower than 20% an advice is sent to charge the car
|DriverTirednessLevel              | if the driver’s tiredness level is higher than 70% an advice is sent to rest a while
|Location                          | If the location is somewhere unknown an advice is sent as dangerous zone
|EnvironmentalLightning            | If the environmental lightning is N/A or bad an advice is sent as bad space visualization
|RainConditions                    | If the rain conditions is Heavy rain, an weather status advice is sent as bad
|FogConditions                     | If the fog conditions is Dense fog, an weather status advice is sent as bad
|TractionWheelsLevel               | If traction wheels level is lower than 20% an advice is sent av status
|AvStatus                          | If Speed, battery level, driver tiredness level, location, environmental lightening, rain conditions, fog conditions, and traction wheels level follow the above rules, then an advice is sent that the av may continue driving, otherwise and advice is sent to slow down the av

After producing to the `apilot topic`, we will then consume from it and process it. The 
processing aims to `produce and mediate useful IQ, EQ, AQ` data and send `recommendations` to the `av-result` topic. 

We used the `openweathermap` api external service to provide more detailed recommendations.

You can check the api here:

[Open Weather Map](https://openweathermap.org/current)

We used the `av location`, generated by the `AVaaS Simulator producer tool`, and we 
fetched the `weather conditions` at that `location`, given the `latitude` and `longitude`
coordinates. We filtered the weather conditions by the following restrictions:

- `Weather Main`: Group of weather parameters (Rain, Snow, Extreme etc.)
- `Weather Description`: Weather condition within the group

If we get `Weather Main` with value `"Clouds"` or `Weather Description` with value `"broken
clouds"`, then we classify the weather status with `"bad"`. This will influence the 
following actions, regardless of the values we get from `AV_Event`. 

We might for example receive rain conditions generated by `AV_Event` with values `"Light
Rain"` or for example `fog conditions` with value `"N/A"`, which at first would not classify the 
weather status as `"bad"`. However, if the result of the `Weather api` satisfies one of the 
conditions mentioned above, then the `weather status` will be evaluated as `"bad"`.

After getting this `weather api data`, we apply the following restrictions which will influence 
the `IQ, EQ, AQ recommendations`:

| Restriction                      | Detail                
|:--------------------------------:|:---------------------------------
|Time Stamp                        |
|AV Id                             |
|Speed                             | 
|CT Infrastructure                 | We assume that if the rainy conditions are Heavy Rain or if the foggy conditions are Dense Fog, the information and communications technology will be weak, otherwise will be strong.
|RoadConditions                    | We assume that if the weather conditions are bad, then the road conditions will be bad, otherwise will be good.
|Security                          | We assume that if the av location is dangerous, then the security issues will be evaluated as unsafe zone, or safe zone if the opposite is true
|Utilitarianism                    | We assume that if the driver is tired, meaning a high level of tiredness, and he decides to take a rest, then we classify it as a good action that promotes happiness
|Legitimacy                        | We assume that if the driver is speeding above average, and has to apply the brakes, then we classify it as an illegal speed, above the allowable limit. If the driver doesn't need to apply the brakes, then he is driving within the allowed speed
|Social Responsibility             | We assume that if the driver requires a rest, and doesn't actually take it, then we classify the driver as responsible
|Detection                         | We assume that if the driver's space of visualization, influenced by the environment lighting, is poor, we classify it as weak space of visualization
|Identification                    | We assume that if weather conditions are bad, we classify the space as unidentifiable
|Risk Analysis                     | We assume that if the weather conditions are bad and if the av location is dangerous, we classify it as high risk. If the weather conditions are bad or if the av location is dangerous, we classify it as medium risk, otherwise we classify it as low risk
|Reaction                          | We assume that if the weather conditions are bad we classify as reduced detection
|Execution                         | We assume that if Av status shows slow down, then we classify it as slow down, otherwise we classify it as may continue


After the data is `produced` to the `av-result` topic, we can visualize it using a `graphical
user interface`, which displays for each message consumed from the `av-result` topic the 
`IQ, EQ, AQ recommendations`.

We built a springboot project called `apilot`, which listens on the port `5000` and displays the 
`consumed` data from `apilot` and `av-result` topics in different urls.

The source code is located in the `apilot/` folder.

Run the apilot GUI:
```code
mvn spring-boot:run
```

To view the data consumed from the `av-result` topic (which contains the IQ, EQ, AQ recommendations)

Open your browser at the following url:
[localhost:5000/avresult/ui](http://localhost:5000/avresult/ui)

We also created an interface for the apilot to be able to view the consumed advices from the apilot topic. 
To view the data consumed from the `apilot` topic (which contains the APilot advices)

Open your browser at the following url:
[localhost:5000/apilot/ui](http://localhost:5000/apilot/ui)

##### Refresh the page to consume other messages from the topics.

---

## Get Started

To immediately start testing the project on your local machine, (Not recommended before reading the documentation), execute the following steps:


### Run Kafka

Open a terminal and run:
```code
# Start the ZooKeeper service
# Note: Soon, ZooKeeper will no longer be required by Apache Kafka.
$ bin/zookeeper-server-start.sh config/zookeeper.properties
```

Open another terminal and run:
```code
# Start the Kafka broker service
$ bin/kafka-server-start.sh config/server.properties
```

### Create Kafka Topics

Open another terminal and type the following instructions:

Create the `av-event` by running:
```code
$ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --
partitions 1 --topic av-event
```

Create the `apilot` by running:
```code
$ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --
partitions 1 --topic apilot
```

Create the `av-result` by running:
```code
$ bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --
partitions 1 --topic av-result
```

### Run Quarkus AVaaS System

Open another terminal and type:

Run the AVaaS system:
```code
./mvnw quarkus:dev
```

### Run The Producer Tool

Open another terminal and type:

Find the jar `AVaaSSimulator.jar` located in the `avaas/` folder and run:
```code
$ java -jar AVaaSSimulator.jar --broker-list localhost:9092 --throughput 3 --typeMessage 
JSON
```

### Run APilot GUI

The source code is located in the `apilot/` folder.

Run the apilot GUI:
```code
mvn spring-boot:run
```

Open your browser at the following url:
[localhost:5000/avresult/ui](http://localhost:5000/avresult/ui)


Open your another browser tab at the following url:
[localhost:5000/apilot/ui](http://localhost:5000/apilot/ui)

##### Refresh the page to consume other messages from the topics.

---

## BPMN Files

We develop the `BPMN` files for each of the use cases. You can check them in the folder 
`bpmn/`.

---

## Used Technologies

* [Java](https://openjdk.java.net/) - Programming Language;
* [Apache Kafka](https://kafka.apache.org/) - Distributed Event Streaming Platform; Data Integration;
* [Docker](https://www.docker.com/) - Containerization Platform;
* [Quarkus](https://quarkus.io/) - Kubernetes Native Java stack;
* [Camunda](https://camunda.com/) - Java-based framework process engine;  Workflow and Decision Automation Platform;
* [SpringBoot](spring.io/projects/spring-boot) - Java-based Spring Framework to develop web application and microservices 

---

## AVaaS API

### Services

 `User Subscription Service`
  - POST /subscription/subscribe/user
  - DELETE /subscription/unsubscribe/user/{id}
  
 `Car Manufacturer Service`
  - POST /car_manufacturer_service/enter/av
  - DELETE /car_manufacturer_service/remove/av/{id}
  - PUT /car_manufacturer_service/update/av/model/{id}/{model}
  
 `A Pilot Developer Service`
  - POST /apilot_service/enter/apilot
  - DELETE /apilot_service/remove/apilot/{id}
  - PUT /apilot_service/update/apilot/model/{id}/{model}
  
 `Purchase Service`
  - PUT/ purchase_service/apilot/select/{id}/{apilotId}
  - PUT/ purchase_service/apilot/unselect/{id}
  - POST/ purchase_service/av/buy
  - PUT/ purchase_service/av/sell/{id}
  
### Resources

 `A Pilot Resource`
  - POST /apilot
  - GET /apilot/all
  - PUT /apilot/brand/{id}/{brand}
  - PUT /apilot/model/{id}/{model}
  - GET /apilot/{id}
  - DELETE /apilot/{id}

 `A Pilot Developer Resource`
  - POST /apilot_developer
  - GET /apilot_developer/all
  - GET /apilot_developer/{brand}
  - DELETE /apilot_developer/{brand}
  
 `Av Resource`
  - POST /av
  - GET /av/all
  - PUT /av/brand/{id}/{brand}
  - PUT /av/model/{id}/{model}
  - GET /av/{id}
  - DELETE /av/{id}
  
  
`Car Manufacturer Resource`
  - POST /car_manufacturer
  - GET /car_manufacturer/all
  - GET /car_manufacturer/{brand}
  - DELETE /car_manufacturer/{brand}

 `Employee Resource`
  - POST /employee
  - GET /employee/all
  - PUT /employee/role/{userId}/{role}
  - GET /employee/{userId}
  - DELETE /employee/{userId}

 `Av Kafka Resource`
  - POST /kafka/produce/av

 `Purchase Info Resource`
  - GET /purchase/all
  - GET /purchase/{id}

 `User Resource`
  - POST /user
  - PUT /user/age/{id}/{age}
  - GET /user/all
  - PUT /user/name/{id}/{name}
  - GET /user/{id}
  - DELETE /user/{id}

---

## Requirements
- [Apache Kafka](https://kafka.apache.org/quickstart)

---

## References

- [Quarkus](https://quarkus.io/)
- [Quarkus Datasources](https://quarkus.io/guides/datasource)
- [Apache Kafka](https://kafka.apache.org/quickstart)
- [Spring Boot](https://spring.io/projects/spring-boot)

---

## Authors

* **André Proença** - [GitHub](https://github.com/AndreProenza)
* **Eduardo Noronha** - [GitHub](https://github.com/edunoronha633)

---
