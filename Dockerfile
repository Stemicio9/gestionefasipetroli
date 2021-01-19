FROM openjdk:8
ADD target/gestionefasipetroli-0.0.1.jar gestionefasipetroli-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gestionefasipetroli-0.0.1.jar"]



