package bkr.core.user.repoistory;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import bkr.core.user.entity.User;

/**
 * 用户表库
 * 
 * @author chengd
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long>,
        QueryDslPredicateExecutor<User> {

}
