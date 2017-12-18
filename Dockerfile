FROM centos:latest

MAINTAINER Deepak Panda - ./build_centos.sh

# Set correct environment variables.
ENV	HOME /root
ENV	LANG en_US.UTF-8
ENV	LC_ALL en_US.UTF-8

RUN yum install -y curl; yum upgrade -y; yum update -y;  yum clean all
RUN yum -y update && yum -y install wget && yum -y install tar
RUN yum install -y wget unzip
RUN yum install -y \
       java-1.8.0-openjdk \
       java-1.8.0-openjdk-devel

ENV JAVA_HOME /usr/java/default
ENV JAVA_OPTS="-Xms64m -Xmx1024m -XX:MaxMetaspaceSize=256m"

# RUN yum remove curl;  yum clean all
# centos-java8U60-ssh
RUN yum -y install openssh-server initscripts
RUN echo "root:#welcome123" | chpasswd
RUN /usr/sbin/sshd-keygen
EXPOSE 22
CMD ["/usr/sbin/sshd", "-D"]

RUN mkdir /opt/helloworld
COPY helloworld-0.0.1-SNAPSHOT.jar /opt/helloworld

WORKDIR /opt/helloworld

EXPOSE 8099
CMD java -jar helloworld-0.0.1-SNAPSHOT.jar
