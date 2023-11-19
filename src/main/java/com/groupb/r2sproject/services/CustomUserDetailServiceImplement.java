package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.CustomUserDetail;
import com.groupb.r2sproject.entities.User;
import com.groupb.r2sproject.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailServiceImplement implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public CustomUserDetail getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetail) {
            return (CustomUserDetail) authentication.getPrincipal();
        } else {
            return null;
        }
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User userEntity = user.get();
        return new CustomUserDetail(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles(), userEntity.getId());
    }
}


