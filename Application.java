/**
 * Created by Влад on 25.04.2016.
 */

import org.apache.tomcat.jni.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@EnableAutoConfiguration
@Controller
public class Application {

    List<Human> humans = new ArrayList<Human>();//...//"Clean Code" Robert Martin

    @RequestMapping("/index")
    public String addNewHuman(HttpServletRequest request) {
        Human h1 = new Human();
        h1.FirstName = request.getParameter("FirstName");
        h1.LastName = request.getParameter("LastName");
        h1.phone = request.getParameter("phone");
        h1.address = request.getParameter("address");
        humans.add(h1);
        System.out.println(readTextFromFile("D:\\file.txt.txt"));

        return "redirect:/";


    }

    public static void writeTextFromFile(String filePath, String text) {
        try {

            FileWriter writer = new FileWriter("D:\\file.txt.txt",true);

            writer.write(text);
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Some Input/Output exception.");
        }
    }

    public static String readTextFromFile(String filePath) {
        String res = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            int oneChar;
            while ((oneChar = fileInputStream.read()) != -1) {
                res += (char)oneChar;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Some Input/Output exception.");
        }
        return res;
    }
    @RequestMapping("")
    @ResponseBody
    public String showAll() {
        String res = "";

        res += "<form action='/index' method='post'>" +
                "<table>" +
                "   <tr>" +
                "    <td align='left'>First Name:</td>" +
                "    <td><input type=text name=FirstName size=25></td>" +
                "   </tr>" +
                "   <tr>" +
                "    <td align='left' >Last Name:</td>" +
                "    <td><input type=text name=LastName size=25></td>" +
                "   </tr><br>" +
                "<tr>" +
                "  <td align='left'>Phone number:</td>" +
                "  <td><input type=text name=phone size=25></td>" +
                " </tr><br>" +
                " <tr>" +
                "  <td align='left'>Address:</td>" +
                "  <td><input type=text name=address size=25></td>" +
                " </tr><br>" +
                "</table>" +
                "<input type=submit style='width:312px;' name=submit value='Add' >" +
                "</form>";
        for (Human human : humans) {
            res +="<form  style='border: solid 1px #aaa; border-radius: 3px; margin-bottom: 10px; padding: 5px; width: 280px;'>"+ human.FirstName + " ";
            res += human.LastName + "<br>";
            res += "Telephone number: " + human.phone + "<br>";
            res += "Address: " + "<em>" + human.address + "</em>" + "</form>";

            writeTextFromFile("D:\\file.txt.txt", human.FirstName+" "+human.LastName+"\n"+"Telephone number: "+human.phone+"\n"+"Address: "+human.address+"\n");

        }

        return res;

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}