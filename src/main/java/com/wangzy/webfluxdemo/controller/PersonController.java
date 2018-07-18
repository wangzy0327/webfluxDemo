package com.wangzy.webfluxdemo.controller;

import com.wangzy.webfluxdemo.model.Location;
import com.wangzy.webfluxdemo.model.Person;
import com.wangzy.webfluxdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("")
    public Mono<Person> save(@RequestBody Person person){
        person.setId(null);
        return this.personService.save(person);
    }

    @GetMapping("")
    public Flux<Person> findAll(){return this.personService.findAll();}

    /**
     * 修改数据 存在的时候返回200 和修改后的数据, 不存在的时候返回404
     *
     * @param id
     * @param person
     * @return
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<Person>> updatePerson(@PathVariable("id") String id,
                                                 @Valid @RequestBody Person person) {
        System.out.println("id:"+id);
        return this.personService.findById(id)
                // flatMap 操作数据
                .flatMap(u -> {
                    u.setAge(person.getAge());
                    u.setName(person.getName());
                    u.setLocations(person.getLocations());
                    u.setSalary(person.getSalary());
                    u.setCompany(person.getCompany());
                    return this.personService.save(u);
                })
                // map: 转换数据
                .map(u -> new ResponseEntity<Person>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据ID查找用户 存在返回用户信息, 不存在返回404
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> findUserById(
            @PathVariable("id") String id) {
        return this.personService.findById(id)
                .map(u -> new ResponseEntity<Person>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据id删除用户 存在的时候返回200, 不存在返回404
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(
            @PathVariable("id") String id) {
        // deletebyID 没有返回值, 不能判断数据是否存在
        // this.personService.deleteById(id)
        return this.personService.findById(id)
                // 当你要操作数据, 并返回一个Mono 这个时候使用flatMap
                // 如果不操作数据, 只是转换数据, 使用map
                .flatMap(user -> this.personService.delete(user).then(
                        Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/name")
    public Flux<Person> findByName(@RequestParam String name){
        return this.personService.findByName(name);
    }

    @PostMapping("/age")
    public Flux<Person> findOrderByAge(@RequestParam Integer age){
        return this.personService.findByAge(age);
    }

    @GetMapping("/sortByAgeAndName")
    public Flux<Person> sortByAgeAndName(){return this.personService.findAllSortByAgeAndName();}

    @PostMapping("/ageBetween")
    public Flux<Person> findByAgeBetween(@RequestParam("start") Integer start,@RequestParam("end") Integer end){
        return this.personService.findByAgeBetween(start,end);
    }

    @PostMapping("/ageIn")
    public Flux<Person> findByAgeIn(@RequestBody  Collection<Person> persons) {
        Collection<Integer> ages = new HashSet<>();
        Iterator<Person> it = persons.iterator();
        while(it.hasNext()){
            ages.add(it.next().getAge());
        }
        return this.personService.findByAgeIn(ages);
    }

    @PostMapping("/salaryBetween")
    public Flux<Person> findSalaryBetween(@RequestParam("from") Double from,@RequestParam("to") Double to){
        return this.personService.findBySalaryBetween(from,to);
    }

    @PostMapping("/ageLessThan")
    public Flux<Person> findByAgeLessThan(@RequestParam Integer age){
        return this.personService.findByAgeLessThan(age);
    }

}
