package edu.miu.ebuy.services.impl;

import edu.miu.ebuy.common.enums.RoleEnum;
import edu.miu.ebuy.common.events.publishers.SignupSuccessfullyEvent;
import edu.miu.ebuy.dao.RoleRepository;
import edu.miu.ebuy.dao.UserRepository;
import edu.miu.ebuy.exceptions.Errors;
import edu.miu.ebuy.exceptions.HttpException;
import edu.miu.ebuy.models.OrderItem;
import edu.miu.ebuy.models.Product;
import edu.miu.ebuy.models.Role;
import edu.miu.ebuy.models.User;
import edu.miu.ebuy.security.Context;
import edu.miu.ebuy.services.interfaces.IMerchantService;
import edu.miu.ebuy.services.interfaces.IShoppingService;
import edu.miu.ebuy.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    IMerchantService merchantService;

    @Autowired
    IShoppingService shoppingService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Bean
    public PasswordEncoder passwordUtil() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User signup(User user) throws HttpException {
        CheckIfUserNameExists(user);
        Product product = null;
        if(user.getRole().getId() == RoleEnum.VENDOR.id)
        {
            product = shoppingService.subscribeVendor(user);
        }
        user.setRole(new Role(user.getRole().getId()));
        user.setIsActive(true);
        user.setPassword(passwordUtil().encode(user.getPassword()));
        User savedUser =  userRepository.save(user);

        if(user.getRole().getId() == RoleEnum.VENDOR.id)
        {
            assert product != null;
            OrderItem orderItem = new OrderItem(product, 1,product.getPrice());
            shoppingService.addOrder(orderItem, savedUser, 0);
        }
        eventPublisher.publishEvent(new SignupSuccessfullyEvent(savedUser));

        return savedUser;
    }

    @Override
    public User get(int id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User update(User user) {
        User userToUpdate = this.get(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setAddress(user.getAddress());
        return userRepository.save(userToUpdate);
    }

    @Override
    public User update(User user, String imageUrl) {
        User userToUpdate = this.get(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setImageUrl(imageUrl);
        if(Context.getUser().getRole().getId() == RoleEnum.VENDOR.id) {
            user.setVendor(new User(Context.getUser().getId()));
        }
        return userRepository.save(userToUpdate);
    }

    @Override
    public User add(User user, String imageUrl) {
        user.setImageUrl(imageUrl);
        user.setActive(true);
        BCryptPasswordEncoder passwordUtil=new BCryptPasswordEncoder();
        String pass=passwordUtil.encode("sa");
        user.setPassword(pass);
        if(Context.getUser().getRole().getId() == RoleEnum.VENDOR.id) {
            user.setVendor(new User(Context.getUser().getId()));
        }
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        if(Context.getUser().getRole().getId() == RoleEnum.ADMIN.id) {
            return userRepository.findAll();
        }
        else if(Context.getUser().getRole().getId() == RoleEnum.VENDOR.id) {
            if(Context.getUser().getVendor() != null) {
                return userRepository.findByVendor_Id(Context.getUser().getVendor().getId());
            } else {
                return userRepository.findByVendor_Id(Context.getUser().getId());
            }
        }
        return null;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<User> findAllByEmail(String email) {
        return userRepository.findAllByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void CheckIfUserNameExists(User user) throws HttpException {
        User exUser = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(exUser != null) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "User name is already exists!", Errors.NOT_UNIQUE_USER_NAME_ERROR);
        }
    }

}
