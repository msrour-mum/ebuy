package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findAllByEmail(String email);
	Optional<User> findByEmail(String email);
	List<User> findByVendor_Id(int vendorId);
}
