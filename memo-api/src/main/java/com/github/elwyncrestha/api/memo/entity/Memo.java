package com.github.elwyncrestha.api.memo.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class Memo extends BaseEntity<Long> {

    @OneToOne
    private MemoType type;

    private String referenceNumber;

    private String subject;

    private String content;

    private DocStatus documentStatus;

    private String previousStageList;

    @OneToOne(cascade = CascadeType.ALL)
    private MemoStage currentStage;

    @Transient
    private List<MemoStage> previousStages;

    public List<MemoStage> getPreviousStages() {
        if (this.getPreviousStageList() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModules(new ParameterNamesModule(), new Jdk8Module(), new JavaTimeModule());
            objectMapper.setSerializationInclusion(Include.NON_EMPTY);
            objectMapper.registerModule(new JavaTimeModule());
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            try {
                this.previousStages = objectMapper
                    .readValue(this.getPreviousStageList(),
                        typeFactory.constructCollectionType(List.class, MemoStage.class));
            } catch (IOException e) {
                log.error("Error parsing previousStageList {}", e.getLocalizedMessage());
            }
        } else {
            this.previousStages = new ArrayList<>();
        }
        return this.previousStages;
    }
}
