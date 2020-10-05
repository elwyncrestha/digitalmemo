package com.github.elwyncrestha.web.memo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.api.memo.entity.MemoStage;
import com.github.elwyncrestha.api.user.entity.User;
import com.github.elwyncrestha.api.user.service.UserService;
import com.github.elwyncrestha.web.memo.dto.StageDto;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Component
@Slf4j
public class MemoMapper {

    private final UserService userService;

    public MemoMapper(UserService userService) {
        this.userService = userService;
    }

    public Memo action(StageDto actionDto, Memo memo) {
        // Adds current stage into previous stage list
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        List<MemoStage> previousStages = memo.getPreviousStages();
        List<String> previousStageList = new ArrayList<>();
        MemoStage memoStage = new MemoStage();

        if (memo.getCurrentStage() != null) {
            memoStage = memo.getCurrentStage();
            Map<String, String> tempCurrentStage = objectMapper
                .convertValue(memo.getCurrentStage(), Map.class);
            try {
                previousStages.forEach(s -> {
                    try {
                        Map<String, String> stageString = objectMapper.convertValue(s, Map.class);
                        previousStageList.add(objectMapper.writeValueAsString(stageString));
                    } catch (JsonProcessingException e) {
                        log.error("Failed to handle Credit Memo JSON data {}", e.getMessage());
                    }
                });
                previousStageList.add(objectMapper.writeValueAsString(tempCurrentStage));
            } catch (JsonProcessingException e) {
                log.error("Failed to get credit memo stage data {}", e.getMessage());
            }
        }

        memo.setPreviousStageList(previousStageList.toString());
        memo.setDocumentStatus(actionDto.getDocumentStatus());
        this.updateStage(actionDto, previousStages, memoStage);
        memo.setCurrentStage(memoStage);
        return memo;
    }

    private void updateStage(StageDto stageDto, List<MemoStage> previousList,
        MemoStage currentStage) {
        User currentUser = userService.getAuthenticated();
        currentStage.setFromUser(currentUser);
        currentStage.setComment(stageDto.getComment());
        currentStage.setDocAction(stageDto.getDocAction());

        switch (stageDto.getDocAction()) {
            case FORWARD:
                User u = userService.findOne(stageDto.getToUser().getId()).get();
                currentStage.setToUser(u);
                log.info("Forward memo with stage: {}", currentStage);
                break;

            case BACKWARD:
                if (previousList.isEmpty()) {
                    currentStage.setToUser(currentStage.getFromUser());
                } else {
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.setSerializationInclusion(Include.NON_EMPTY);
                    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                    for (Object obj : previousList) {
                        MemoStage maker = objectMapper.convertValue(obj, MemoStage.class);
                        if (maker.getFromUser().getId().equals(currentStage.getCreatedBy())) {
                            try {
                                final List<User> users = userService
                                    .findByRoleId(maker.getFromUser().getRole().getId());
                                final List<Long> userIdList = users.stream().map(User::getId)
                                    .collect(Collectors.toList());
                                if (userIdList.contains(currentStage.getCreatedBy())) {
                                    java.util.Optional<User> userOptional = users.stream().
                                        filter(p -> p.getId().equals(currentStage.getCreatedBy())).
                                        findFirst();
                                    currentStage.setToUser(
                                        objectMapper.convertValue(userOptional.get(), User.class));
                                } else {
                                    currentStage
                                        .setToUser(
                                            objectMapper.convertValue(users.get(0), User.class));
                                }
                            } catch (Exception e) {
                                log.error("Error occurred while mapping credit memo stage", e);
                                throw new RuntimeException("Error while performing the action");
                            }
                        }
                    }
                }
                break;

            case APPROVE:
            case REJECT:
                currentStage.setToUser(currentUser);
                log.info("{} credit memo stage: {}", stageDto.getDocAction(), currentStage);
                break;
        }
    }
}
