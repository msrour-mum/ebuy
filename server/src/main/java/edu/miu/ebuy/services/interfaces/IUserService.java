package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.Role;
import edu.miu.ebuy.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User signup(User user) throws HttpException;
    User get(int id);
    User update(User user);
    User update(User user, String imageUrl);
    User add(User user, String imageUrl);
    void delete(int id);
    List<User> findAllByEmail(String name);
    Optional<User> findByEmail(String email);
    List<User> getAll();
    List<Role> getRoles();
}
