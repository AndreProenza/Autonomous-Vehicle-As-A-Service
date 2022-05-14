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

## AVaaS participants

The following participants are considered in this project accordingly with the previous core concepts:

![avass_part](https://user-images.githubusercontent.com/78174997/168423748-39b6d5fa-c4a3-4ef1-bc74-c8761e307363.png)

--- 

## AVaaS System of Interest 

The system of interest developed in this project is the following. AVaaS system is responsible to mediate the 
depicted participants accordingly with the use cases described in the use cases section.

![image](https://user-images.githubusercontent.com/78174997/168423860-db74fb48-6409-4cb8-90a4-8468fe0db114.png)

---

## AVaaS Use Cases

The following six use cases are considered in the project:

### User subscribing/unsubscribing to AvaaS

- A user decides to subscribe or unsubscribe the AVaaS IQ, EQ, AQ service. For that purpose, the AVaaS employee 
requests information, validates the user information and then subscribes or unsubscribes the user and informs the 
user about the access to the service.

![image](https://user-images.githubusercontent.com/78174997/168424002-643fc0a2-171f-4f3a-90ed-0d6f840f1f2e.png)

### Car manufacturer entering/removing/updating to AVaaS catalog

- The car manufacturer company can add, remove, or update its cars offer to AVaaS catalog. No validation is performed 
by the AVaaS employees. The car manufacturer is responsible to the quality of the information provided. Adding, 
removing, or updating the catalog represents a topic Kafka provisioning operation

![image](https://user-images.githubusercontent.com/78174997/168424048-885d3ec1-aa74-468e-871b-187874b74af8.png)


### APILOT developer entering/removing/updating to AVaaS catalog

- The APILOT developer company can add, remove, or update its APILOT systems offer to AVaaS catalog. No validation 
is performed by the AVaaS employees. The APILOT developer is responsible to the quality of the information provided.
Adding, removing, or updating the catalog represents a topic Kafka provisioning operation

![image](https://user-images.githubusercontent.com/78174997/168424065-cf877263-ad1a-4c63-863b-93b255f6f2a8.png)

### User buying/selling a car 

- A previous subscribed AVaaS user can buy or sell a car that is previously provided in the catalog by a car manufacturer. 
For that purpose, the AVaaS employee requests information, validates the user information and then assign or unassign 
the car to a AVaaS user and then informs the user and the car manufacturer

![image](https://user-images.githubusercontent.com/78174997/168424101-6bd045cd-0ff4-4a06-8c0c-aedc8cd5fb83.png)

### User selecting/unselecting an APILOT to a car

- A previous subscribed AVaaS user, with a car, can select or unselect a APILOT that is previously provided in the 
catalog by a APILOT developer. For that purpose, the AVaaS employee requests information, validates the user 
information and then assign or unassign the APILOT to a AVaaS user car and then informs the user and the APILOT 
developer.

![image](https://user-images.githubusercontent.com/78174997/168424116-f9930d23-d5ce-4dc6-b5df-81e968e24093.png)

### IQ, EQ, AQ Autonomous driving

- AVaaS is responsible to observe all the relevant events produces by cars, send it to the APILOT and then consume 
the high-level events that can produce IQ, EQ an AQ events. For that end, external services could be triggered

![image](https://user-images.githubusercontent.com/78174997/168424141-1c65c562-f3f6-44b6-9143-e4ffb0ed75d4.png)


---
