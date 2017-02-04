package jobApplicationApp.controller;

import jobApplicationApp.entity.JobApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import jobApplicationApp.service.JobApplicationService;

import java.util.Collection;

@RestController
@RequestMapping("/jobapplication")
public class JobApplicationController {

    @Autowired
    private JobApplicationService jobApplicationService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<JobApplicationEntity> getAllApplications(){
        return jobApplicationService.getAllApplications();
    }

}
