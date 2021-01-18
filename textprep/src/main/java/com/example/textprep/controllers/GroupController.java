package com.example.textprep.Controllers;

import com.example.textprep.Services.GroupService;
import com.example.textprep.entity.ResponseMessage;
import com.example.textprep.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/createGroup")
    public String create(){
        return "createGroup";
    }

    @PostMapping("/CreateGroup")
    public ResponseEntity<ResponseMessage> createGroup(@RequestParam("name") String name) {
        String message = "";
        String groupName = name;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String userId = user.getId();

        try {
            groupService.createGroup(name, userId);
            message = "You have created group " + name;
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not create Group: " + name + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
