package jobApplicationApp.controller;

import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;
import jobApplicationApp.dto.form.ApplicationStatusForm;
import jobApplicationApp.dto.response.RequestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping(value = "/{id}")
    public HttpEntity getApplicationById(@PathVariable(value = "id") int id){
        try{
            return new HttpEntity(jobApplicationService.getApplicationById(id));
        }catch (Exception e){
            return new HttpEntity(new RequestResponse(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }

    @PostMapping(value = "/byparam",consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity getApplicationsByParam(@Valid @RequestBody ApplicationParamForm param, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            ArrayList<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach((e)->{errorMessages.add(e.getDefaultMessage());});
            return new HttpEntity(errorMessages);
        }else {
            try {
                return new HttpEntity(jobApplicationService.getApplicationsByParam(param));
            } catch (Exception e) {
                return new HttpEntity(new RequestResponse(HttpStatus.BAD_REQUEST,e.getMessage()));
            }
        }
    }

    @GetMapping(value = "/page/{pageSize}/{pageNmr}")
    public HttpEntity getApplicationsPage(@PathVariable(value = "pageNmr") int pageNmr, @PathVariable(value = "pageSize") int pageSize){
        try{
            return new HttpEntity(jobApplicationService.getApplicationsPage(pageSize, pageNmr));
        }catch (Exception e){
            return new HttpEntity(new RequestResponse(HttpStatus.BAD_REQUEST,e.getMessage()));
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity registerJobApplication(@Valid @RequestBody ApplicationForm application, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ArrayList<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach((e) -> {
                errorMessages.add(e.getDefaultMessage());
            });
            return new HttpEntity(errorMessages);
        } else {
            try {
                jobApplicationService.registerJobApplication(application);
                return new HttpEntity(new RequestResponse(HttpStatus.ACCEPTED,"new application was registered"));
            } catch (Exception e) {
                return new HttpEntity(new RequestResponse(HttpStatus.BAD_REQUEST,e.getMessage()));
            }
        }
    }

    @PutMapping(value = "/change/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity changeStatusOnApplicationById(@PathVariable(value = "id") int id,@Valid @RequestBody ApplicationStatusForm newStatus, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            ArrayList<String> errorMessages = new ArrayList<>();
            bindingResult.getAllErrors().forEach((e) -> {
                errorMessages.add(e.getDefaultMessage());
            });
            return new HttpEntity(errorMessages);
        } else {
            try {
                jobApplicationService.changeStatusOnApplicationById(id, newStatus);
                return new ResponseEntity<String>(HttpStatus.ACCEPTED);
            } catch (Exception e) {
                return new HttpEntity(new RequestResponse(HttpStatus.BAD_REQUEST,e.getMessage()));
            }
        }
    }

    @GetMapping(value = "/getAllValidStatus")
    public HttpEntity getAllValidStatus(){
        try {
            return new HttpEntity(jobApplicationService.getAllValidStatus());
        }catch (Exception e){
            return new HttpEntity(new RequestResponse(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage()));
        }
    }

    @GetMapping(value = "/getAllValidCompetences")
    public HttpEntity getAllValidCompetences(){
        try {
            return new HttpEntity(jobApplicationService.getAllValidCompetences());
        }catch (Exception e){
            return new HttpEntity(new RequestResponse(HttpStatus.SERVICE_UNAVAILABLE,e.getMessage()));
        }
    }

}
