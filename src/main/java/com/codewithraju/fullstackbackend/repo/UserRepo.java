package com.codewithraju.fullstackbackend.repo;

import com.codewithraju.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {


}
