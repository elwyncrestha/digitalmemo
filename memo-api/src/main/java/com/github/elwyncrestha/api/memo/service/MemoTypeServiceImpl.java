package com.github.elwyncrestha.api.memo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.elwyncrestha.api.memo.entity.MemoType;
import com.github.elwyncrestha.api.memo.repository.MemoTypeRepository;
import com.github.elwyncrestha.core.repository.BaseSpecBuilder;
import com.github.elwyncrestha.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Service
public class MemoTypeServiceImpl extends BaseServiceImpl<MemoType, Long> implements
    MemoTypeService {

    protected MemoTypeServiceImpl(
        MemoTypeRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<MemoType> getSpec(Map<String, String> filterParams) {
        return null;
    }
}
