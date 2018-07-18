package com.wangzy.webfluxdemo.service;

import com.wangzy.webfluxdemo.model.Company;
import com.wangzy.webfluxdemo.model.Person;
import com.wangzy.webfluxdemo.repository.CompanyRepository;
import com.wangzy.webfluxdemo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PersonRepository personRepository;

    public Mono<Company> save(Company company){return companyRepository.save(company);}

    public Flux<Company> findAll() {return companyRepository.findAll();}

    public Flux<Company> findByName(String name){
        return companyRepository.findByName(name);
    }

    public Flux<Person> findPersonsById(String id) {return personRepository.findByCompanyId(id);}

}
