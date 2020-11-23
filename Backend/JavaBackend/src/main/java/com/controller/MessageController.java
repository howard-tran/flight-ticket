package com.controller;

import java.util.Optional;

import com.App;
import com.helper.SocketService;
import com.model.Message;
import com.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(App.service + "/conversation")
@RestController
public class MessageController {
  @Autowired
  private MessageService messageService;

  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;

  private void sendToUser(String userId, Object message){
    this.simpMessagingTemplate.convertAndSend(
      String.format("%s/%s", SocketService.ChatSupplier.messageBrocker, userId),
      message
    );
  }

  @MessageMapping(SocketService.ChatSupplier.chatHanlder)
  public void messageHandle(@Payload Message message) {
    Optional<Optional<String>> res = this.messageService.addMessage(message);

    if (res.isEmpty()) {
      sendToUser(message.getSenderId(), ResponseHandler.error(null));
    } else if (res.get().isEmpty()) {
      sendToUser(message.getSenderId(), ResponseHandler.conversationNotExisted(null));
    } else {
      sendToUser(message.getSenderId(), message);
      sendToUser(message.getReceiverId(), message);
    }
  }
}
