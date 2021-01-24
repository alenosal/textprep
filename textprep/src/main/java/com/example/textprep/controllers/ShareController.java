package com.example.textprep.Controllers;

import com.example.textprep.Services.SharesService;
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
public class ShareController {

    @Autowired
    private SharesService sharesService;

    @GetMapping("/share")
    public String share(){
        return "share";
    }

    @PostMapping("/createShare")
    public ResponseEntity<ResponseMessage> createShare(@RequestParam("schemaId") String schemaID,
                                                       @RequestParam("groupId") String groupId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String userId = user.getId();

        String message;
        try {
            sharesService.createShare(schemaID, groupId, userId);
            message = "You have created share ";
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not create share!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
}
