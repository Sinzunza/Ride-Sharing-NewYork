import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SendRow extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SendRow() {
        super();
    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        InputStream reader = request.getInputStream();

       // parameters from client
        String file = request.getParameter("param1");
        String row = request.getParameter("param2");

       // String to int
        int fileNum = Integer.parseInt(file);
        int rowNum = Integer.parseInt(row);

        String[] fileNames = {"Uber-Jan-Feb-FOIL", "other-Dial7_B00887", "other-Firstclass_B01536",
                              "uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};

        
        String newFile = fileNames[fileNum] + ".csv";

        File tempFile = new File("webapps/BadAPPles/temp/" + newFile);
        InputStream inputStream = new FileInputStream(tempFile);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();

        String result = "";
        for (int i = 0; i < csvList.get(rowNum).length; i++) {
            if (i != (csvList.get(rowNum).length - 1)) {
                result += " || " + csvList.get(rowNum)[i].trim();
            }
            else {
                result += csvList.get(rowNum)[i].trim();
            }
        }

        result += " ||";
        
        reader.close();
        out.println(result); 

    }
}

//  javac -Xlint DataList.java CSVFile.java JSONstring.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java
