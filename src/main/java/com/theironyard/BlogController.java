package com.theironyard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */

@Controller
public class BlogController {


@Autowired
    BlogRepository message;


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session){
        List<Message> messageList = message.findAll();
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages",messageList);
//        model.addAttribute("messages",session.getAttribute("messages"));
        model.addAttribute("deleteMessage" ,session.getAttribute("deleteMessage"));
        return "home";
    }


    @RequestMapping(path ="/login", method = RequestMethod.POST)
    public String login (HttpSession session, String userName){
        session.setAttribute("userName",userName);
        return "redirect:/";
    }



    @RequestMapping(path = "/add-message", method = RequestMethod.POST)
    public String inputMessage (HttpSession session, String text ){

        session.setAttribute("messages",text);
        message.save(new Message(text));

        return "redirect:/";
    }

    /**
     * edit feature is not working
     * looking for fix to this issue
      */
@Modifying
@Query
    @RequestMapping(path ="/edit-message", method = RequestMethod.POST)
    public  String editMessage(HttpSession session , int id,String text){


//        session.setAttribute("deleteMessage",message.get(id));
//        message.remove(id);

        session.setAttribute("messages",text);
        message.findOne(id);





        //message.delete(-1);
        return "redirect:/";
   }


}