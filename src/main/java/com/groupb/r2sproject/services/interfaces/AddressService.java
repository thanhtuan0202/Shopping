package com.groupb.r2sproject.services.interfaces;

import com.groupb.r2sproject.dtos.AdressDTO.AddAddress;
import com.groupb.r2sproject.dtos.AdressDTO.GetAdress;
import com.groupb.r2sproject.dtos.AdressDTO.UpdateAddress;

public interface AddressService {
    GetAdress getUserAddresses(Long user_id);

    Boolean AddUserAddresses(Long user_id, AddAddress addAdress);

    Boolean UpdateUserAddresses(Long user_id, UpdateAddress updateAdress);

    Boolean DeleteAddresses(Long user_id, UpdateAddress updateAdress);
}
