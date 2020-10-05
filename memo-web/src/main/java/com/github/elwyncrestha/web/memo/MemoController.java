package com.github.elwyncrestha.web.memo;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.api.memo.service.MemoService;
import com.github.elwyncrestha.core.controller.BaseController;
import com.github.elwyncrestha.core.dto.RestResponseDto;
import com.github.elwyncrestha.web.memo.dto.StageDto;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@RestController
@RequestMapping(MemoController.URL)
@Slf4j
public class MemoController extends BaseController<Memo, Long> {

    static final String URL = "/v1/memo";

    private final MemoService service;
    private final MemoMapper memoMapper;

    protected MemoController(
        MemoService service,
        MemoMapper memoMapper
    ) {
        super(service, log.getClass());

        this.service = service;
        this.memoMapper = memoMapper;
    }

    @PostMapping("/action")
    public ResponseEntity<?> action(@RequestBody StageDto stageDto) {
        Optional<Memo> memo = service.findOne(stageDto.getMemoId());
        if (!memo.isPresent()) {
            return new RestResponseDto().fail(HttpStatus.NOT_FOUND, Optional.of("Memo not found."));
        }

        final Memo actionMemo = memoMapper.action(stageDto, memo.get());
        return new RestResponseDto().success(service.save(actionMemo));
    }
}
