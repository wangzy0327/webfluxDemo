package com.wangzy.webfluxdemo.service;

import com.wangzy.webfluxdemo.model.Person;
import com.wangzy.webfluxdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Mono<Person> save(Person person){return personRepository.save(person);}

    public Flux<Person> findAll() {return personRepository.findAll();}

    public Mono<Person> findById(String id) {return personRepository.findById(id);}

    public Flux<Person> findByName(String name){
        return personRepository.findByName(name);
    }

    public Flux<Person> findByAge(Integer age){
        return personRepository.withQueryFindByAge(age);
    }

    public Flux<Person> findAllSortByAgeAndName() {return personRepository.findAll(sortByAgeAndName());}

    public Flux<Person> findByAgeBetween(Integer start,Integer end) {return personRepository.findByAgeBetween(start,end);}

    public Flux<Person> findByAgeLessThan(Integer age) {return personRepository.findByAgeLessThanEqualOrderByAgeAsc(age);}

    public Flux<Person> findByAgeIn(Collection<Integer> ages){return personRepository.findByAgeIn(ages);}

    public Flux<Person> findBySalaryBetween(Double from,Double to){return personRepository.findBySalaryBetween(from,to);}

    public Sort sortByAgeAndName(){
        return new Sort(Sort.Direction.ASC,"age").and(new Sort(Sort.Direction.ASC,"name"));
    }

    public Mono<Void> delete(Person person) {
        return personRepository.delete(person);
    }
}
