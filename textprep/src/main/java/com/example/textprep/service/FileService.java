package com.example.textprep.service;

import com.example.textprep.model.DocxManipulator;
import com.example.textprep.model.FileDb;
import com.example.textprep.repository.FileRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;


@Service
public class FileService {
    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    @Autowired
    private FileRepository fileRepository;

    public FileDb uploadFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDb fileDb = new FileDb(fileName,file.getBytes(), file.getContentType());

        return fileRepository.save(fileDb);
    }

    public FileDb getFileById(String id) {
        return fileRepository.findById(id).get();
    }

    public Stream<FileDb> getAllFiles() {
        return fileRepository.findAll().stream();
    }

    public XWPFDocument createFile (String fileId, ArrayList<String> replacements) throws IOException {
        byte[] docByte = getFileById(fileId).getData();
        XWPFDocument docx = new XWPFDocument(new ByteArrayInputStream(docByte));
        DocxManipulator docxManipulator = new DocxManipulator(docx);
        return docxManipulator.replacePlaceholders(replacements);
    }



}
