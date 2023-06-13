package com.example.easyquiz;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class QuestionBank {
    private Context context;
    private String subject;
    private String grade;

    public QuestionBank(Context context, String subject, String grade) {
        this.context = context;
        this.subject = subject;
        this.grade = grade;
    }

    public List<Question> getQuestions(String subject, String grade) {
        List<Question> questionList = new ArrayList<>();

        try {
            String fileName = subject + "/" + grade + ".xml";
            InputStream inputStream = context.getAssets().open(fileName);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);

            NodeList nodeList = document.getElementsByTagName("question");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String questionText = getElementText(element, "questionText");
                    String option1 = getElementText(element, "option1");
                    String option2 = getElementText(element, "option2");
                    String option3 = getElementText(element, "option3");
                    String option4 = getElementText(element, "option4");
                    int correctOption = Integer.parseInt(getElementText(element, "correctOption"));

                    Question question = new Question(questionText, option1, option2, option3, option4, correctOption);
                    questionList.add(question);
                }
            }

            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionList;
    }

    private String getElementText(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        Node node = nodeList.item(0);
        return node.getTextContent();
    }
}


