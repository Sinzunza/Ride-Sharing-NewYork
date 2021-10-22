import java.io.*;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/CreateTempServlet")
public class CreateTempServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        // reader for user input
        InputStream reader = request.getInputStream();

        // files
        String[] fileNames = {"Uber-Jan-Feb-FOIL", "other-Dial7_B00887", "other-Firstclass_B01536",
                              "uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};

        // parameters from client
        String fileParam = request.getParameter("param1");
        String backUpFile = request.getParameter("param2");

        // convert necessary parameters to ints
        int fileNum = (int) Double.parseDouble(fileParam);

        String file;
        CleanDir clean = new CleanDir();
        File oldFile;
        
        //store backUp file into temp folder
        switch (fileNum){
            case 0:
                file = fileNames[fileNum] + ".csv";
                clean.removeFile(new File("webapps/BadAPPles/temp/" + file));
                Files.copy(Paths.get("webapps/BadAPPles/backUp-0/" + backUpFile),
                    Paths.get("webapps/BadAPPles/temp/" + file));
                break;
            case 1:
                file = fileNames[fileNum] + ".csv";
                clean.removeFile(new File("webapps/BadAPPles/temp/" + file));
                Files.copy(Paths.get("webapps/BadAPPles/backUp-1/" + backUpFile),
                    Paths.get("webapps/BadAPPles/temp/" + file));
                break;
            case 2:
                file = fileNames[fileNum] + ".csv";
                clean.removeFile(new File("webapps/BadAPPles/temp/" + file));
                Files.copy(Paths.get("webapps/BadAPPles/backUp-2/" + backUpFile),
                    Paths.get("webapps/BadAPPles/temp/" + file));
                break;
            case 3:
                file = fileNames[fileNum] + ".csv";
                clean.removeFile(new File("webapps/BadAPPles/temp/" + file));
                Files.copy(Paths.get("webapps/BadAPPles/backUp-3/" + backUpFile),
                    Paths.get("webapps/BadAPPles/temp/" + file));
                break;
            case 4:
                file = fileNames[fileNum] + ".csv";
                clean.removeFile(new File("webapps/BadAPPles/temp/" + file));
                Files.copy(Paths.get("webapps/BadAPPles/backUp-4/" + backUpFile),
                    Paths.get("webapps/BadAPPles/temp/" + file));
                break;
            case 5:
                file = fileNames[fileNum] + ".csv";
                clean.removeFile(new File("webapps/BadAPPles/temp/" + file));
                Files.copy(Paths.get("webapps/BadAPPles/backUp-5/" + backUpFile),
                    Paths.get("webapps/BadAPPles/temp/" + file));
                break;
            case 100:

                // clean cache of any old files
                File dirCache = new File("webapps/BadAPPles/cache");
                clean.cleanDirectory(dirCache);

                // clean temp of any old files
                File dirTemp = new File("webapps/BadAPPles/temp");
                clean.cleanDirectory(dirTemp);

                // move all original files into temp folder
                file = fileNames[0] + ".csv";
                Files.copy(Paths.get("webapps/BadAPPles/backUp-0/" + file),
                    Paths.get("webapps/BadAPPles/temp/" + file));

                file = fileNames[1] + ".csv";
                Files.copy(Paths.get("webapps/BadAPPles/backUp-1/" + file),
                    Paths.get("webapps/BadAPPles/temp/" + file));

                file = fileNames[2] + ".csv";
                Files.copy(Paths.get("webapps/BadAPPles/backUp-2/" + file),
                    Paths.get("webapps/BadAPPles/temp/" + file));

                file = fileNames[3] + ".csv";
                Files.copy(Paths.get("webapps/BadAPPles/backUp-3/" + file),
                    Paths.get("webapps/BadAPPles/temp/" + file));

                file = fileNames[4] + ".csv";
                Files.copy(Paths.get("webapps/BadAPPles/backUp-4/" + file),
                    Paths.get("webapps/BadAPPles/temp/" + file));

                file = fileNames[5] + ".csv";
                Files.copy(Paths.get("webapps/BadAPPles/backUp-5/" + file),
                    Paths.get("webapps/BadAPPles/temp/" + file));

                break;
        }

    }
}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java
