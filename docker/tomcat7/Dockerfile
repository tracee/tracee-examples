# Tomcat7 with deployed tracee-examples-webapp
#
# VERSION       1.0

FROM java:openjdk-6-jre
MAINTAINER Daniel Wegener "daniel.wegener@holisticon.de"

ENV DEBIAN_FRONTEND noninteractive

# Download & install Tomcat 7
RUN cd / && wget -q http://archive.apache.org/dist/tomcat/tomcat-7/v7.0.52/bin/apache-tomcat-7.0.52.tar.gz \
 && tar -xf apache-tomcat-7.0.52.tar.gz && rm apache-tomcat-7.0.52.tar.gz \
 && ln -s apache-tomcat-7.0.52 apache-tomcat \
 && rm -rf /apache-tomcat/webapps/* \
 && rm -rf /apache-tomcat/server/webapps/*

# Enable Tomcat remote debugging
ENV JPDA_ADDRESS 8000
ENV JPDA_TRANSPORT dt_socket
ENV CATALINA_HOME /apache-tomcat-7.0.52

# Adding additional logging support
# disabled ADDS due to docker 0.10 bug #5370

ADD http://archive.apache.org/dist/tomcat/tomcat-7/v7.0.52/bin/extras/tomcat-juli.jar $CATALINA_HOME/bin/
ADD http://archive.apache.org/dist/tomcat/tomcat-7/v7.0.52/bin/extras/tomcat-juli-adapters.jar $CATALINA_HOME/lib/

# Download slf4j and logback
ADD http://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.6.6/slf4j-api-1.6.6.jar $CATALINA_HOME/lib/slf4j-api-1.6.6.jar
ADD http://repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/1.6.6/jul-to-slf4j-1.6.6.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/org/slf4j/log4j-over-slf4j/1.6.6/log4j-over-slf4j-1.6.6.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/1.6.6/jcl-over-slf4j-1.6.6.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.1.2/logback-classic-1.1.2.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/ch/qos/logback/logback-core/1.1.2/logback-core-1.1.2.jar $CATALINA_HOME/lib/

# Add logback logstash appender
ADD http://repo1.maven.org/maven2/net/logstash/logback/logstash-logback-encoder/4.2/logstash-logback-encoder-4.2.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/commons-io/commons-io/2.4/commons-io-2.4.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/commons-lang/commons-lang/2.6/commons-lang-2.6.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/com/lmax/disruptor/3.3.0/disruptor-3.3.0.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.4.0/jackson-databind-2.4.0.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.4.0/jackson-core-2.4.0.jar $CATALINA_HOME/lib/
ADD http://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.4.0/jackson-annotations-2.4.0.jar $CATALINA_HOME/lib/

# Add jax-rs 2 api
ADD http://repo1.maven.org/maven2/javax/ws/rs/javax.ws.rs-api/2.0/javax.ws.rs-api-2.0.jar $CATALINA_HOME/lib/

# Disable jcl
RUN rm /apache-tomcat/conf/logging.properties
# Add logback configuration
ADD logback.xml $CATALINA_HOME/lib/


ENV CATALINA_OPTS -Dlogback.configurationFile=file:$CATALINA_HOME/conf/logback.xml

ADD logback.xml $CATALINA_HOME/conf/
ADD tomcat-users.xml $CATALINA_HOME/conf/

EXPOSE 8000 8080

ENTRYPOINT export CATALINA_OPTS="$CATALINA_OPTS -Xms128m -Xmx256m -DLOGSTASH_SYSLOG_ADDR=logstash -DLOGSTASH_SYSLOG_PORT=12201" \
&& env \
# && ifconfig \
 && /apache-tomcat/bin/catalina.sh run

# Download and install latest tracee snapshot artifacts
ADD tracee-examples-springmvc.war $CATALINA_HOME/webapps/tracee-examples-springmvc.war
ADD tracee-examples-jaxrs2.war $CATALINA_HOME/webapps/tracee-examples-jaxrs2.war
ADD tracee-examples-webapp.war $CATALINA_HOME/webapps/tracee-examples-webapp.war
