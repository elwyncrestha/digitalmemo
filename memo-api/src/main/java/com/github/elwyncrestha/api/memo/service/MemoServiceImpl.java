package com.github.elwyncrestha.api.memo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.api.memo.repository.MemoRepository;
import com.github.elwyncrestha.api.memo.repository.spec.MemoSpecBuilder;
import com.github.elwyncrestha.core.repository.BaseSpecBuilder;
import com.github.elwyncrestha.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Service
public class MemoServiceImpl extends BaseServiceImpl<Memo, Long> implements MemoService {

    protected MemoServiceImpl(
        MemoRepository repository) {
        super(repository);
    }

    @Override
    protected BaseSpecBuilder<Memo> getSpec(Map<String, String> filterParams) {
        return new MemoSpecBuilder(filterParams);
    }
}
