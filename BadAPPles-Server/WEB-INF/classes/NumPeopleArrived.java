import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// How many times have people arrived at their destinations? - arrived, cancelled, assigned

public class NumPeopleArrived extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public NumPeopleArrived() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        Map<String, Integer> map = new HashMap<String, Integer>(); // keys: Arrived, Cancelled, Assigned

        ///*
        File tempFile = new File("webapps/BadAPPles/cache/NumPeopleArrived.csv");
        boolean exists = tempFile.exists();
        if (exists){
            //get results from cache
            BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/cache/NumPeopleArrived.csv"));
            String line = null;
            while ((line = br.readLine()) != null) {
                // read into map
                String[] col = line.split(",");
                String status = col[0].trim();
                String count = col[1].trim();
                boolean notFirstLine = !col[1].equals("Count");
                if(notFirstLine) {
                    Integer cnt = Integer.parseInt(count);
                    map.put(status, cnt);
                }
            }
            br.close();

            //get values
            String[] stringArray = new String[3];
            stringArray[0] = "Arrived: " + String.valueOf(map.get("Arrived"));
            stringArray[1] = "Cancelled: " + String.valueOf(map.get("Cancelled"));
            stringArray[2] = "Assigned: " + String.valueOf(map.get("Assigned"));

            // send results to client
            JSONstring parser = new JSONstring();
            String result = parser.toJSON(stringArray);
            out.println(result);
        }
        else { // do the stuff from previous sprints and at the end create a cache file that stores the counts for all hours not just the top 3
            //*/

            BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/temp/other-Federal_02216.csv"));
            String line = null;

            while ((line = br.readLine()) != null) {
                String[] col = line.split(",");
                String status = col[col.length - 1].trim(); // get the last column

                if (status.equals("Arrived")) {
                    if (map.containsKey(status)) {
                        // just increment count
                        map.replace(status, map.get(status) + 1);
                    } else {
                        // add it and increment count
                        map.put(status, 1);
                    }
                } else if (status.equals("Cancelled")) {
                    if (map.containsKey(status)) {
                        // just increment count
                        map.replace(status, map.get(status) + 1);
                    } else {
                        // add it and increment count
                        map.put(status, 1);
                    }
                } else if (status.equals("Assigned")) {
                    if (map.containsKey(status)) {
                        // just increment count
                        map.replace(status, map.get(status) + 1);
                    } else {
                        // add it and increment count
                        map.put(status, 1);
                    }
                }
            }
            br.close();

            String[] stringArray = new String[3];
            stringArray[0] = "Arrived: " + String.valueOf(map.get("Arrived"));
            stringArray[1] = "Cancelled: " + String.valueOf(map.get("Cancelled"));
            stringArray[2] = "Assigned: " + String.valueOf(map.get("Assigned"));

            JSONstring parser = new JSONstring();
            String result = parser.toJSON(stringArray);
            out.println(result); // send results to client

            //SPRINT6 - create cache file
            ///*
            File myObj = new File("webapps/BadAPPles/cache/NumPeopleArrived.csv");
            if (myObj.createNewFile()) { // File created
                FileWriter myWriter = new FileWriter("webapps/BadAPPles/cache/NumPeopleArrived.csv");
                myWriter.write("Status,Count\n");
                for (Map.Entry<String, Integer> entry : map.entrySet()){
                    myWriter.write(entry.getKey() + "," + entry.getValue() + "\n");
                }
                myWriter.close();
            }
        }
        //*/
    }
}

//  javac -Xlint DataList.java CSVFile.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java