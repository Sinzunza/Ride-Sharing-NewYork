import java.io.*;

import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FirstClassServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FirstClassServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

// read file into dataList
        File file = new File("webapps/BadAPPles/temp/other-Firstclass_B01536.csv");
        InputStream inputStream = new FileInputStream(file);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();

// search dataList
        DataList data = new DataList(csvList);
        long result = data.search(0, "7/1/2014");

// send result
        out.println(result);
    }

}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java
