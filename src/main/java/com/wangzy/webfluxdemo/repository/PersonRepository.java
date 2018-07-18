package com.wangzy.webfluxdemo.repository;

import com.wangzy.webfluxdemo.model.Location;
import com.wangzy.webfluxdemo.model.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

public interface PersonRepository  extends ReactiveMongoRepository<Person,String> {

    /**
     * 支持方法名查询
     * @param name
     * @return
     */
    Flux<Person> findByName(String name);

    /**
     * 支持@Query查询，查询参数构造JSON字符串即可
     * @param age
     * @return
     */
    @Query("{'age': ?0}")
    Flux<Person> withQueryFindByAge(Integer age);


    /**
     *
     * @param start
     * @param end
     * @return
     */
//    @Query(value = "{'age':{'$gt':?0,'$lt':?1}}")
    Flux<Person> findByAgeBetween(int start,int end);

    Flux<Person> findByAgeIn(Collection<Integer> ages);

    Flux<Person> findByAgeNotIn(Collection<Integer> ages);

    @Query(value = "{'age':{'$lte':?0}}")
    Flux<Person> findByAgeLessThanEqualOrderByAgeAsc(Integer age);

    /**
     * 按照自定义排序获取所有数据
     * @param sort
     * @return
     */
    @Override
    Flux<Person> findAll(Sort sort);

    Flux<Person> findBySalaryBetween(double from,double to);

    Flux<Person> findByCompanyId(String companyId);

}
