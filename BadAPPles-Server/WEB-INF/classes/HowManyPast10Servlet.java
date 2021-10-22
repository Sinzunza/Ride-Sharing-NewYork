import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HowManyPast10Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public HowManyPast10Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //parsing a CSV file into Scanner class constructor
        String line = "";
        String splitBy = ",";
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/temp/uber-raw-data-apr14.csv"));
            int total = 0;
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] column = line.split(splitBy);    // use comma as separator
                PrintWriter out = response.getWriter();
                if((column[0].contains(" 22:")) || column[0].contains(" 23:")){ // 10:00pm to 11:59pm
                    total += 1;
                }
                if((line = br.readLine()) == null){
                    out.println(total);
                }
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java