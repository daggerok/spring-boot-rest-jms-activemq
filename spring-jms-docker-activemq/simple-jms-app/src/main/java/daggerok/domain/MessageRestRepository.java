package daggerok.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRestRepository extends JpaRepository<Message, Long>, QueryDslPredicateExecutor<Message> {}
