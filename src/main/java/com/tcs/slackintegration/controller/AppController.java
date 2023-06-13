package com.tcs.slackintegration.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.seratch.jslack.api.methods.SlackApiException;
import com.tcs.slackintegration.exception.ResourceNotFoundException;
import com.tcs.slackintegration.model.Request;
import com.tcs.slackintegration.repository.AgentRepository;
import com.tcs.slackintegration.repository.RequestRepository;
import com.tcs.slackintegration.utility.AppService;
import com.tcs.slackintegration.utility.Message;
import com.tcs.slackintegration.utility.Payload;

@RestController
@RequestMapping("/api/v1")
public class AppController {

	@Autowired
    private AppService appService;
	@Autowired
	private RequestRepository reqRepository;
	@Autowired
	private AgentRepository agentRepository;
	
    @PostMapping(path = "/sendmsgtoappletravel",consumes = {"application/x-www-form-urlencoded"})
    public synchronized ResponseEntity<String> sendNewMessageToChannel(Payload payload) throws SlackApiException, IOException, InterruptedException{
    	
    	Request request = new Request();
    	request.setUser_id(payload.getUser_id());
    	request.setRes_url(payload.getResponse_url());
    	request.setMsg(payload.getText());
    	request = reqRepository.save(request);
        appService.sendMessageToSlack("Message " + request.getReq_id() + " from " + appService.fetchUsername(payload.getUser_id()) + ": Hi Team, " + payload.getText());
        
        Message msg = appService.fetchHistory("C03BS1YJ4UE");
        
        request.setTs(msg.getTs());
        request = reqRepository.save(request);
        return ResponseEntity.ok("");
    }

    @PostMapping(path = "/responsetouserquery",consumes = {"application/x-www-form-urlencoded"})
    public ResponseEntity<String> sendNewMessageToUser(Payload payload) throws ResourceNotFoundException, SlackApiException, IOException, InterruptedException {
    	    	
    	String str = payload.getText();
    	String strarr[] = str.split(":");
    	if(strarr.length < 2) return ResponseEntity.badRequest().build();
    	
    	agentRepository.findById(payload.getUser_id())
    			.orElseThrow(() -> new ResourceNotFoundException("Unauthorized user for this request :: " + strarr[0]));
    	
    	Request req = reqRepository.findById(Integer.parseInt(strarr[0]))
    			.orElseThrow(() -> new ResourceNotFoundException("No request for this id :: " + Integer.parseInt(strarr[0])));
    	
        appService.sendMessageToSlack("Hi..! " + " " + strarr[1], req.getRes_url());
        
        appService.replyMessage("C03BS1YJ4UE", req.getTs(), "Message from " + appService.fetchUsername(payload.getUser_id()) + ": " + strarr[1]);
        
        return ResponseEntity.ok("");
    } 
    
    @PostMapping(path = "/sendreplytoappletravel",consumes = {"application/x-www-form-urlencoded"})
    public ResponseEntity<String> sendMessageToChannel(Payload payload) throws ResourceNotFoundException, SlackApiException, IOException, InterruptedException {
    	
    	String str = payload.getText();
    	String strarr[] = str.split(":");
    	if(strarr.length < 2) return ResponseEntity.badRequest().build();
    	
    	Request req = reqRepository.findById(Integer.parseInt(strarr[0]))
    			.orElseThrow(() -> new ResourceNotFoundException("No request for this id :: " + Integer.parseInt(strarr[0])));
    	    	
    	if(!payload.getUser_id().equals(req.getUser_id()))  
    	{
    		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized user for this request..!");
    	}
    	
        appService.replyMessage("C03BS1YJ4UE", req.getTs(), "Message from " + appService.fetchUsername(payload.getUser_id()) + ": " + strarr[1]);
        
        return ResponseEntity.ok("");
    } 
} 

