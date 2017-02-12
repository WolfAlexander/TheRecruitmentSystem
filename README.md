# The Recruitment System
One Paragraph of project description goes here

## Getting Started
todo

## Deployment & Installing

1. Have docker installed on the machine
2. Open terminal and go to shell_scripts directory
3. 
    - For Docker Toolbox/Windows without Hyper V
        1. In build-services-docker-toolbox.sh enter your specific DOCKER_HOST and DOCKER_CERT_PATH
        2. Run the script
    - For MacOS/Linux:
        1. Run build-service-docker-unix.sh
4. Run start-config-service.sh - it has to be started before other services
5. Run start-other-services.sh

#### Services
Config service - host:9999
Discovery service - host:9090
Edge service - host 8080

## Running the tests
todo

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management


## Authors

* **Adrian Gortzak**  
* **Albin Friedner**  
* **Alexander Nikalayeu** 


## License

todo

## Acknowledgments

* todo
