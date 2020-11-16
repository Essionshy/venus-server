FROM java:8

EXPOSE 9001
EXPOSE 11098

VOLUME /tmp
ADD venus-server.jar /venus.jar
RUN bash -c 'touch /venus.jar'
ENTRYPOINT ["java","-jar","venus.jar"]