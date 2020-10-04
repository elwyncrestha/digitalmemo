package com.github.elwyncrestha.api.memo.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.github.elwyncrestha.api.user.entity.User;
import com.github.elwyncrestha.core.entity.BaseEntity;
import com.github.elwyncrestha.core.enums.DocAction;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MemoStage extends BaseEntity<Long> {

    @OneToOne
    private User fromUser;

    @OneToOne
    private User toUser;

    private DocAction docAction;

    private String comment;
}
