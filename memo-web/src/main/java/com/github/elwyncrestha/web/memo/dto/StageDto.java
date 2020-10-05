package com.github.elwyncrestha.web.memo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.github.elwyncrestha.core.enums.DocAction;
import com.github.elwyncrestha.core.enums.DocStatus;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class StageDto {

    private Long memoId;
    private DocAction docAction;
    private UserDto toUser;
    private DocStatus documentStatus;
    private String comment;
}
