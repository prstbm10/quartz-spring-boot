package com.example.quartz.service;

import com.example.quartz.entity.Person;
import com.example.quartz.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzService {
    @Autowired
    private PersonRepository personRepository;


    public void addNewData(Person person) {
        personRepository.save(person);

    }
}
