package com.github.elwyncrestha.api.memo.repository;

import org.springframework.stereotype.Repository;

import com.github.elwyncrestha.api.memo.entity.Memo;
import com.github.elwyncrestha.core.repository.BaseRepository;

/**
 * @author Elvin Shrestha on 10/4/2020
 */
@Repository
public interface MemoRepository extends BaseRepository<Memo, Long> {

}
