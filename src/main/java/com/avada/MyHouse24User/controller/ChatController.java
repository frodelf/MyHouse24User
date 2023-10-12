package com.avada.MyHouse24User.controller;


import com.avada.MyHouse24User.entity.Chat;
import com.avada.MyHouse24User.services.impl.ChatServiceImpl;
import com.avada.MyHouse24User.services.impl.HouseServiceImpl;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserServiceImpl userService;
    private final HouseServiceImpl houseService;
    private final ChatServiceImpl chatService;
    private final RabbitTemplate template;

    @GetMapping("/rabbit/message")
    public ResponseEntity<String> rabbitMessage(@RequestParam("text")String message, @RequestParam("house")Long house) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Chat chat = new Chat();
        chat.setText(message);
        chat.setFromName(userService.getAuthUser().getFirstName());
        chat.setFromId(house);
        chat.setIsUser(true);
        template.setExchange("common-exchange");
        template.convertAndSend(objectMapper.writeValueAsString(chat));
        return ResponseEntity.ok("Message sent to queue");
    }

    @GetMapping("/chat/getAll/{page}/{houseId}")
    @ResponseBody
    public List<Chat> getComments (@PathVariable("page")int page, @PathVariable("houseId")long houseId){
        return chatService.getAll(page, houseId);
    }
    @GetMapping("/message/{id}")
    public ModelAndView message(@PathVariable("id")Long id){
        ModelAndView modelAndView = new ModelAndView("view/chat");
        return modelAndView;
    }
}