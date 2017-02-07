package jobApplicationApp.controller;

import jobApplicationApp.dto.ApplicationParamForm;
import jobApplicationApp.entity.ApplicationEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;

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
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    //todo get param
    @PostMapping(value = "/byparam")
    public HttpEntity getApplicationByParam(ApplicationParamForm param){
        try {
            return new HttpEntity(jobApplicationService.getApplicationByParam(param));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/page/{id}")
    public HttpEntity getApplicationPage(@PathVariable(value = "id") int id){
        try{
            return new HttpEntity(jobApplicationService.getApplicationPage(id));
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity registerJobApplication(@RequestBody ApplicationEntity application){
        try{
            jobApplicationService.registerJobApplication(application);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/change/status/{id}/{newStatus}")
    public HttpEntity changeStatusOnApplicationById(@PathVariable(value = "id") int id, @PathVariable(value = "newStatus") String newstatus){
        try {
            jobApplicationService.changeStatusOnApplicationById(id, newstatus);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        }catch (Exception e){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }
}
