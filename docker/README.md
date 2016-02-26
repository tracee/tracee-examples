> This document contains documentation for TracEE examples. Click [here](/README.md) to get an overview that TracEE is about.

# tracee-examples

This folder contains Dockerfiles and a `docker-compose.yml` descriptor for tracee-examples. It starts a preconfigured tomcat7, jbossas7 + ELK-Stack and
deploys tracee-examples-webap and tracee-examples-ear into these containers.

![overview](docker.png?raw=true)

### Start the enviroment

1. Run `mvn install` on the tracee-examples parent project
2. Install [`docker`](https://docs.docker.com/installation/)
3. Install [`docker-compose`](https://docs.docker.com/compose/install/) 1.6.0 or later or newer.
4. Run `docker-compose build` in this directory
5. Run `docker-compose up` in this directory

This will start a service landscape with the following setup:

- An Apache Tomcat7 (tomcat7) instance with three applications using springmvc, jaxrs2 and JSF.
- A JBossAS 7 (jbossas7) instance with an EAR containing a JAX-WS, JMS and Remote-EJB endpoint.
- A Elasticsearch/Logstash/Kibana installation


After starting all services, you may browse the following http endpoints:

 * [http://localhost:8080/tracee-examples-webapp](http://localhost:8080/tracee-examples-webapp) - tracee-examples-webapp on tomcat7
 * [http://localhost:8080/tracee-examples-jaxrs2](http://localhost:8080/tracee-examples-jaxrs2) - tracee-examples-jaxrs2 on tomcat7
 * [http://localhost:8080/tracee-examples-springmvc](http://localhost:8080/tracee-examples-springmvc) - tracee-examples-springmvc on tomcat7
 * [http://localhost:8081/](http://localhost:8081/) - jbossas7
 * [http://localhost:9990/](http://localhost:9990/) - jbossas7 - management interface
 * [http://localhost:5601/](http://localhost:5601/) - Kibana4 Dashboard


_username and password is always admin:yummie_

There are several other open ports:

* tcp://localhost:8000 - tomcat7 remote debugging port
* tcp://localhost:8787 - jbossas7 remote debugging port
* http://localhost:9200 - elasticsearch rest api



