package com.avada.MyHouse24User.controller;


import com.avada.MyHouse24User.entity.Chat;
import com.avada.MyHouse24User.services.impl.ChatServiceImpl;
import com.avada.MyHouse24User.services.impl.HouseServiceImpl;
import com.avada.MyHouse24User.services.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final SimpMessagingTemplate messagingTemplate;
    private final UserServiceImpl userService;
    private final HouseServiceImpl houseService;
    private final ChatServiceImpl chatService;

    @MessageMapping("/application")
    public void send(final Chat chat) throws Exception {
        Chat res = new Chat();
        res.setDate(LocalDate.now());
        res.setFromName(userService.getAuthUser().getFirstName());
        res.setIsUser(false);
        res.setFromId(userService.getAuthUser().getId());
        res.setHouse(houseService.getById(chat.getFromId()));
        res.setText(chat.getText());
        chatService.save(res);
        res.setHouse(null);
        messagingTemplate.convertAndSend("/all/messages/"+ chat.getFromId(), res);
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