package com.github.elwyncrestha.api.memo.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.github.elwyncrestha.core.entity.BaseEntity;
import com.github.elwyncrestha.core.enums.DocStatus;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Memo extends BaseEntity<Long> {

    @OneToOne
    private MemoType type;

    private String referenceNumber;

    private String subject;

    private String content;

    private DocStatus documentStatus;

    private String previousStageList;

    @OneToOne
    private MemoStage currentStage;
}
