FROM eclipse-temurin:21@sha256:952185109e6790588902d524bd43fcf5f478e022847097ef9ae8ef5b82511bbd
WORKDIR /opt/app
RUN addgroup --system javauser && adduser -S -s /usr/sbin/nologin -G javauser javauser
COPY target/*.jar app.jar
RUN chown -R javauser:javauser .
USER javauser
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
