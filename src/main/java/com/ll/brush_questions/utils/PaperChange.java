package com.ll.brush_questions.utils;

import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.entity.ChoiceQuestion;
import com.ll.brush_questions.entity.Paper;
import com.ll.brush_questions.service.ChoiceQuestionService;
import com.ll.brush_questions.service.PaperService;
import com.ll.brush_questions.service.impl.ChoiceQuestionServiceImpl;
import com.ll.brush_questions.service.impl.PaperServiceImpl;
import org.apache.poi.hwpf.extractor.WordExtractor;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaperChange {

   
    public static Paper paper=new Paper();

    public static List<ChoiceQuestion> choiceQuestions=new ArrayList<>(100);

    public static List<String> p1=new ArrayList<>(50);
    public static List<String> p2=new ArrayList<>(100);

    public static String name;
    
    public static void analysisPaper(String fileUrl) throws IOException {

        Map<String, String> map = new HashMap();
        StringBuilder content = new StringBuilder("");
        String result = "0";  // 0表示获取正常，1表示获取异常
        InputStream is = null;
        try {
            is = Files.newInputStream(new File(fileUrl).toPath());
            // 2003版本的word
            WordExtractor extractor = new WordExtractor(is);  // 2003版本 仅doc格式文件可处理，docx文件不可处理
            String[] paragraphText = extractor.getParagraphText();   // 获取段落，段落缩进无法获取，可以在前添加空格填充
            if (paragraphText != null && paragraphText.length > 0) {
                for (String paragraph : paragraphText) {
                    if (!paragraph.startsWith("    ")) {
                        content.append("    ").append(paragraph.trim()).append("\r\n");
                    } else {
                        content.append(paragraph);
                    }
                }
            }
        } catch (Exception e) {

            result = "1"; // 出现异常
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
            map.put("result", result);
            map.put("content", content.toString());


        }

        // 解析试卷的题

        String s = map.get("content");
//        System.out.println(s);
        StringBuilder c= new StringBuilder();
        int flag=-1;
        for(int i=0;i<s.length();i++){
//            System.err.print(s.charAt(i));

            if (s.charAt(i)!=' '&&s.charAt(i)!='\n'&&s.charAt(i)!='\r'){
                c.append(s.charAt(i));
            }



            if (i==s.length()-2){
                c.append(s.charAt(i+1));
                p1.add(String.valueOf(c));
                System.out.println(c);
                break;
            }

            if (s.charAt(i)=='二'&&s.charAt(i+1)=='、'){
                break;
            }

            if (flag==-1&&s.charAt(i+1)=='一'){
                flag=0;
                name= String.valueOf(c);
                System.out.println(name);
                c.delete(0,c.length());
            }
            if (flag==0&&s.charAt(i+1)=='A'){
                flag=1;
                String str=String.valueOf(c);
                p1.add( str.split("、",2)[1]);
//                System.out.println(c);
                c.delete(0,c.length());
            }
            else if (flag==1&&s.charAt(i+1)=='B'){
                flag=2;
                p2.add(String.valueOf(c));
//                System.out.println(c);
                c.delete(0,c.length());
            }
            else if (flag==2&&s.charAt(i+1)=='C'){
                flag=3;
                p2.add(String.valueOf(c));
//                System.out.println(c);
                c.delete(0,c.length());
            }
            else if (flag==3&&s.charAt(i+1)=='D'){
                flag=4;
                p2.add(String.valueOf(c));
//                System.out.println(c);
                c.delete(0,c.length());
            }
            else if (flag==4&&s.charAt(i+1)=='\n'){
                flag=0;
                p2.add(String.valueOf(c));
//                System.out.println(c);
                c.delete(0,c.length());
            }

        }


    }

    public static void addQuestions(long paperId) {
        // 设置选择题

        int index=0;
        int num=1;
        for(String s1:p1) {
            ChoiceQuestion choiceQuestion = new ChoiceQuestion();
            choiceQuestion.setPaperId(paperId);
            choiceQuestion.setTitle(s1);
            try {
                choiceQuestion.setNum(num);
                num++;
            } catch (Exception e) {
                choiceQuestion.setNum(1);
            }
            int flag1 = 1;
            while (flag1 == 1&&index<p2.size()) {
                String option = p2.get(index);
                switch (option.charAt(0)) {
                    case 'A':
                        if (choiceQuestion.getChoiceA() == null) {
                            option=option.split("\\.",2)[1];
                            choiceQuestion.setChoiceA(option);
                        } else {
                            flag1 = 0;
                        }
                        break;
                    case 'B':
                        if (choiceQuestion.getChoiceB() == null) {
                            option=option.split("\\.",2)[1];
                            choiceQuestion.setChoiceB(option);
                        } else {
                            flag1 = 0;
                        }
                        break;
                    case 'C':
                        if (choiceQuestion.getChoiceC() == null) {
                            option=option.split("\\.",2)[1];
                            choiceQuestion.setChoiceC(option);
                        } else {
                            flag1 = 0;
                        }
                        break;
                    case 'D':
                        if (choiceQuestion.getChoiceD() == null) {
                            option=option.split("\\.",2)[1];
                            choiceQuestion.setChoiceD(option);
                        } else {
                            flag1 = 0;
                        }
                        break;
                }
                if (flag1==1){
                    index++;
                }
            }

            choiceQuestions.add(choiceQuestion);
        }
    }


//        for(int i=0;i<20;i++){
//            if (s.charAt(i)!=' '&&s.charAt(i)!='\n'){
//                System.out.print("k"+s.charAt(i));
//            }
//            else {
//                System.out.print("p"+s.charAt(i));
//            }
//
//        }


    public static void addPaper(String name) {
        //设置试卷
        paper.setTitle(name);
        paper.setCount(p1.size());
        paper.setDuration(120);
        paper.setSubject("考研专业课-408");
        paper.setTotalMarks(150);
    }
}
