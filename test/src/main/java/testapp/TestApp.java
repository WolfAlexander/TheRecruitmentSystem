package testapp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.DatagramSocket;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
    public ValidationResponse registration(@Valid @RequestBody RegistrationForm form, BindingResult bindingResult){
        log.info("Request to /registration received! Form data: " + form);

        log.info(form.getDateOfBirth().toString());

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

    @NotNull
    /*@DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)*/
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 2)
    private String username;

    @NotNull
    @Size(min = 8)
    private String password;
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
