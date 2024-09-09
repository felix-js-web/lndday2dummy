# Use a base image with Java installed
FROM azul/zulu-openjdk-alpine:21

ARG JAR_FILE=build/libs/*.jar

ENV APP_HOME=/app
ENV LOG_HOME=$APP_HOME/logs
ENV TARGET_HOME=$APP_HOME/target
ENV JAVA_OPTS=""

# Create a non-root user with specified options
RUN adduser -D -s /sbin/nologin -u 1000 appuser

RUN mkdir -p $APP_HOME $LOG_HOME $TARGET_HOME \
    && chown -R appuser $APP_HOME $LOG_HOME $TARGET_HOME

# Copy the JAR file into the container at /app
COPY $JAR_FILE $TARGET_HOME/app.jar

# Change ownership of the JAR file
RUN chown appuser $TARGET_HOME/app.jar

# Switch to the non-root user
USER appuser

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Specify the command to run your application
ENTRYPOINT [ "sh" , "-c" , "java $JAVA_OPTS -jar $TARGET_HOME/app.jar" ]