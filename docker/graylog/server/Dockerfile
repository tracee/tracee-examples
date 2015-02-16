# graylog2-server
#
# VERSION       1.0

FROM dockerfile/java:openjdk-6-jre
MAINTAINER Daniel Wegener "daniel.wegener@holisticon.de"

ENV GL_PKG_NAME graylog2-server-0.92.0
ENV DEBIAN_FRONTEND noninteractive

# Download and install Greylog
RUN cd / && wget -q http://packages.graylog2.org/releases/graylog2-server/$GL_PKG_NAME.tgz \
 && tar xvfz $GL_PKG_NAME.tgz && rm $GL_PKG_NAME.tgz \
 && mv $GL_PKG_NAME graylog2-server

ADD graylog2.conf /etc/

EXPOSE 12900
EXPOSE 12201/udp

ENTRYPOINT export HOST_ADDR=`hostname --ip-address` \
  && sed -i "s/MONGODB_PORT/$MONGO_PORT_27017_TCP_PORT/g" "/etc/graylog2.conf" \
  && sed -i "s/MONGODB_HOST/$MONGO_PORT_27017_TCP_ADDR/g" "/etc/graylog2.conf" \
  && sed -i "s/HOST_ADDR/$HOST_ADDR/g" "/etc/graylog2.conf" \
  && sed -i "s/ELASTICSEARCH_HOST/$ELASTICSEARCH_PORT_9300_TCP_ADDR/g" "/etc/graylog2.conf" \
  && sed -i "s/ELASTICSEARCH_PORT/$ELASTICSEARCH_PORT_9300_TCP_PORT/g" "/etc/graylog2.conf" \
  && env && ifconfig && cd /graylog2-server/ \
  && java -jar graylog2-server.jar
