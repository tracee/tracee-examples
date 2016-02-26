# JBoss7 with deployed tracee-examples-jaxws and tracee-examples-ejb
#
# VERSION       1.0

FROM java:openjdk-6-jre
MAINTAINER Daniel Wegener "daniel.wegener@holisticon.de"

ENV DEBIAN_FRONTEND noninteractive
ENV JBOSS_PKG jboss-as-7.1.1.Final

# Download and install JBoss 7
RUN cd / &&  wget  http://download.jboss.org/jbossas/7.1/jboss-as-7.1.1.Final/$JBOSS_PKG.tar.gz \
  &&  tar -xf $JBOSS_PKG.tar.gz && rm $JBOSS_PKG.tar.gz \
  &&  mv $JBOSS_PKG jboss-as

ENV JBOSS_HOME /jboss-as

# Download logstash-gelf-logging as jboss7 module (see https://github.com/mp911de/logstash-gelf#jboss-7-configuration)
ADD http://repo1.maven.org/maven2/biz/paluch/logging/logstash-gelf/1.6.0/logstash-gelf-1.6.0-logging-module.zip /
RUN unzip /logstash-gelf-1.6.0-logging-module.zip -d / \
  && mv /logstash-gelf-1.6.0/* /jboss-as/modules \
  && rm -rf /logstash-gelf-1.6.0*


ADD standalone-full.xml $JBOSS_HOME/standalone/configuration/
ADD standalone.conf $JBOSS_HOME/bin/

# Add management user to jboss7
RUN $JBOSS_HOME/bin/add-user.sh admin yummie --silent=true

# Download and install latest tracee snapshot artifacts
ADD tracee-examples-ear.ear $JBOSS_HOME/standalone/deployments/tracee-examples.ear

EXPOSE 8080 9990 9999 8787

ENTRYPOINT env && $JBOSS_HOME/bin/standalone.sh --server-config=standalone-full.xml -Djboss.bind.address=0.0.0.0 -Djboss.bind.address.management=0.0.0.0
