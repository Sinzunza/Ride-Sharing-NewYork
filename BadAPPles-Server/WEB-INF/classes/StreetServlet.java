import java.io.*;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StreetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public StreetServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

// read file into dataList
        File file = new File("webapps/BadAPPles/temp/other-Dial7_B00887.csv");
        InputStream inputStream = new FileInputStream(file);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();
        DataList data = new DataList(csvList);

// search dataList
        long result = data.search(5, "PARK AVE");

// send result
        out.println(result);

        
    }
}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java