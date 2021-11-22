import java.io.*;
import java.util.List;

import java.nio.file.Files;
import java.nio.file.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/BackUp")
public class BackUp extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // reader for user input
        InputStream reader = request.getInputStream();

       // files
        String[] fileNames = {"Uber-Jan-Feb-FOIL", "other-Dial7_B00887", "other-Firstclass_B01536",
                              "uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};

       // parameters from client
        String fileParam = request.getParameter("param1");
        
       // convert necessary parameters to ints
        int fileNum = Integer.parseInt(fileParam);

       //  get file
        final String file = fileNames[fileNum];

       // get time stamp
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
        String timeStamp = now.format(myFormat);

        Path path = Files.copy(Paths.get("webapps/BadApples/temp/" + file + ".csv"), Paths.get("webapps/BadAPPles/backUp-" + fileNum + "/" + file + timeStamp + ".csv"));

    }
}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java
