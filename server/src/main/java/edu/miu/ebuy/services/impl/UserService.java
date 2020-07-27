package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.dao.RoleRepository;
import edu.miu.ebuy.dao.UserRepository;
import edu.miu.ebuy.models.Role;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Bean
    public PasswordEncoder passwordUtil() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User create(User user) {
        //Todo: check role
        user.setRole(new Role(4));
        user.setPassword(passwordUtil().encode(user.getPassword()));
        return userRepository.save(user);}

    @Override
    public User get(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<User> findAllByEmail(String email) {
        return (List<User>) userRepository.findAllByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

//    @Override
//    public List<UserDetail> getAllUser() {
//        return
//                convertToDetails( userRepository.findAll());
//    }
//
//    public  List<UserDetail> convertToDetails(List<User> lst)
//    {
//        if (lst==null) return null;
//        List<UserDetail> resultUser=new ArrayList<>();
//        for (User user : lst)
//        {
//
//
//            UserDetail userDetail=new UserDetail(user.getId(), user.getName(),user.getEmail(),user.getBirthDate(),user.getGender(),user.getMobile(),user.getBio(),user.getPhotoUrl(),user.getCoverUrl(),user.isActive(),user.isAdmin());
//            System.out.println("User : :"+user.getId()+" "+user.getName());
//            resultUser.add(userDetail);
//        }
//        return resultUser;
//    }


}
