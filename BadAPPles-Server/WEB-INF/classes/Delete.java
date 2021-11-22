import java.io.*;
import java.util.List;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/Delete")
public class Delete extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        InputStream reader = request.getInputStream();

        String[] fileNames = {"Uber-Jan-Feb-FOIL", "other-Dial7_B00887", "other-Firstclass_B01536",
                              "uber-raw-data-apr14","other-Federal_02216", "other-Highclass_B01717"};

        String pathToTemp = "webapps/BadAPPles/temp/";
        String LatLongCache = "webapps/BadAPPles/cache/LatLong.csv";
        String Top3HoursCache = "webapps/BadAPPles/cache/Top3Hours.csv";
        String Top3StreetsCache = "webapps/BadAPPles/cache/Top3Streets.csv";
        String TopVehicleAMCache = "webapps/BadAPPles/cache/TopVehicleAM.csv";
        String TopVehiclePMCache = "webapps/BadAPPles/cache/TopVehiclePM.csv";
        String TopStreetsTimeCache = "webapps/BadAPPles/cache/TopStreetsTime.csv";
        String NumPeopleArrivedCache = "webapps/BadAPPles/cache/NumPeopleArrived.csv";
        
        // parameters from client
        String fileParam = request.getParameter("param1");
        String rowParam = request.getParameter("param2");

        // convert necessary parameters to ints
        int fileNum = Integer.parseInt(fileParam);
        int row = Integer.parseInt(rowParam);

        // file name
        String file = fileNames[fileNum] + ".csv";

        // create file and load into dataList
        File newFile = new File(pathToTemp + file);
        InputStream inputStream = new FileInputStream(newFile);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> csvList = csvFile.read();
        DataList data = new DataList(csvList);

        String[] line = new String[data.getData().get(row).length];
        for (int i = 0; i < line.length; i++) {
            line[i] = data.getData().get(row)[i];
        }

        // delete old file
        CleanDir clean = new CleanDir();
        clean.removeFile(new File(pathToTemp + file));

        // write the dataList to the backup
        csvFile.write(data.getData(), pathToTemp + file);

        // update cache if it exists
        if (fileNum == 1) { // Dial7
            File Top3Hours = new File(Top3HoursCache);
            File Top3Streets = new File(Top3StreetsCache);
            File TopStreetsTime = new File(TopStreetsTimeCache);
            if (Top3Hours.exists()) { // if file exists and time column was updated
                String oldTime = line[1].split(":")[0].trim();
                String[] oldValues = {oldTime};
                int[] columns = {0};
                decrementCache(Top3Hours, Top3HoursCache, oldValues, columns);
            }
            if (Top3Streets.exists()) { // if file exists and street column was updated  (index 5)
                String oldStreet = line[5].trim();
                String[] oldValues = {oldStreet};
                int[] columns = {0};
                decrementCache(Top3Streets, Top3StreetsCache, oldValues, columns);
            }
            if (TopStreetsTime.exists()){ // if file exists and either street or time columns were updated
                String streetName = line[5].trim();
                int oldTime = Integer.parseInt(line[1].split(":")[0].trim());

                int oldInterval = 0;
                if (oldTime < 8) {
                    oldInterval = 1;
                }
                else if (oldTime < 16) {
                    oldInterval = 2;
                }
                else if (oldTime < 24) {
                    oldInterval = 3;
                }
                String[] oldValues = {String.valueOf(oldInterval), streetName};
                int[] columns = {0,1};
                decrementCache(TopStreetsTime, TopStreetsTimeCache, oldValues, columns);
            }
        }
        else if (fileNum == 2 || fileNum == 5) { // FirstClass or HighClass
            File TopVehicleAM = new File(TopVehicleAMCache);
            File TopVehiclePM = new File(TopVehiclePMCache);

            String oldTime = line[1].split(":")[0].trim();

            if (TopVehicleAM.exists() && line[1].contains("AM") ) { // if file exists and time column was updated
                String vehicleClass;
                if (fileNum == 2) {
                    vehicleClass = "FirstClass";
                }
                else {
                    vehicleClass = "HighClass";
                }
                // decrement old value
                String[] values = {oldTime, vehicleClass}  ;
                int[] columns = {0, 1};
                decrementCache(TopVehicleAM, TopVehicleAMCache, values, columns);
            }
            if (TopVehiclePM.exists() && line[1].contains("PM")) { // if file exists and time column was updated
                String vehicleClass;
                if (fileNum == 2) {
                    vehicleClass = "FirstClass";
                }
                else {
                    vehicleClass = "HighClass";
                }
                // decrement old value
                String[] values = {oldTime, vehicleClass};
                int[] columns = {0, 1};
                decrementCache(TopVehiclePM, TopVehiclePMCache, values, columns);
            }
        }
        else if (fileNum == 4) { // Federal (index 6)
            File NumPeopleArrived = new File(NumPeopleArrivedCache);
            if (NumPeopleArrived.exists()) {
                String[] oldValues = {line[line.length-1].trim()};
                int[] columns = {0};
                decrementCache(NumPeopleArrived, NumPeopleArrivedCache, oldValues, columns);
             }
        }
        else if (fileNum == 3) { // apr
        System.out.println("latlong here");
            File LatLong = new File(LatLongCache);
            if (LatLong.exists()) {
                System.out.println("latlong exists");
                String[] oldValues = {line[1].trim() + line[2].trim()};
                int[] columns = {0};
                decrementCache(LatLong, LatLongCache, oldValues, columns);
            }
        }
        
        reader.close();
        inputStream.close();
        
    }

    ///////////// Helper Functions

    void decrementCache(File file, String fileName, String[] values, int[] columns) {
        try {
            // create file and load into dataList
            InputStream inputStream = new FileInputStream(file);
            CSVFile csvFile = new CSVFile(inputStream);
            List<String[]> csvList = csvFile.read();
            DataList data = new DataList(csvList);

            for (int i = 0; i < data.getData().size(); i++) {
                boolean isRow = true;
                for (int j = 0; j < values.length; j++) {
                    if (!data.getData().get(i)[columns[j]].trim().equals(values[j])){
                        isRow = false;
                        break;
                    }
                }
                if (isRow) { // does not enter without setting isRow = true when we do find a match
                    int countIndex = data.getData().get(i).length - 1; // this had get(0) instead of get(i). nvm it doesnt matter for length of row
                    int count = Integer.parseInt(data.getData().get(i)[countIndex].trim()); // needed .trim()
                    count--;
                    data.update(i, countIndex, String.valueOf(count));
                    break;
                }
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
