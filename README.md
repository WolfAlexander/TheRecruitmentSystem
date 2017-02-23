# The Recruitment System
[![build status](https://git.gortz.org/IV1201/TheRecruitmentSystem/badges/develop/build.svg)](https://git.gortz.org/IV1201/TheRecruitmentSystem/commits/develop)
[![coverage report](https://git.gortz.org/IV1201/TheRecruitmentSystem/badges/develop/coverage.svg)](https://git.gortz.org/IV1201/TheRecruitmentSystem/commits/jobapplication-multilan-feature)

Project in IV1201 Architecture and design of global applications

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
* <b>Config service</b> - host: 9999
* <b>Discovery service</b> - host: 9090
* <b>Edge service</b> - host: 8080

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
