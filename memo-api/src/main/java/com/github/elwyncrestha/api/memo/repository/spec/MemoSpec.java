package com.github.elwyncrestha.api.memo.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.core.enums.DocStatus;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
public class MemoSpec implements Specification<Memo> {

    private static final String FILTER_BY_DOC_STATUS = "documentStatus";
    private static final String FILTER_BY_CURRENT_STAGE_TO_USER_ID = "currentStage.toUser.id";

    private final String property;
    private final String value;

    public MemoSpec(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Memo> root, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) {
        switch (property) {
            case FILTER_BY_DOC_STATUS:
                return criteriaBuilder.equal(root.get(property), DocStatus.valueOf(value));
            case FILTER_BY_CURRENT_STAGE_TO_USER_ID:
                return criteriaBuilder.equal(root
                        .join("currentStage", JoinType.LEFT)
                        .join("toUser")
                        .get("id"),
                    Long.valueOf(value));
            default:
                return null;
        }
    }
}
