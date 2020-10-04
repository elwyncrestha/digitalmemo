package com.github.elwyncrestha.api.role.entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.github.elwyncrestha.core.entity.BaseEntity;
import com.github.elwyncrestha.core.enums.RoleType;
import com.github.elwyncrestha.core.enums.Status;

/**
 * @author Elvin Shrestha on 6/20/2020
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity<Long> {

    private String name;

    private Status status = Status.ACTIVE;

    private RoleType roleType = RoleType.APPROVAL;

}
