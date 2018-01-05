- start

```
---- Start MQ ----
C:\apache-activemq-5.4.1>bin\activemq
./bin/activemq console

---- Console ----
http://127.0.0.1:8161/admin/
admin/admin

---- Test ----
activemq producer
activemq consumer

---- Connection URI ----
tcp://localhost:61616

```


---
- Java Message Service (JMS)
- availability, performance, scalability, reliability, and securityOB
- message-oriented middleware[M $.
  - ActiveMQ is an open source, Java Message Service (JMS) 1.1–compliant, message-oriented middleware (MOM) from the Apache Software Foundation that provides high availability, performance, scalability, reliability, and security for enterprise messaging. 
 
---

- asynchronous, loosely coupled manner
  - Apache ActiveMQ is one such solution, providing the ability for applications to communicate in an asynchronous, loosely coupled manner. This chapter will introduce you to ActiveMQ.

---

- Remote Procedure Calls (RPC)
  - Loose coupling is commonly introduced into an architecture to mitigate the classic tight coupling of Remote Procedure Calls (RPC). 

---

- Message-oriented middleware (MOM)
  - Message-oriented middleware (MOM) is best described as a category of software for communication in an asynchronous, loosely-coupled, reliable, scalable, and secure manner among distributed applications or systems.
  - MOMs added welcome additional features to enterprise messaging that weren’t previously possible when systems were tightly coupled

---

- persistence, robust
  - it weren't previously possible when systems were tightly coupled - to implement features such as message persistence, robust communication over slow or unreliable connections, complex message routing, message transformation, and much more.

---

- JMS: Java Message Service
  -  It’s an API that abstracts the interaction between messaging clients and MOMs in the same manner that JDBC abstracts communication with relational databases

---

- A simple JMS application will utilize the following steps:
  - Acquire a JMS connection factory
  - Create a JMS connection using the connection factory
  - Start the JMS connection
  - Create a JMS session from the connection
  - Acquire a JMS destination
  - Create JMS producer OR
    - a. Create a JMS producer
    - b. Create a JMS message and address it to a destination
  - Create a JMS consumer
    - a. Create a JMS consumer
    - b. Optionally register a JMS message listener
  - Send or receive JMS message(s)
  - Close all JMS resources(connection, session, producer, consumer, and so forth)

---

- Note that:
  - Note that there’s no concern whether a JMS consumer is on the other end waiting for the message.
  - This mediation of messages between producers and consumers is what MOMs provide and is a big benefit when creating JMS applications.

