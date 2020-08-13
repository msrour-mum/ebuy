package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.UserSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSearchRepository extends JpaRepository<UserSearch, Long> {
}
