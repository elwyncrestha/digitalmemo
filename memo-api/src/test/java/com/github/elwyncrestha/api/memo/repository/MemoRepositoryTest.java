package com.github.elwyncrestha.api.memo.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.elwyncrestha.BaseJpaTest;
import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.api.memo.entity.MemoStage;
import com.github.elwyncrestha.api.memo.entity.MemoType;
import com.github.elwyncrestha.api.user.entity.User;
import com.github.elwyncrestha.api.user.repository.UserRepository;
import com.github.elwyncrestha.core.enums.DocAction;
import com.github.elwyncrestha.core.enums.DocStatus;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Elvin Shrestha on 10/6/2020
 */
public class MemoRepositoryTest extends BaseJpaTest {

    @Autowired
    private MemoTypeRepository memoTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MemoRepository memoRepository;

    @Test
    @DatabaseSetup("/dataset/memo/memo-config.xml")
    public void testSaveShouldSaveMemo() {

        final User initiator = userRepository.getOne(1L);
        final MemoType memoType = memoTypeRepository.getOne(1L);

        final Memo memo = new Memo();
        memo.setType(memoType);
        memo.setReferenceNumber("Ref-2020-12345");
        memo.setSubject("Test Memo");
        memo.setContent("Test Content for Memo");
        memo.setDocumentStatus(DocStatus.PENDING);

        final MemoStage stage = new MemoStage();
        stage.setFromUser(initiator);
        stage.setToUser(initiator);
        stage.setDocAction(DocAction.DRAFT);
        stage.setComment(DocAction.DRAFT.toString());

        memo.setCurrentStage(stage);
        final Memo savedMemo = memoRepository.save(memo);

        assertThat(savedMemo.getId(), equalTo(1L));
    }

}
