package com.github.elwyncrestha.core.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.elwyncrestha.core.dto.BaseMapper;
import com.github.elwyncrestha.core.dto.RestResponseDto;
import com.github.elwyncrestha.core.service.BaseService;
import com.github.elwyncrestha.core.utils.PaginationUtils;

/**
 * @param <E> Entity
 * @param <D> DTO
 * @param <I> ID
 * @author Elvin Shrestha on 6/14/2020
 */
public abstract class BaseController<E, D, I> {

    private final Logger logger;

    private final BaseService<E, I> service;
    private final BaseMapper<E, D> mapper;

    protected BaseController(
        BaseService<E, I> service,
        BaseMapper<E, D> mapper,
        Class<?> loggerClass
    ) {
        this.service = service;
        this.mapper = mapper;
        this.logger = LoggerFactory.getLogger(loggerClass);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody D dto) {

        final E saved = service.save(mapper.mapDtoToEntity(dto));

        if (null == saved) {
            logger.error("Error while saving {}", dto);
            return new RestResponseDto()
                .fail(HttpStatus.INTERNAL_SERVER_ERROR, Optional.of("Error while saving " + dto));
        }

        return new RestResponseDto().success(mapper.mapEntityToDto(saved));
    }

    @PostMapping("/all")
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<D> dto) {

        final List<E> saved = service.saveAll(mapper.mapDtoListToEntityList(dto));

        if (null == saved) {
            logger.error("Error while saving {}", dto);
            return new RestResponseDto()
                .fail(HttpStatus.INTERNAL_SERVER_ERROR, Optional.of("Error while saving " + dto));
        }

        return new RestResponseDto().success(mapper.mapEntityListToDtoList(saved));
    }

    @GetMapping("/{i}")
    public ResponseEntity<?> getById(@PathVariable I i) {
        Optional<E> e = service.findOne(i);

        if (!e.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.empty());
        }

        return new RestResponseDto().success(mapper.mapEntityToDto(e.get()));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return new RestResponseDto().success(mapper.mapEntityListToDtoList(service.findAll()));
    }

    @PostMapping("/one")
    public ResponseEntity<?> getOneBySpec(@RequestBody Object filter) {
        final Map<String, String> filters = getFilterParams(filter);
        Optional<E> e = service.findOneBySpec(filters);

        if (!e.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.empty());
        }

        return new RestResponseDto().success(mapper.mapEntityToDto(e.get()));
    }

    @PostMapping("/list")
    public ResponseEntity<?> getPageable(@RequestBody Object filter, @RequestParam int page,
        @RequestParam int size) {

        final Pageable pageable = PaginationUtils.pageable(page, size);
        final Map<String, String> filters = getFilterParams(filter);

        final Page<E> entities = service.findPageableBySpec(filters, pageable);
        final Page<D> dtos = new PageImpl<>(mapper.mapEntityListToDtoList(entities.getContent()),
            pageable, entities.getTotalElements());

        return new RestResponseDto().success(dtos);
    }

    @DeleteMapping("/{i}")
    public ResponseEntity<?> delete(@PathVariable I i) {
        service.deleteById(i);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/all")
    public ResponseEntity<?> getAllBySpec(@RequestBody Object filter) {

        final Map<String, String> filters = getFilterParams(filter);

        return new RestResponseDto()
            .success(mapper.mapEntityListToDtoList(service.findAllBySpec(filters)));
    }

    private Map<String, String> getFilterParams(Object filter) {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(filter, Map.class);
    }

}
