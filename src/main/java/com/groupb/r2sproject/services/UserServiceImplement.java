package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.AuthDTO.RegisterDTO;
import com.groupb.r2sproject.entities.User;
import com.groupb.r2sproject.repositories.UserRepository;
import com.groupb.r2sproject.services.interfaces.UserService;
import com.groupb.r2sproject.shared.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        Optional<User> user =userRepository.findByUsername(username);
        if(user.isPresent()) {
            User curr_user = user.get();
            if(passwordEncoder.matches(password, curr_user.getPassword())){
                return JwtUtils.generateToken(username,curr_user.getRoles());
            }
            else{
                throw new Exception("Tài khoản hoặc mật khẩu không chính xác");
            }
        }
        else{
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
}
