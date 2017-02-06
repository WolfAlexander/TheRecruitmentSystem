package jobApplicationApp.controller;

import jobApplicationApp.dto.ApplicationParam;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;

import java.util.Collection;

@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApplicationEntity getApplicationById(@PathVariable(value = "id") int id){
        return jobApplicationService.getApplicationById(id);
    }

    //todo get param
    @RequestMapping(value = "/byparam", method = RequestMethod.POST)
    public Collection<ApplicationEntity> getApplicationByParam(ApplicationParam param){
        return jobApplicationService.getApplicationByParam(param);
    }

    @RequestMapping(value = "/page/{id}", method = RequestMethod.GET)
    public Collection<ApplicationEntity> getApplicationPage(@PathVariable(value = "id") int id){
        return jobApplicationService.getApplicationPage(id);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void registerJobApplication(@RequestBody ApplicationEntity application){
        jobApplicationService.registerJobApplication(application);
    }

    //todo get status
    @RequestMapping(value = "/change/status/{id}", method = RequestMethod.PUT)
    public void changeStatusOnApplicationById(@PathVariable(value = "id") int id, ApplicationStatusEntity status){
        jobApplicationService.changeStatusOnApplicationById(id, status);
    }


    @RequestMapping(value = "/change/status",method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void changeStatusOnApplication(@RequestBody ApplicationEntity application){
        jobApplicationService.changeStatusOnApplication(application);
    }

}
