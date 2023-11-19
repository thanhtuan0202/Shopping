package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;
import com.groupb.r2sproject.dtos.UserDTO.GetProfile;
import com.groupb.r2sproject.dtos.UserDTO.UpdateProfile;
import com.groupb.r2sproject.entities.User;
import com.groupb.r2sproject.repositories.UserRepository;
import com.groupb.r2sproject.services.interfaces.UserService;
import com.groupb.r2sproject.shared.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
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
    public RegisterDTO register(RegisterDTO register) throws Exception {
        Optional<User> user = userRepository.findByEmail(register.getEmail());
        if (user.isPresent()) {
            throw new Exception("Username existed!");
        }
//        Account account = accountMapper.toEntity(registrationDTO);
//        account.setStatus(true);
//        account.setCreatedDate(ZonedDateTime.now());
//        Account resultAccount = accountRepository.save(account);
//        return accountMapper.toDTO(resultAccount);
        return null;
    }

    @Override
    public GetProfile getUserByUserId(Long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            GetProfile profile = new GetProfile();
            BeanUtils.copyProperties(user.get(), profile);
            return profile;
        } else
            throw new Exception("UserId not existed!");
    }

    @Override
    public Boolean updateProfile(Long userId, UpdateProfile profile) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User curr_user = user.get();
            BeanUtils.copyProperties(profile, curr_user);
            userRepository.save(curr_user);
            return true;
        } else
            throw new Exception("UserId not existed!");
    }


}
