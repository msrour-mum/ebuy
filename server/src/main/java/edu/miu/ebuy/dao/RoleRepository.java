package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, Integer> {
}
