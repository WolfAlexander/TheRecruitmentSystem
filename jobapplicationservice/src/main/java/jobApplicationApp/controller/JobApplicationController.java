package jobApplicationApp.controller;

import jobApplicationApp.dto.form.ApplicationForm;
import jobApplicationApp.dto.form.ApplicationParamForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JobApplicationService jobApplicationService;

    @GetMapping(value = "/{id}")
    public HttpEntity getApplicationById(@PathVariable(value = "id") int id){
        try{
            return new HttpEntity(jobApplicationService.getApplicationById(id));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    //todo get param
    @PostMapping(value = "/byparam",consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity getApplicationsByParam(@Valid @RequestBody ApplicationParamForm param){
        return new HttpEntity(jobApplicationService.getApplicationsByParam(param));
       /**  try {
            return new HttpEntity(jobApplicationService.getApplicationsByParam(param));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        */
    }

    @GetMapping(value = "/page/{pageSize}/{pageNmr}")
    public HttpEntity getApplicationsPage(@PathVariable(value = "pageNmr") int pageNmr, @PathVariable(value = "pageSize") int pageSize){
        try{
            return new HttpEntity(jobApplicationService.getApplicationsPage(pageSize, pageNmr));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity registerJobApplication(@Valid @RequestBody ApplicationForm application){

        try{
            jobApplicationService.registerJobApplication(application);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            log.info(e.getMessage());
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/change/status/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity changeStatusOnApplicationById(@PathVariable(value = "id") int id, @PathVariable(value = "newStatus") String newstatus){
        try {
            jobApplicationService.changeStatusOnApplicationById(id, newstatus);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
