package com.prokarma.com.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface CustomRepository<T, ID extends Serializable> extends Repository<T,ID> {

    T findOne(ID id);

    T save(T entity);

    Iterable<T> findAll();

    Page<T> findAll(Pageable pageable);

    void deleteAll();


}
