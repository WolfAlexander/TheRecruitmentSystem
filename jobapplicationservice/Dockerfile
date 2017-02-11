################## BEGIN INSTALLATION ######################
# Install JobApplication service                           #
# Based on Java:8                                          #
############################################################
#MAINTAINER Adrian Gortzak

FROM java:8

# Update the repository sources list
RUN apt-get update -y

#Move application to container
ADD target/jobapplicationservice.jar application.jar

# User to run service
USER 751

# Expose the default port
EXPOSE 8080

# Set default container command
ENTRYPOINT["/usr/lib/jvm/java-8-openjdk-amd64/bin/java", "-jar", "/application.jar"]

##################### INSTALLATION END #####################