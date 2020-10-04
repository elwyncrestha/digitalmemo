package com.github.elwyncrestha.web.memo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.api.memo.service.MemoService;
import com.github.elwyncrestha.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@RestController
@RequestMapping(MemoController.URL)
@Slf4j
public class MemoController extends BaseController<Memo, Long> {

    static final String URL = "/v1/memo";

    protected MemoController(
        MemoService service) {
        super(service, log.getClass());
    }
}
