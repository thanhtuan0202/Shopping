package com.groupb.r2sproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.groupb.r2sproject.repositories.AddressRepository;
import com.groupb.r2sproject.services.interfaces.AddressService;

@Service
public class AddressServiceImplement implements AddressService{
    @Autowired
    private AddressRepository addressRepository;
}
