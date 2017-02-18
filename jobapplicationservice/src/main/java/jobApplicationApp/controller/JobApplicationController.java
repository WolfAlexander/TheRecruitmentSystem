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
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    /**
     * Get an application by its id
     * @param id of the application
     * @return an application and a http status
     */
    @GetMapping(value = "/{lang}/by/id/{id}")
    public ResponseEntity getApplicationById(@PathVariable(value = "id") int id,@PathVariable(value = "lang") String lang){
        try{
            return new ResponseEntity<>(jobApplicationService.getApplicationById(id,lang),HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * get applications by parameters
     * @param param is the parameters to filter with
     * @param bindingResult handles validation of input from user
     * @return collection of application and a http status
     */
    @PostMapping(value = "/{lang}/by/param",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getApplicationsByParam(@Valid @RequestBody ApplicationParamForm param, @PathVariable(value = "lang") String lang, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ArrayList<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach((e)->{errorMessages.add(e.getDefaultMessage());});
            return new ResponseEntity<>(new RequestListResponse(errorMessages), HttpStatus.BAD_REQUEST);
        }else {
            try {
                return new ResponseEntity<>(jobApplicationService.getApplicationsByParam(param,lang),HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * Get an application page, containing a defined amount of applications starting from a defined page number
     * @param pageNmr to retrieve
     * @param pageSize of a page
     * @return collection of application and a http status
     */
    @GetMapping(value = "/{lang}/page/{pageSize}/{pageNmr}")
    public ResponseEntity getApplicationsPage(@PathVariable(value = "pageNmr") int pageNmr,@PathVariable(value = "lang") String lang, @PathVariable(value = "pageSize") int pageSize){
        try{
            return new ResponseEntity<>(jobApplicationService.getApplicationsPage(pageSize, pageNmr,lang),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RequestResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Register a new job application
     * @param application to register
     * @param bindingResult  handles validation of input from user
     * @return a message and http status describing if the application was accepted
     */
    @PostMapping(value = "/{lang}/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity registerJobApplication(@Valid @RequestBody ApplicationForm application,@PathVariable(value = "lang") String lang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ArrayList<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach((e) -> {
                errorMessages.add(e.getDefaultMessage());
            });
            return new ResponseEntity<>(new RequestListResponse(errorMessages),HttpStatus.BAD_REQUEST);
        } else {
            try {
                jobApplicationService.registerJobApplication(application);
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
     * @param bindingResult handles validation of input from user
     * @return a message and http status describing if the application was accepted
     */
    @PutMapping(value = "/{lang}/change/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity changeStatusOnApplicationById(@PathVariable(value = "id") int id,@Valid @RequestBody ApplicationStatusForm newStatus,@PathVariable(value = "lang") String lang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ArrayList<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach((e) -> {
                errorMessages.add(e.getDefaultMessage());
            });
            return new ResponseEntity<>(new RequestListResponse(errorMessages),HttpStatus.BAD_REQUEST);
        } else {
            try {
                jobApplicationService.changeStatusOnApplicationById(id, newStatus);
                return new ResponseEntity<String>(HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.BAD_REQUEST);
            }
        }
    }

    /**
     * Get all statuses allowed on an application
     * @return collection of application statuses and a http status
     */
    @GetMapping(value = "/{lang}/getAllValidStatus")
    public ResponseEntity getAllValidStatus(@PathVariable(value = "lang") String lang){
        try {
            return new ResponseEntity<>(jobApplicationService.getAllValidStatus(lang),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    /**
     * Get all competences allowed on an application
     * @return collection of competences and a http status
     */
    @GetMapping(value = "/{lang}/getAllValidCompetences")
    public ResponseEntity getAllValidCompetences(@PathVariable(value = "lang") String lang){
        try {
            return new ResponseEntity<>(jobApplicationService.getAllValidCompetences(lang),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new RequestResponse(e.getMessage()),HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}
