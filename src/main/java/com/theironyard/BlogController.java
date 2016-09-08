package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 *
 */

@Controller
public class BlogController {


    @Autowired
    BlogRepository message;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {

        List<Message> messageList = message.findAll();

        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messageList);
        model.addAttribute("messages",session.getAttribute("messages"));
        model.addAttribute("updateMessage", session.getAttribute("updateMessage"));
        return "home";
    }


    @RequestMapping(path ="/login", method = RequestMethod.POST)
    public String login (HttpSession session, String userName){
        session.setAttribute("userName",userName);
        return "redirect:/";
    }


    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String inputMessage(HttpSession session, String text) {
        session.setAttribute("messages", text);
        message.save(new Message(text));

        return "redirect:/";
    }

    /**
     * edit feature is not working
     * looking for fix to this issue
     */

    @RequestMapping(path = "/edit-message", method = RequestMethod.POST)
    public String edit(String text, Integer id) {
        if (!text.isEmpty()) {
            message.delete(id);
            message.save(new Message(text));
        }
        return "redirect:/";
    }


}