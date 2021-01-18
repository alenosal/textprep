package com.example.textprep.entity;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DocxInput {
    MultipartFile docx;


    public DocxInput(MultipartFile docx) {

        this.docx = docx;
    }

    public MultipartFile getDocx() {

        return docx;
    }

    public File ConvertToFile() throws IOException {
        File convFile = new File(docx.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(docx.getBytes());
        fos.close();
        return convFile;
    }

    public void printText() throws IOException {
        XWPFDocument doc = new XWPFDocument(docx.getInputStream());
        XWPFWordExtractor ex = new XWPFWordExtractor(doc);
        String text = ex.getText();
        System.out.println(text);
    }

    public Integer placeholderSearch() throws IOException {
        Integer amount = 0;
        XWPFDocument file = new XWPFDocument(docx.getInputStream());
        for (XWPFParagraph p : file.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("placeholder")) {
                        amount++;
                    }
                }
            }
        }
        return amount;
    }

    public ArrayList<String> QuestionFinder() throws IOException {
        XWPFDocument file = new XWPFDocument(docx.getInputStream());
        boolean flag = false;
        ArrayList<String> questionList = new ArrayList<String>();
        String question = "";
        for (XWPFParagraph p : file.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains(">")) {
                        flag = false;
                        questionList.add(question);
                        question = "";
                    }
                    if (text != null && flag == true) {
                        question = question + text + " ";
                    }
                    if (text != null && text.contains("<")) {
                        flag = true;
                    }
                }
            }
        }
        return questionList;
    }


}
