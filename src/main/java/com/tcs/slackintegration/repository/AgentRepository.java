package com.tcs.slackintegration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcs.slackintegration.utility.Agents;

@Repository
public interface AgentRepository extends JpaRepository<Agents, String>{

}
