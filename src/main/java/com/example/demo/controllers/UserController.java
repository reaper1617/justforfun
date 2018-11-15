package com.example.demo.controllers;

import com.example.demo.entities.KickAssUser;
import com.example.demo.repository.KickAssUserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static final String ERROR_MESSAGE_JSON = "{\"status\":\"FAILED\"}";
    private KickAssUserRepository kickAssUserRepository;

    @Autowired
    public UserController(KickAssUserRepository kickAssUserRepository) {
        this.kickAssUserRepository = kickAssUserRepository;
    }


    @RequestMapping(value = "/getusers", method = RequestMethod.GET)
    @ResponseBody
    @Transactional(readOnly = true)
    public String getUsers(){
        Iterable<KickAssUser> kickassusers = kickAssUserRepository.findAll();
        Gson gson = new Gson();
        String result = gson.toJson(kickassusers);
        result = "{\"kickassusers\":" + result + "}";
        return result;
    }

    @RequestMapping(value = "/putuser", method = RequestMethod.POST)
//    @Transactional
    public String putUser(@RequestParam(name = "username") String userName){
        if (userName == null) return ERROR_MESSAGE_JSON;
        KickAssUser found = kickAssUserRepository.findByName(userName);
        if (found == null) found = new KickAssUser(userName,0);
        found.setNumOfKicks(found.getNumOfKicks()+1);
        try {
            kickAssUserRepository.save(found);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR_MESSAGE_JSON;
        }
        return "{\"status\":\"OK\"}";
    }


    @RequestMapping(value = "/getnumofpunchers", method = RequestMethod.GET)
    @ResponseBody
    public String checkIfGotCunt(@RequestParam(value = "username") String username){
        if (username == null) return ERROR_MESSAGE_JSON;
        KickAssUser found = kickAssUserRepository.findByName(username);
        if (found == null) return ERROR_MESSAGE_JSON;
        int num = found.getNumOfKicks();
        String res = "{\"status\":\"OK\",\"numOfKicks\":\""+num+"\"}";
        return res;
    }
}
