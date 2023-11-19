package com.groupb.r2sproject.services;

import com.groupb.r2sproject.dtos.AdressDTO.AddAddress;
import com.groupb.r2sproject.dtos.AdressDTO.GetAdress;
import com.groupb.r2sproject.dtos.AdressDTO.UpdateAddress;
import com.groupb.r2sproject.entities.Address;
import com.groupb.r2sproject.entities.User;
import com.groupb.r2sproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupb.r2sproject.repositories.AddressRepository;
import com.groupb.r2sproject.services.interfaces.AddressService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImplement implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public GetAdress getUserAddresses(Long user_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            Set<String> res = new HashSet<>();
            for (Address address : user.get().getAddresses()){
                res.add(address.getAddress());
            }
            return new GetAdress(res);
        } else throw new RuntimeException("User not found");
    }

    @Override
    public Boolean AddUserAddresses(Long user_id, AddAddress addAdress) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            User curr_user = user.get();

            Address address = new Address();
            address.setAddress(addAdress.getAddress());
            address.setUser(curr_user);
            addressRepository.save(address);

            return true;
        } else throw new RuntimeException("User not found");
    }

    @Override
    public Boolean UpdateUserAddresses(Long user_id, UpdateAddress updateAdress) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            Optional<Address> address = addressRepository.findById(updateAdress.getId());
            if (address.isPresent()) {
                Address curr_address = address.get();
                curr_address.setAddress(updateAdress.getAddress());
                addressRepository.save(curr_address);
                return true;
            } else throw new RuntimeException("Address not found");
        } else throw new RuntimeException("User not found");
    }

    @Override
    public Boolean DeleteAddresses(Long user_id, Long address_id) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            Optional<Address> address = addressRepository.findById(address_id);
            if (address.isPresent()) {
                Address curr_address = address.get();
                addressRepository.delete(curr_address);
                return true;
            } else throw new RuntimeException("Address not found");
        } else throw new RuntimeException("User not found");
    }

}
