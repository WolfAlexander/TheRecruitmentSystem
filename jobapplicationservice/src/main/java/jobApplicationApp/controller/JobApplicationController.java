package jobApplicationApp.controller;

import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.response.RequestListResponse;
import jobApplicationApp.dto.response.RequestResponse;
import jobApplicationApp.entity.ApplicationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Controller for url mapping
 */
@SpringBootApplication
@RestController
@RequestMapping("/")
public class JobApplicationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobApplicationService jobApplicationService;

    /**
     * Get an application by its id
     * @param id of the application
     * @param language of the applications parameters
     * @return an application and a http status or an error message
     */
    @GetMapping(value = "/{language}/by/id/{id}")
    public ResponseEntity getApplicationById(@PathVariable(value = "id") int id,@PathVariable(value = "language") String language){
        try{
            return new ResponseEntity<>(jobApplicationService.getApplicationById(id,language),HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Get applications by parameters
     * @param param is the parameters to filter with
     * @param language of the applications parameters
     * @param bindingResult handles validation of input from user
     * @return collection of application and a http status or an error message
     */
    @PostMapping(value = "/{language}/by/param",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getApplicationsByParam(@Valid @RequestBody ApplicationParamForm param, @PathVariable(value = "language") String language, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new RequestListResponse(errorBindHandler("param",bindingResult)), HttpStatus.BAD_REQUEST);
        }else {
            try {
                return new ResponseEntity<>(jobApplicationService.getApplicationsByParam(param,language),HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * Get an application page, containing a defined amount of applications starting from a defined page number
     * @param pageNmr on page to retrive
     * @param language on applications parameters
     * @param pageSize of a page (in applications)
     * @return collection of application and a http status
     */
    @GetMapping(value = "/{language}/page/{pageSize}/{pageNmr}")
    public ResponseEntity getApplicationsPage(@PathVariable(value = "pageNmr") int pageNmr,@PathVariable(value = "language") String language, @PathVariable(value = "pageSize") int pageSize){
        try{
            return new ResponseEntity<>(jobApplicationService.getApplicationsPage(pageSize, pageNmr,language),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RequestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Register a new job application
     * @param application to register
     * @param language of the new application
     * @param bindingResult  handles validation of input from user
     * @return a message and http status describing if the application was accepted
     */
    @PostMapping(value = "/{language}/",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity registerJobApplication(@Valid @RequestBody ApplicationForm application, BindingResult bindingResult, @PathVariable(value = "language") String language) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RequestListResponse(errorBindHandler("application",bindingResult)),HttpStatus.BAD_REQUEST);
        } else {
            try {
                jobApplicationService.registerJobApplication(application,language);
                return new ResponseEntity<>(new RequestResponse("new application was registered"),HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }


    /**
     * Changes status on an application
     * @param id of application to change status on
     * @param newStatus of the application
     * @param language of the status
     * @param bindingResult handles validation of input from user
     * @return a message and http status describing if the application was accepted
     */
    @PutMapping(value = "/{language}/change/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeStatusOnApplicationById(@PathVariable(value = "id") int id,@Valid @RequestBody ApplicationStatusForm newStatus,@PathVariable(value = "language") String language, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RequestListResponse(errorBindHandler("status",bindingResult)),HttpStatus.BAD_REQUEST);
        } else {
            try {
                jobApplicationService.changeStatusOnApplicationById(id, newStatus, language);
                return new ResponseEntity<String>("New status have been set on application",HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * Get all statuses allowed on an application
     * @param language on the status
     * @return collection of application statuses and a http status
     */
    @GetMapping(value = "/{language}/getAllValidStatus")
    public ResponseEntity getAllValidStatus(@PathVariable(value = "language") String language){
        try {
            return new ResponseEntity<>(jobApplicationService.getAllValidStatus(language),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all competences allowed on an application
     * @param language on the competences
     * @return collection of competences and a http status
     */
    @GetMapping(value = "/{language}/getAllValidCompetences")
    public ResponseEntity getAllValidCompetences(@PathVariable(value = "language") String language){
        try {
            return new ResponseEntity<>(jobApplicationService.getAllValidCompetences(language),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Handle bad binds
     * logs full error to debug and short description to info
     * @param objectName of object that fail to bind
     * @param bindingResult is the binding information object
     * @return list of the error messages to show user what's wrong
     */
    private ArrayList<String>  errorBindHandler(String objectName, BindingResult bindingResult){
        ArrayList<String> errorMessages = new ArrayList<>();
        log.info("non valid " + objectName + " object received");
        StringBuilder error = new StringBuilder();
        bindingResult.getAllErrors().forEach((e) -> {
            errorMessages.add(e.getDefaultMessage());
            error.append(e.getDefaultMessage());
            error.append(" ");
        });
        log.debug(error.toString());
        return errorMessages;
    }

}
