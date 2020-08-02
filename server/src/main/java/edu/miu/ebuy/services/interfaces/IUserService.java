package edu.miu.ebuy.services.interfaces;

import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    //Default
    public User create(User user) throws HttpException;
    public User get(int id);
    public User update(User user);
    public void delete(int id);
    public List<User> findAllByEmail(String name);
    public Optional<User> findByEmail(String email);
    public List<User> getAll();
}
