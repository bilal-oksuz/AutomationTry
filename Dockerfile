FROM    maven:3.6.0-jdk-8
ARG browser
RUN     mkdir /docker
WORKDIR /docker
COPY    pom.xml .
COPY    src .
COPY    src/test ./src/test
COPY    src/test/java ./src/test/java
COPY    src/test/resources ./src/test/resources
COPY    Config-production.yaml ./Config-production.yaml
COPY    Customer.txt ./Customer.txt
COPY    lib ./totallylazy-1.86.jar
RUN     mvn test "-Dbrowser=$browser"