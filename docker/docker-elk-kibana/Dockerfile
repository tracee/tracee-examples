FROM deviantony/elk-kibana:latest

MAINTAINER Daniel Wegener "daniel.wegener@holisticon.de"
ADD conf/kibana.yml /opt/kibana/config/kibana.yml

ENTRYPOINT env \
&& sed -i "s/ELASTICSEARCH_ADDR/$ELASTICSEARCH_PORT_9200_TCP_ADDR/g" "/opt/kibana/config/kibana.yml" \
&& sed -i "s/ELASTICSEARCH_PORT/$ELASTICSEARCH_PORT_9200_TCP_PORT/g" "/opt/kibana/config/kibana.yml" \
/entrypoint.sh