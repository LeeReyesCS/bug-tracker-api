package com.adacapstone.bug.tracker.api.Bug;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BugRepository extends JpaRepository<Bug, Long> {
    Bug findByBugId(Long Id);
//    List<Bug> findBugsByUserId(Long userId);

    List<Bug> findAll();
}
