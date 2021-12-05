package com.springrest.chatLog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.chatLog.model.chatLogEntity;
import com.springrest.chatLog.view.chatLogServiceImpl;

@RestController
public class chatLogController {

	@Autowired
	private chatLogServiceImpl serviceImpl;
	
	@GetMapping("/chatlogs/{user}")
	public Object getChatLogs(@PathVariable String user, @RequestBody chatLogEntity entity) {	
		return serviceImpl.getChatLogs(user, entity);
	}
	
	@PostMapping("/chatlogs/{user}/")
	public  Object createChatLog(@PathVariable String user,@RequestBody chatLogEntity entity) {	
		return serviceImpl.createChatLog(user, entity);
	}
	
	@DeleteMapping("/chatlogs/{user}")
	public Object deleteChatLogs(@PathVariable String user) {
		return serviceImpl.deleteChatLogs(user);
	}
	
	@DeleteMapping("/chatlogs/{user}/{msgId}")
	public Object deleteChatLog(@PathVariable String user,Long msgId) {	
		return serviceImpl.deleteChatLog(user, msgId);
	}
}
