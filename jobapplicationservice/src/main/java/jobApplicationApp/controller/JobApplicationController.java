package jobApplicationApp.controller;

import jobApplicationApp.dto.ApplicationParam;
import jobApplicationApp.entity.ApplicationEntity;
import jobApplicationApp.entity.ApplicationStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jobApplicationApp.service.JobApplicationService;

import java.util.Collection;

@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ApplicationEntity getRole(@PathVariable(value = "id") int id){
        return jobApplicationService.getApplicationById(id);
    }

    //todo get param
    @RequestMapping(value = "/byparam", method = RequestMethod.GET)
    public Collection<ApplicationEntity> getApplicationByParam(ApplicationParam param){
        return jobApplicationService.getApplicationByParam(param);
    }

    @RequestMapping(value = "/ahundred/from/{index}", method = RequestMethod.GET)
    public Collection<ApplicationEntity> getAHundredApplicationsFrom(@PathVariable(value = "index") int index){
        return jobApplicationService.getAHundredApplicationsFrom(index);
    }

    //todo get application from post
    @RequestMapping(method = RequestMethod.POST)
    public void registerJobApplication(ApplicationEntity application){
        jobApplicationService.registerJobApplication(application);
    }

    //todo get status
    @RequestMapping(value = "/change/status/{id}", method = RequestMethod.PUT)
    public void changeStatusOnApplicationById(@PathVariable(value = "id") int id, ApplicationStatusEntity status){
        jobApplicationService.changeStatusOnApplicationById(id, status);
    }

    //todo get status
    @RequestMapping(value = "/change/status",method = RequestMethod.PUT)
    public void changeStatusOnApplication(ApplicationEntity application, ApplicationStatusEntity status){
        jobApplicationService.changeStatusOnApplication(application, status);
    }

}
