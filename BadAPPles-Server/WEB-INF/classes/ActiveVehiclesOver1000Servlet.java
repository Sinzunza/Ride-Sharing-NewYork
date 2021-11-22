import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ActiveVehiclesOver1000Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ActiveVehiclesOver1000Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //parsing a CSV file into Scanner class constructor
        String line = "";
        String splitBy = ",";
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/temp/uber-Jan-Feb-FOIL.csv"));
            int total = 0;
            while ((line = br.readLine()) != null) {  //returns a Boolean value
                
                String[] column = line.split(splitBy);    // use comma as separator
                PrintWriter out = response.getWriter();
                if (!column[2].contains("active_vehicles")) { // skip first line
                    int i=Integer.parseInt(column[2]); // string to int
                    if (i > 1000) {
                        total += 1;
                    }
                }

                if((line = br.readLine()) == null) {
                    out.println(total);
                }
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java
