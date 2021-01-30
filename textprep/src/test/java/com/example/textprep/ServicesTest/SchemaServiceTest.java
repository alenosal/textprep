package com.example.textprep.ServicesTest;

import com.example.textprep.Services.SchemeService;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchemaServiceTest {

    SchemeService schemeService;
    MultipartFile multipartFile = new MockMultipartFile("file.txt",
            new FileInputStream(new File("C:\\Users\\milos\\OneDrive\\Pulpit\\inz\\file.txt")));

    public SchemaServiceTest() throws IOException {
    }

    @Test
    void getFilesTest() {
        assertTrue(schemeService.getAllFiles() instanceof Stream);
        assertTrue(schemeService.getUserSchemes("4902d37f-deb0-4094-8537-12c8e0fd581d") instanceof Stream);
        assertTrue(schemeService.getUserGroupSchemes("4902d37f-deb0-4094-8537-12c8e0fd581d") instanceof Stream);

        
    }
}
