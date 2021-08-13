package com.example.batch_samples.batch0.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    private final JobLauncher jobLauncher;
    private final Job job;

    @Autowired
    public LoadController(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> parameterMap = new HashMap<>();
        parameterMap.put("time", new JobParameter(System.currentTimeMillis()));
        parameterMap.put("name", new JobParameter("Gentjan Kolicaj"));

        JobParameters jobParameters = new JobParameters(parameterMap);
        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        System.out.println("JobExecution : " + jobExecution.getStatus());

        System.out.println("Batch is running ");
        while (jobExecution.isRunning()) {
            System.out.print(".");
        }

        return jobExecution.getStatus();

    }


}
