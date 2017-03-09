package jobApplicationApp.controller;


import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.response.RequestListResponse;
import jobApplicationApp.dto.response.RequestResponse;
import jobApplicationApp.service.JobApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Controller for url mapping
 */
@RestController
@RequestMapping("/")
public class JobApplicationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobApplicationService jobApplicationService;

    /**
     * Get an application by id
     * @param id of the application
     * @param language of the application's parameters
     * @return an application and a http status or an error message
     */
    @PreAuthorize("hasRole('APPLICANT')")
    @GetMapping(value = "/{language}/by/id/{id}")
    public ResponseEntity getApplicationById(@PathVariable(value = "id") int id, @PathVariable(value = "language") String language){
        try{
            return new ResponseEntity<>(jobApplicationService.getApplicationById(id,language), HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Get applications by parameters
     * @param param are the parameters to filter with
     * @param language of the applications' parameters
     * @param bindingResult handles validation of input from user
     * @return collection of application and a http status or an error message
     */
    @PreAuthorize("hasRole('USER')")
    @PostMapping(value = "/{language}/by/param",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
     * Get an application page, containing a defined amount of applications starting from a specific page number
     * @param pageNmr of the page to retrieve
     * @param language on applications' parameters
     * @param pageSize of a page (in applications)
     * @return collection of application and a http status
     */
    @PreAuthorize("hasRole('APPLICANT')")
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
     * @param language of the new application's parameters
     * @param bindingResult  handles validation of input from user
     * @return a message and http status describing if the application was accepted
     */
    @PreAuthorize("hasRole('USER')")
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
     * @param id of the application to change the status on
     * @param newStatus of the application
     * @param language of the status
     * @param bindingResult handles validation of input from user
     * @return a message and http status describing if the status change was accepted
     */
    @PreAuthorize("hasRole('USER')")
    @PutMapping(value = "/{language}/change/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeStatusOnApplicationById(@PathVariable(value = "id") int id, @Valid @RequestBody ApplicationStatusForm newStatus, @PathVariable(value = "language") String language, BindingResult bindingResult) {
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
    @PreAuthorize("hasRole('APPLICANT')")
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
    @PreAuthorize("hasRole('USER')")
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
     * logs full error to debug and short description to inform
     * @param objectName of object that fails to bind
     * @param bindingResult is the binding information object
     * @return list of the error messages to show user what's wrong
     */
    private ArrayList<String> errorBindHandler(String objectName, BindingResult bindingResult){
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
