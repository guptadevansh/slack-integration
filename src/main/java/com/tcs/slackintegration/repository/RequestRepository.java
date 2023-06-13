package com.tcs.slackintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.slackintegration.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

}
