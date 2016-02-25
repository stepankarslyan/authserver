FROM java:7
COPY target/ /home/
COPY model/ /home/model

EXPOSE 8080
CMD ["java", "-jar", "/home/*.jar"]
