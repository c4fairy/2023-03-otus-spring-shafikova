FROM bellsoft/liberica-openjdk-alpine:17.0.7

ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR
WORKDIR $PROJECT_DIR

COPY --from=0 $PROJECT_DIR/target/SpringDockerApplication.jar $PROJECT_DIR

EXPOSE 8085
CMD ["java","-jar","/opt/project/SpringDockerApplication.jar"]
