package com.example.quartz.quartz;

import com.example.quartz.entity.Person;
import com.example.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class AddPersonJob implements Job {
    @Autowired
    private QuartzService service;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("START");
        Person person = new Person();
        person.setAddress("jakarta");
        person.setName("test name");
        person.setCreatedAt(LocalDateTime.now());

        service.addNewData(person);
        log.info("DONE");
    }
}
