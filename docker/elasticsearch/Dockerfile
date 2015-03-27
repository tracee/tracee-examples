# JBoss7 with deployed tracee-examples-jaxws and tracee-examples-ejb
#
# VERSION       1.0

FROM elasticsearch
MAINTAINER Daniel Wegener "daniel.wegener@holisticon.de"

ADD elasticsearch.yml /elasticsearch/config/elasticsearch.yml

CMD []
ENTRYPOINT export HOST_ADDR=`hostname --ip-address` \
	&& sed -i "s/HOSTNAME/$HOSTNAME/g" /elasticsearch/config/elasticsearch.yml \
	&& /elasticsearch/bin/elasticsearch
