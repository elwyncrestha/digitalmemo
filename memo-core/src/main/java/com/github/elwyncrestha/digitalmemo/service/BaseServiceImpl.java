package com.github.elwyncrestha.digitalmemo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.github.elwyncrestha.digitalmemo.repository.BaseRepository;
import com.github.elwyncrestha.digitalmemo.repository.BaseSpecBuilder;

/**
 * @param <T> Type
 * @param <I> ID
 * @author Elvin Shrestha on 6/14/2020
 */
public abstract class BaseServiceImpl<T, I> implements BaseService<T, I> {

    private final BaseRepository<T, I> repository;

    protected BaseServiceImpl(
        BaseRepository<T, I> repository
    ) {
        this.repository = repository;
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public List<T> saveAll(List<T> entities) {
        return repository.saveAll(entities);
    }

    @Override
    public Optional<T> findOne(I i) {
        return repository.findById(i);
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(T entity) {
        repository.delete(entity);
    }

    @Override
    public void deleteById(I i) {
        repository.deleteById(i);
    }

    @Override
    public Page<T> findPageableBySpec(Map<String, String> filterParams, Pageable pageable) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findAll(spec, pageable);
    }

    @Override
    public List<T> findAllBySpec(Map<String, String> filterParams) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findAll(spec);
    }

    @Override
    public Optional<T> findOneBySpec(Map<String, String> filterParams) {
        final BaseSpecBuilder<T> builder = getSpec(filterParams);
        final Specification<T> spec = builder.build();

        return repository.findOne(spec);
    }

    protected abstract BaseSpecBuilder<T> getSpec(Map<String, String> filterParams);
}
