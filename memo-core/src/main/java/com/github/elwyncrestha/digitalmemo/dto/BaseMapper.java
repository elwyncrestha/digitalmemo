package com.github.elwyncrestha.digitalmemo.dto;

import java.util.List;

/**
 * @param <E> Entity
 * @param <D> DTO
 * @author Elvin Shrestha on 6/14/2020
 */
public interface BaseMapper<E, D> {

    D mapEntityToDto(E e);

    E mapDtoToEntity(D d);

    List<D> mapEntityListToDtoList(List<E> e);

    List<E> mapDtoListToEntityList(List<D> d);
}
