package com.makarovv_task5.controller;

import com.makarovv_task5.model.Message;
import com.makarovv_task5.queue.MessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageQueue messageQueue;

    @GetMapping("/")
    public String welcomePage(@ModelAttribute("message") Message message) {
        return "main";
    }

    @GetMapping("/all")
    public String showAllMessages(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        List<Message> messages = messageQueue.all();
        model.addAttribute("messages", messages);
        return "showAllMessages";
    }

    @PostMapping("/new")
    public String addNewMessage(@ModelAttribute("message") @Valid Message message, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "main";
        messageQueue.offer(message);
        return "redirect:/all";
    }

    @GetMapping("/peek")
    public String peekMessage(Model model) {
        Message message = messageQueue.peek();
        model.addAttribute("message", message);
        return "showMessage";
    }

    @GetMapping("/poll")
    public String pollMessage(Model model) {
        Message message = messageQueue.poll();
        model.addAttribute("message", message);
        return "showMessage";
    }

    @GetMapping("/peekMax")
    public String peekMaxMessage(Model model) {
        Message message = messageQueue.peekMax();
        model.addAttribute("message", message);
        return "showMessage";
    }
}
