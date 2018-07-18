package com.wangzy.webfluxdemo.repository;

import com.wangzy.webfluxdemo.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonRepositoryTest {


    @Autowired
    private PersonRepository personRepository;


    @Test
    public void findByAgeLessThanEqualOrderByAgeAsc() throws Exception {
        Flux<Person> persons = personRepository.findByAgeLessThanEqualOrderByAgeAsc(35);
        persons.toStream().map(u->"Person:"+u).forEach(System.out::println);
        persons.toStream().peek(u -> System.out.println(u)).count();
    }

    @Test
    public void findByCompanyId() throws Exception {
        String companyId = "5b4ef61e9ea8f907ecda50fe";
        Flux<Person> persons = personRepository.findByCompanyId(companyId);
        persons.toStream().map(u->"Person:"+u).forEach(System.out::println);
        System.out.println("111111");
        persons.toStream().peek(u -> System.out.println(u)).count();
        System.out.println("2222222");
    }

    @Test
    public void findByAgeBetween() throws Exception {
        Flux<Person> persons = personRepository.findByAgeBetween(Integer.valueOf(20),Integer.valueOf(35));
        persons.toStream().map(u->"Person:"+u).forEach(System.out::println);
        persons.toStream().peek(u -> System.out.println(u)).count();
    }

    @Test
    public void findByAgeIn() throws Exception {
        Flux<Person> persons = personRepository.findByAgeIn(new ArrayList<Integer>(Arrays.asList(18,30,35)));
        persons.toStream().map(u->"Person:"+u).forEach(System.out::println);
        persons.toStream().peek(u -> System.out.println(u)).count();
    }

    @Test
    public void findByAgeNotIn() throws Exception {
        Flux<Person> persons = personRepository.findByAgeNotIn(new ArrayList<Integer>(Arrays.asList(18,30,35)));
        persons.toStream().map(u->"Person:"+u).forEach(System.out::println);
        persons.toStream().peek(u -> System.out.println(u)).count();
    }

    @Test
    public void findBySalaryBetween() throws Exception {
        Flux<Person> persons = personRepository.findBySalaryBetween(3000,8000);
        persons.toStream().map(u->"Person:"+u).forEach(System.out::println);
        persons.toStream().peek(u -> System.out.println(u)).count();
    }

}