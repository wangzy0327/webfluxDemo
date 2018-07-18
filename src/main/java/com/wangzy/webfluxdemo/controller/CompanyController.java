package com.wangzy.webfluxdemo.controller;

import com.wangzy.webfluxdemo.model.Company;
import com.wangzy.webfluxdemo.model.Person;
import com.wangzy.webfluxdemo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("")
    public Mono<Company> save(@RequestBody Company company){
        return this.companyService.save(company);
    }

    @GetMapping("")
    public Flux<Company> findAll(){return this.companyService.findAll();}


    @PostMapping("/name")
    public Flux<Company> findByName(@RequestParam String name){
        return this.companyService.findByName(name);
    }

    @GetMapping("/persons")
    public Flux<Person> findPersonsById(@RequestParam String id) {return companyService.findPersonsById(id);}

}
