package com.example.textprep.Controllers;


import com.example.textprep.Services.SchemeService;
import com.example.textprep.entity.ResponseMessage;
import com.example.textprep.entity.SchemeDao;
import com.example.textprep.entity.SchemeDb;
import com.example.textprep.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SchemeController {

    @Autowired
    private SchemeService schemeService;

    @GetMapping("/upload")
    String upload() {
        return "upload";
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        String userId = user.getId();
        try {
            schemeService.uploadFile(file, userId);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<SchemeDao>> getListFiles() {
        List<SchemeDao> files = schemeService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new SchemeDao(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        SchemeDb schemeDb = schemeService.getSchemeById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + schemeDb.getName() + "\"")
                .body(schemeDb.getData());
    }

    @GetMapping("/createFile/{id}")
    public void createFile(@PathVariable String id, @RequestBody ArrayList<String> replacements,
                           HttpServletResponse response ) throws IOException {
        try {
            InputStream inputStream = schemeService.createFile(id, replacements);
            org.apache.commons.io.IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }
        catch (IOException ex) {
            throw new RuntimeException("IOError writing file to output stream");
        }
    }

//    @GetMapping("/downloadFile")
//    public void getFile( HttpServletRequest request,
//                  HttpServletResponse response,
//                  @PathVariable("fileName") String fileName)
//    {
//        //If user is not authorized - he should be thrown out from here itself
//
//        //Authorized user will download the file
//        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");
//        Path file = Paths.get(dataDirectory, fileName);
//        if (Files.exists(file))
//        {
//            response.setContentType("application/pdf");
//            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
//            try
//            {
//                Files.copy(file, response.getOutputStream());
//                response.getOutputStream().flush();
//            }
//            catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}
