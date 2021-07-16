FROM openjdk:11
EXPOSE 8080
VOLUME /tmp
COPY build/libs/*.jar build/libs/*app.jar
COPY META-INF/MANIFEST.MF META-INF/MANIFEST.MF
ENTRYPOINT ["java","-jar","build/libs/*app.jar"]