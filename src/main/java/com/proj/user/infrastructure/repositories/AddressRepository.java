package com.proj.user.infrastructure.repositories;

import com.proj.user.infrastructure.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long>{
}
