import java.io.*;
import java.util.List;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/Insert")
public class Insert extends HttpServlet{
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
        String newRow = request.getParameter("param2");
        
        // convert necessary parameters to ints
        int fileNum = Integer.parseInt(fileParam);

        String splitBy = ",";
        String line[] = newRow.split(splitBy);

        String file = fileNames[fileNum] + ".csv";

        // create file and load into dataList
        File newFile = new File("webapps/BadAPPles/temp/" + file);
        InputStream inputStream = new FileInputStream(newFile);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();
        DataList data = new DataList(csvList);
        
        data.insert(line);

        // delete old file
        CleanDir clean = new CleanDir();
        clean.removeFile(new File("webapps/BadAPPles/temp/" + file));

        // write the dataList to the backup
        csvFile.write(data.getData(), "webapps/BadAPPles/temp/" + file);
    
        // update cache if it exists
        if (fileNum == 1) { // Dial
            File Top3Hours = new File("webapps/BadAPPles/cache/Top3Hours.csv");
            File Top3Streets = new File("webapps/BadAPPles/cache/Top3Streets.csv");
            File TopStreetsTime = new File("webapps/BadAPPles/cache/TopStreetsTime.csv");
            if (Top3Hours.exists()) {
                String[] newValues = {line[1].split(":")[0].trim()};
                System.out.println("New value = " + line[1].trim());
                int[] columns = {0};
                incrementCache(Top3Hours, "webapps/BadAPPles/cache/Top3Hours.csv", newValues, columns);
            }
            if (Top3Streets.exists()) {
                String[] newValues = {line[5].trim()};
                int[] columns = {0};
                incrementCache(Top3Streets, "webapps/BadAPPles/cache/Top3Streets.csv", newValues, columns);
            }
            if (TopStreetsTime.exists()){
                int newTime = Integer.parseInt(line[1].split(":")[0].trim());
                int newInterval = 0;
                if (newTime < 8) {
                    newInterval = 1;
                }
                else if (newTime < 16) {
                    newInterval = 2;
                }
                else if (newTime < 24) {
                    newInterval = 3;
                }
                String streetName = line[5].trim();
                String[] newValues = {String.valueOf(newInterval), streetName};
                int[] columns = {0,1};
                incrementCache(TopStreetsTime, "webapps/BadAPPles/cache/TopStreetsTime.csv", newValues, columns);
            }
        }
        else if (fileNum == 2 || fileNum == 5) { // FirstClass or HighClass
            File TopVehicleAM = new File("webapps/BadAPPles/cache/TopVehicleAM.csv");
            File TopVehiclePM = new File("webapps/BadAPPles/cache/TopVehiclePM.csv");
            String newTime = line[1].split(":")[0].trim();

            System.out.println("topVehicle here");

            if (TopVehicleAM.exists() && line[1].contains("AM")) { // if file exists and time column was updated
                String vehicleClass;
                if (fileNum == 2) {
                    System.out.println("first class");
                    vehicleClass = "FirstClass";
                }
                else {
                    System.out.println("high class");
                    vehicleClass = "HighClass";
                }
                if (line[1].contains("AM")){
                    // increment old value
                    String[] values = {newTime, vehicleClass};
                    int[] columns = {0, 1};
                    incrementCache(TopVehicleAM, "webapps/BadAPPles/cache/TopVehicleAM.csv", values, columns);
                }
            }
            if (TopVehiclePM.exists() &&  line[1].contains("PM")) { // if file exists and time column was updated
                String vehicleClass;
                if (fileNum == 2) {
                    System.out.println("first class");
                    vehicleClass = "FirstClass";
                }
                else {
                    System.out.println("high class");
                    vehicleClass = "HighClass";
                }

                if (line[1].contains("PM")){
                    // increment old value
                    String[] values = {newTime, vehicleClass};
                    int[] columns = {0, 1};
                    incrementCache(TopVehiclePM, "webapps/BadAPPles/cache/TopVehiclePM.csv", values, columns);
                }

            }
        }
        else if (fileNum == 4) { // Federal (index 6)
            File NumPeopleArrived = new File("webapps/BadAPPles/cache/NumPeopleArrived.csv");
            if (NumPeopleArrived.exists()) {
                String[] newValues = {line[line.length-1].trim()};
                int[] columns = {0};
                incrementCache(NumPeopleArrived, "webapps/BadAPPles/cache/NumPeopleArrived.csv", newValues, columns);
             }

        }
        else if (fileNum == 3) { // apr
            File LatLong = new File("webapps/BadAPPles/cache/LatLong.csv");
            if (LatLong.exists()) {
                String[] newValues = {line[1].trim() + line[2].trim()};
                int[] columns = {0};
                incrementCache(LatLong, "webapps/BadAPPles/cache/LatLong.csv", newValues, columns);
            }
        }
        reader.close();
        inputStream.close();
    }

    void incrementCache(File file, String fileName, String[] values, int[] columns) {
        try {
        // create file and load into dataList
            InputStream inputStream = new FileInputStream(file);
            CSVFile csvFile = new CSVFile(inputStream);
            List<String[]> csvList = csvFile.read();
            DataList data = new DataList(csvList);

            boolean isNewValue = true;
            for (int i = 0; i < data.getData().size(); i++) {
                
                boolean isRow = true;

                for (int j = 0; j < values.length; j++) {

                    if (!data.getData().get(i)[columns[j]].trim().equals(values[j])){
                        isRow = false;
                        break;
                    }

                }

                if (isRow) {
                    System.out.println("found equal row");
                    int countIndex = data.getData().get(0).length-1;
                    int count = Integer.parseInt(data.getData().get(i)[countIndex].trim());
                    count++;
                    data.update(i, countIndex, String.valueOf(count));
                    isNewValue = false;
                    break;
                }

            }
            if (isNewValue){ // if new value isn't an existing value then add it to the cache

                System.out.println("new cache value");

                String[] newRow = new String[values.length+1];
                for (int i = 0; i < values.length; i++) {
                    newRow[i] = values[i];
                }
                newRow[values.length] = "1";
                data.getData().add(newRow);
            }

            // write the dataList to the backup
            csvFile.write(data.getData(), fileName);

            inputStream.close();
            
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        } 
    }
}

//  javac -Xlint DataList.java CSVFile.java JSONstring.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java