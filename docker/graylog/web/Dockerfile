# JBoss7 with deployed tracee-examples-jaxws and tracee-examples-ejb
#
# VERSION       1.0

FROM dockerfile/java:openjdk-6-jre
MAINTAINER Daniel Wegener "daniel.wegener@holisticon.de"

ENV GLW_PKG_NAME graylog2-web-interface-0.92.0
ENV DEBIAN_FRONTEND noninteractive

# Download and install Greylog
RUN cd / && wget -q http://packages.graylog2.org/releases/graylog2-web-interface/$GLW_PKG_NAME.tgz \
 && tar xvfz $GLW_PKG_NAME.tgz && rm $GLW_PKG_NAME.tgz \
 && mv $GLW_PKG_NAME graylog2-web-interface

ENV GRAYLOG2_WEB_HOME /graylog2-web-interface
ADD graylog2-web-interface.conf $GRAYLOG2_WEB_HOME/conf/

EXPOSE 9000

# Reduce initial heap space from 1024m default.
ENV JAVA_OPTS -Xms256m

ENTRYPOINT export HOST_ADDR=`hostname --ip-address` \
 && cd /graylog2-web-interface/ \
 && sed -i "s/GRAYLOG_PORT_12900_TCP_ADDR/$GRAYLOG_PORT_12900_TCP_ADDR/g" "/graylog2-web-interface/conf/graylog2-web-interface.conf" \
 && sed -i "s/GRAYLOG_PORT_12900_TCP_PORT/$GRAYLOG_PORT_12900_TCP_PORT/g" "/graylog2-web-interface/conf/graylog2-web-interface.conf" \
 && ./bin/graylog2-web-interface -v
