FROM openjdk:7

# create application work directory
RUN mkdir -p /app/bin && mkdir -p /app/conf

COPY target/demo-arch-0.0.1-SNAPSHOT.jar /app/bin
COPY Entrypoint.sh /

ENTRYPOINT ["/bin/bash", "/Entrypoint.sh"]
