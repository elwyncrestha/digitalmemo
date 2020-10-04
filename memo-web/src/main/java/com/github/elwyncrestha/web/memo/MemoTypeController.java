package com.github.elwyncrestha.web.memo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.elwyncrestha.api.memo.entity.MemoType;
import com.github.elwyncrestha.api.memo.service.MemoTypeService;
import com.github.elwyncrestha.core.controller.BaseController;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@RestController
@RequestMapping(MemoTypeController.URL)
@Slf4j
public class MemoTypeController extends BaseController<MemoType, Long> {

    static final String URL = "/v1/memo-type";

    protected MemoTypeController(
        MemoTypeService service) {
        super(service, log.getClass());
    }
}
