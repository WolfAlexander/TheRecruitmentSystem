package testapp;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class TestApp {
    private static Logger log = LoggerFactory.getLogger(TestApp.class);

    public static void main(String[] args) {
        SpringApplication.run(TestApp.class, args);
    }

    @GetMapping("/testmapping" )
    public ResponseEntity<String> testMapping(){
        log.info("Request to /testmapping received!");
        return new ResponseEntity<>("I am working!", HttpStatus.OK);
    }

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ValidationResponse registration(@Valid RegistrationForm form, BindingResult bindingResult){
        log.info("Request to /registration received! Form data: " + form);

        if(bindingResult.hasErrors()) {
            log.warn("Error in the form!");
            return new ValidationResponse(HttpStatus.BAD_REQUEST, bindingResult.getFieldErrors());
        }else {
            log.info("No errors in form!");
            return new ValidationResponse(HttpStatus.CREATED);
        }
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class RegistrationForm{
    @NotNull
    @Size(min = 2)
    private String firstname;

    @NotNull
    @Size(min = 3)
    private String lastname;
}

@Getter
@Setter
@NoArgsConstructor
class ValidationResponse{
    private HttpStatus status;
    private List errorList;

    public ValidationResponse(HttpStatus status) {
        this.status = status;
    }

    public ValidationResponse(HttpStatus status, List errorList) {
        this.status = status;
        this.errorList = errorList;
    }
}
