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
9. [Used Technologies](#used-technologies)
10. [AVaaS API](#avaas-api)
11. [References](#references)
12. [Authors](#authors)

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

##### Note: We populated all the tables with some records. We suggest that before testing any CRUD operation, you first verify which records already exist in the tables. You can query all records in the AV table by following these instructions: Access the url above, hit "AV Resource", choose the operation "GET /av/all", hit "Try it out", and execute

---

## Used Technologies

* [Java](https://openjdk.java.net/) - Programming Language;
* [Apache Kafka](https://kafka.apache.org/) - Distributed Event Streaming Platform; Data Integration;
* [Docker](https://www.docker.com/) - Containerization Platform;
* [Quarkus](https://quarkus.io/) - Kubernetes Native Java stack;
* [Camunda](https://camunda.com/) - Java-based framework process engine;  Workflow and Decision Automation Platform;

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
