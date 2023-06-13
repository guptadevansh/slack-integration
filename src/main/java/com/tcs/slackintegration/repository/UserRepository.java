package com.tcs.slackintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.slackintegration.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

}
