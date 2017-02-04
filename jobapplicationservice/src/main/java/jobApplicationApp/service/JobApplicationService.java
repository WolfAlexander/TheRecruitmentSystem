package jobApplicationApp.service;

import jobApplicationApp.dao.JobApplicationDao;
import jobApplicationApp.entity.JobApplicationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JobApplicationService {

    @Autowired
    private JobApplicationDao jobApplicationDao;

    public Collection<JobApplicationEntity> getAllApplications() {
        return jobApplicationDao.getAllApplications();
    }
}
