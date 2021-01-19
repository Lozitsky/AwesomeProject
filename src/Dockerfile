#FROM alpine
#FROM openjdk:11-jdk-slim
FROM cakhanif/openjdk11-centos

WORKDIR /usr/src/memapp 
COPY com /usr/src/memapp/com

#RUN apk add openjdk8
#ENV JAVA_HOME /usr/lib/jvm/java-1.8-openjdk
#ENV PATH $PATH:$JAVA_HOME/bin

RUN echo $PATH

RUN cd com/kirilo/memory/ && javac *.java

ENTRYPOINT java com/kirilo/memory/MemoryTest
#CMD ["java", "MemoryTest"]
