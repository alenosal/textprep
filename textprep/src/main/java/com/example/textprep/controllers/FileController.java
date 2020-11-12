package com.example.textprep.controllers;

import com.example.textprep.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@Controller
public class FileController {
    @Autowired
    FileService fileService;

    @GetMapping("/api/v1")
    public String index() {
        return "upload";
    }

    @PostMapping("/api/v1/uploadFile")
    public String uploadfile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        fileService.uploadFile(file);

        redirectAttributes.addFlashAttribute("message", "You successfully uploaded "+ file.getOriginalFilename());
        return "redirect:/";
    }
}
