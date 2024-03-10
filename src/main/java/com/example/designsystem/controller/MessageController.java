package com.example.designsystem.controller;

import com.example.designsystem.bean.Message;
import com.example.designsystem.bean.Message;
import com.example.designsystem.dto.ResultDto;
import com.example.designsystem.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private MessageMapper messageMapper;

    @RequestMapping("/addmessage")
    public ResultDto addMessage(
            @RequestBody Message message
    ){
        try{
            messageMapper.insert(message);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/getmessage")
    public Map<String, List<Message>> hello(){
        List<Message> messages=messageMapper.selectList(null);
        List<Message> readList = new ArrayList<Message>();
        List<Message> unreadList = new ArrayList<Message>();
        List<Message> deleteList = new ArrayList<Message>();
        for(Message message : messages){
            if(message.getState().equals("read")){
                readList.add(message);
            }else if(message.getState().equals("unread")){
                unreadList.add(message);
            }else if(message.getState().equals("recycle")){
                deleteList.add(message);
            }
        }
        return new HashMap<String, List<Message>>(){{
            put("read",readList);
            put("unread",unreadList);
            put("recycle",deleteList);
        }};
    }

    @RequestMapping("/setmessage")
    public ResultDto setMessages(
            @RequestBody Map<String, List<Message>> messages
    ){
        for (Map.Entry<String, List<Message>> entry : messages.entrySet()) {
            for(Message message : entry.getValue()){
                message.setState(entry.getKey());
                try{
                    messageMapper.updateById(message);
                }catch(Exception ex){
                    return new ResultDto(ex.getMessage(),false);
                }
            }
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/deletemessage")
    public ResultDto deleteMessagesById(
            @RequestParam("messageId") Integer messageId
    ){
        try{
            messageMapper.deleteById(messageId);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

    @RequestMapping("/deletemessages")
    public ResultDto deleteMessagesByIds(
            @RequestBody List<Integer> messageIds
    ){
        try{

            messageMapper.deleteBatchIds(messageIds);
        }catch(Exception ex){
            return new ResultDto(ex.getMessage(),false);
        }
        return new ResultDto("",true);
    }

}
