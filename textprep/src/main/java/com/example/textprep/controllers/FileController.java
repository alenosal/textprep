package com.example.textprep.controllers;

import com.example.textprep.model.DocxInput;
import com.example.textprep.service.FileService;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;
import java.io.IOException;

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
    public String uploadfile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) throws JAXBException, Docx4JException, IOException {
        DocxInput docx = new DocxInput(file);
        fileService.uploadFile(file);
        docx.printText();
        System.out.println(docx.placeholderSearch());
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded "+ file.getOriginalFilename());
        return "redirect:/";
    }

    @PostMapping("/api/v1/changeFile")
    public void changeFile() {

    }
}
