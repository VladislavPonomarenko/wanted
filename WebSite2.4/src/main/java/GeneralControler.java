import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class GeneralControler {

    @RequestMapping("")
    public String getFive() throws IOException{
        //PrintStream printStream = new PrintStream("D:\\file.txt");
        BufferedReader reader= new BufferedReader(new InputStreamReader(new FileInputStream("D:\\file.txt")));

        Integer number = Integer.parseInt(reader.readLine());
        reader.close();
        PrintStream printStream = new PrintStream("D:\\file.txt");
        printStream.print(number+1);
        printStream.close();
        return "Тобі хана"+ number +"раз";
    }
}
