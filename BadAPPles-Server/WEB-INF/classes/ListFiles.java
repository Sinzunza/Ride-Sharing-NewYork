import java.io.*;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListFiles extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListFiles() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        // Parameters from client
        String fileParam = request.getParameter("param1");

        // Convert necessary parameters to ints
        int fileNum = (int) Double.parseDouble(fileParam);
        
        // Creating a File object for directory
        File directoryPath = new File("webapps/BadAPPles/backUp-" + fileNum);

        // List of all files in directories
        String contents[] = directoryPath.list();
        String importf = String.join(",", contents);

        out.println(importf);

    }

}

//  javac -Xlint -cp ../../../../lib/servlet-api.jar ImportOtherFirstClass.java