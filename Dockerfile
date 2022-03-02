FROM docker.artifactory.f-secure.com/openjdk:17

# Remove setuid/setgid binaries
RUN find / -xdev -perm /6000 -type f -exec chmod a-s {} \; || true

EXPOSE 8080
USER 9999

COPY target/equipmentapp-0.0.1-SNAPSHOT.jar /app.jar

CMD exec java -Djava.security.egd=file:/dev/urandom -jar /app.jar
