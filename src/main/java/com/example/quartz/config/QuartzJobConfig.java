package com.example.quartz.config;

import com.example.quartz.quartz.AddPersonJob;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzJobConfig {
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void runJobDataTransfer(){
        try {
            JobDetail jobDetail = jobDetail();
            Trigger trigger = trigger(jobDetail);
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    private JobDetail jobDetail() {
        return JobBuilder.newJob(AddPersonJob.class)
                .withIdentity("dataTransferJob")
                .storeDurably()
                .build();
    }


    private Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("dataTransferTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();
    }
}
