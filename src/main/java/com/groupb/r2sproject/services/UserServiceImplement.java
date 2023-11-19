package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;
import com.groupb.r2sproject.dtos.UserDTO.GetProfile;
import com.groupb.r2sproject.dtos.UserDTO.UpdateProfile;
import com.groupb.r2sproject.entities.Address;
import com.groupb.r2sproject.entities.Cart;
import com.groupb.r2sproject.entities.User;
import com.groupb.r2sproject.repositories.CartRepository;
import com.groupb.r2sproject.repositories.UserRepository;
import com.groupb.r2sproject.services.interfaces.UserService;
import com.groupb.r2sproject.shared.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.time.ZonedDateTime;
import java.util.*;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplement(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public String login(String username, String password) throws Exception {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            User curr_user = user.get();
            if (passwordEncoder.matches(password, curr_user.getPassword())) {
                return JwtUtils.generateToken(username, curr_user.getRoles());
            } else {
                throw new Exception("Tài khoản hoặc mật khẩu không chính xác");
            }
        } else {
            throw new Exception("Tài khoản hoặc mật khẩu không chính xác");
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public GetProfile register(RegisterDTO register) throws Exception {
        Optional<User> user = userRepository.findByEmail(register.getEmail());
        if (user.isPresent()) {
            throw new Exception("Username existed!");
        }
        else{
            ZonedDateTime now = ZonedDateTime.now();
            Cart cart = new Cart();
            cart.setCreate_date(now);
            Cart saved_cart = this.cartRepository.save(cart);
            String password = this.passwordEncoder.encode(register.getPassword());
            User new_user = this.userRepository.save(new User(register.getUsername(),password,register.getEmail(),register.getFull_name(),saved_cart));
            saved_cart.setUser(new_user);
            return new GetProfile(new_user.getId(), new_user.getUsername(), new_user.getEmail(),new_user.getFull_name(),new HashSet<>());
        }
    }

    @Override
    public GetProfile getUserByUserId(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User current_usr = user.get();
            Set<String> address_lst = new HashSet<String>();
            for(Address address : current_usr.getAddresses()){
                address_lst.add(address.getAddress());
            }
            return new GetProfile(
                    current_usr.getId(), current_usr.getUsername(), current_usr.getEmail(),current_usr.getFull_name(),address_lst
            );
        }
        else {
            return null;
        }
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public GetProfile updateProfile(Long userId, UpdateProfile profile) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curr_user = user.get();
            curr_user.setFull_name(profile.getFull_name());
            curr_user.setEmail(profile.getEmail());
            User current_usr = this.userRepository.save(curr_user);
            Set<String> address_lst = new HashSet<String>();
            for(Address address : current_usr.getAddresses()){
                address_lst.add(address.getAddress());
            }
            return new GetProfile(current_usr.getId(), current_usr.getUsername(), current_usr.getEmail(),current_usr.getFull_name(),address_lst);
        } else
            throw new Exception("UserId not existed!");
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public Boolean changePassword(Long user_id, String old_password, String new_password, String confirm_password) throws Exception {
        try{
            Optional<User> user = userRepository.findById(user_id);
            if(user.isPresent()) {
                User curr_user = user.get();
                if (passwordEncoder.matches(old_password, curr_user.getPassword())) {
                    if(!Objects.equals(new_password, confirm_password)){
                        throw new Exception("Nhập lại mật khẩu không đúng");
                    }
                    else{
                        String password = this.passwordEncoder.encode(new_password);
                        curr_user.setPassword(password);
                        this.userRepository.save(curr_user);
                        return true;
                    }
                } else {
                    throw new Exception("Mật khẩu cũ không chính xác");
                }
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            throw e;
        }
    }


}
