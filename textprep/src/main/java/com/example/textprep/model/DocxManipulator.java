package com.example.textprep.model;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DocxManipulator {
    private XWPFDocument document;
//    private String fileId;

    public DocxManipulator(XWPFDocument document) throws IOException {
        this.document = document;
    }

    private void printText() throws IOException {
        XWPFWordExtractor ex = new XWPFWordExtractor(document);
        String text = ex.getText();
        System.out.println(text);
    }

    public XWPFDocument replacePlaceholders(ArrayList<String> replacements) throws IOException {
        int i =0;

        for (XWPFParagraph p : document.getParagraphs()) {
            List<XWPFRun> runs = p.getRuns();
            if (runs != null) {
                for (XWPFRun r : runs) {
                    String text = r.getText(0);
                    if (text != null && text.contains("placeholder")) {
                        text = text.replace("placeholder", replacements.get(i));
                        r.setText(text, 0);
                        i++;
                    }
                }
            }
        }
       return document;
    }
}
