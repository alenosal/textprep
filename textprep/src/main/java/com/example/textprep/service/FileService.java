package com.example.textprep.service;

import com.example.textprep.model.FileDb;
import com.example.textprep.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;


@Service
public class FileService {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    private FileRepository fileRepository;

    public FileDb uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDb FileDB = new FileDb(fileName, file.getBytes());

        return fileRepository.save(FileDB);
    }

    public FileDb getFileById(String id) {
        return fileRepository.findById(id).get();
    }

    public Stream<FileDb> getAllFiles() {
        return fileRepository.findAll().stream();
    }

}
