package com.github.elwyncrestha.api.memo.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.api.memo.entity.MemoStage;
import com.github.elwyncrestha.api.memo.repository.MemoRepository;
import com.github.elwyncrestha.api.memo.repository.spec.MemoSpecBuilder;
import com.github.elwyncrestha.api.user.entity.User;
import com.github.elwyncrestha.api.user.service.UserService;
import com.github.elwyncrestha.core.enums.DocAction;
import com.github.elwyncrestha.core.repository.BaseSpecBuilder;
import com.github.elwyncrestha.core.service.BaseServiceImpl;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Service
public class MemoServiceImpl extends BaseServiceImpl<Memo, Long> implements MemoService {

    private final MemoRepository repository;
    private final UserService userService;

    protected MemoServiceImpl(
        MemoRepository repository,
        UserService userService
    ) {
        super(repository);
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public Memo save(Memo memo) {
        if (memo.getId() == null) {
            User user = userService.getAuthenticated();
            final MemoStage stage = new MemoStage(user, user, DocAction.DRAFT, DocAction.DRAFT.toString());
            memo.setCurrentStage(stage);
        }
        return repository.save(memo);
    }

    @Override
    protected BaseSpecBuilder<Memo> getSpec(Map<String, String> filterParams) {
        return new MemoSpecBuilder(filterParams);
    }
}
