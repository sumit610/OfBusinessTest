package com.springrest.chatLog.view;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springrest.chatLog.model.APICustomException;
import com.springrest.chatLog.model.chatLogEntity;

public class chatLogServiceImpl {
	
	public static ArrayList<chatLogEntity> entities = new ArrayList<>();
	
	public chatLogServiceImpl() {
		chatLogEntity sandy =  new chatLogEntity();
		sandy.setMessage("Hi Sarah! ");
		sandy.setSent(true);
		sandy.setTimeStamp(System.currentTimeMillis());
		sandy.setUser("Sandy");
		
		chatLogEntity sarah =  new chatLogEntity();
		sarah.setMessage("Hi Sandy ...");
		sarah.setSent(true);
		sarah.setTimeStamp(System.currentTimeMillis());
		sarah.setUser("Sarah");
		
		entities.add(sandy);
		entities.add(sarah);
	}
	
	public Object getChatLogs(String user,chatLogEntity entity) {
		
		ArrayList<LinkedHashMap<String, Object>> list = new ArrayList<>();
		int i = entities.size()-1;
		int limit = 0;
		if(entity.getLimit() == null) {
			limit = 10;
		}else {
			limit = entity.getLimit();
		}
		
		while(limit != 0 && i >= 0) {
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			map.put(user,entities.get(i).getMessage());
			list.add(map);
			limit--;
			i--;
		}
		
		return list;
	}
	
	public Object createChatLog(String user,chatLogEntity entity) {
		if(user != null && (!user.matches("^[a-zA-Z0-9]*$") || user.length() >  15)) {
			LinkedHashMap<String, String> map =  new LinkedHashMap<>();
			map.put("error","Invalid parameter");
			APICustomException exception = new APICustomException();
			exception.setGenericError(map);
			exception.setResponseEntity(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
			return exception;
		}
		entity.setUser(user);
		entity.setTimeStamp(System.currentTimeMillis());
		entities.add(entity);
		return entity;
	}
	
	public Object deleteChatLogs(String user) {
		for(int i = entities.size()-1;i>=0;i--) {
			chatLogEntity entity = entities.get(i);
			if(entity.getUser().equals(user)) {
				entities.remove(i);
			}
		}
		return entities;
	}
	
	public Object deleteChatLog(String user,Long msgId) {
		boolean status = false;
		for(int i = entities.size()-1;i>=0;i--) {
			chatLogEntity entity = entities.get(i);
			if(entity.getUser().equals(user) && entity.getMsgId() != null && entity.getMsgId().equals(msgId)) {
				entities.remove(i);
				status = true;
				break;
			}
		}
		
		if(!status) {
			LinkedHashMap<String, String> map =  new LinkedHashMap<>();
			map.put("error","msgId - " + msgId +" not found for user - " + user);
			APICustomException exception = new APICustomException();
			exception.setGenericError(map);
			exception.setResponseEntity(new ResponseEntity<>(HttpStatus.NOT_FOUND));
			return exception;
		}
		
		
		return entities;
	}
	
	
	
}
