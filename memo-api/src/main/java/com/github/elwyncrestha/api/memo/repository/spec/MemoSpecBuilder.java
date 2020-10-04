package com.github.elwyncrestha.api.memo.repository.spec;

import java.util.Map;

import org.springframework.data.jpa.domain.Specification;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.core.repository.BaseSpecBuilder;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
public class MemoSpecBuilder extends BaseSpecBuilder<Memo> {

    public MemoSpecBuilder(Map<String, String> params) {
        super(params);
    }

    @Override
    protected Specification<Memo> getSpecification(String property, String filterValue) {
        return new MemoSpec(property, filterValue);
    }
}
