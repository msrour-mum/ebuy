package edu.miu.ebuy.dao;

import edu.miu.ebuy.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	public List<User> findAllByEmail(String email);
	public Optional<User> findByEmail(String email);
	public List<User> findByVendor_Id(int vendorId);
}
