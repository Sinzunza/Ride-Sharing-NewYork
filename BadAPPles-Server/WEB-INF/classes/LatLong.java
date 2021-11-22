import java.io.*;
import java.util.*;

import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LatLong extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LatLong() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       // parsing a CSV file into Scanner class constructor
        
        String line = "";

        PrintWriter out = response.getWriter();
        
        Map<String, Integer> LatLong1 = new HashMap<String, Integer>();
        ArrayList<String> time1 = new ArrayList<String>();

        List<String[]> cachedResults = new ArrayList();
        File file = new File("webapps/BadAPPles/cache/LatLong.csv");

        try {
            if (file.exists()) {

                System.out.println("LatLong Cache exists.");

                BufferedReader br = new BufferedReader(new FileReader(file));
                br.readLine();

                while ((line = br.readLine()) != null)   //returns a Boolean value {

                    String[] column = line.split(",");

                    int i = Integer.parseInt(column[1].trim());

                    LatLong1.put(column[0].trim(), i);

                }
                br.close();

                List<String> keys = LatLong1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
                int maxValueIntMap1 = (Collections.max(LatLong1.values()));
                LatLong1.replace(keys.get(0), 1);
                int maxValueIntMap2 = (Collections.max(LatLong1.values()));
                LatLong1.replace(keys.get(1), 1);
                int maxValueIntMap3 = (Collections.max(LatLong1.values()));
                LatLong1.replace(keys.get(2), 1);

                String[] stringArray = new String[3];
                stringArray[0] = keys.get(0) + ": " + String.valueOf(maxValueIntMap1);
                stringArray[1] = keys.get(1) + ": " + String.valueOf(maxValueIntMap2);
                stringArray[2] = keys.get(2) + ": " + String.valueOf(maxValueIntMap3);

                JSONstring parser = new JSONstring();
                String result = parser.toJSON(stringArray);
                out.println(result);

            }
            else {
                File file1 = new File("webapps/BadAPPles/backUp-3/uber-raw-data-apr14.csv");
                InputStream inputStream = new FileInputStream(file1);
                CSVFile csvFile = new CSVFile(inputStream);
                List<String[]> csvList = csvFile.read();
                DataList data = new DataList(csvList);

                file.createNewFile();
                
                BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/backUp-3/uber-raw-data-apr14.csv"));
                while ((line = br.readLine()) != null) {  // returns a Boolean value
                    
                    String[] employee = line.split(",");  // use comma as separator

                    String temp = employee[1].trim() + employee[2].trim(); // Lat + Long

                    if (LatLong1.containsKey(temp)) { //true or false
                        LatLong1.replace(temp, LatLong1.get(temp) + 1);
                    } else {
                        LatLong1.put(temp, 1);
                    }


                }
                br.close();

                String[] columns = {"Lat-Long", "Count"};
                cachedResults.add(columns);

                for (Map.Entry<String, Integer> pair : LatLong1.entrySet()) {
                    int i = 0;
                    String temp = pair.getKey();
                    i++;
                    String[] values = {temp, String.valueOf(pair.getValue())};
                    cachedResults.add(values);
                }

                DataList dataCache = new DataList(cachedResults);
                csvFile.write(dataCache.getData(), "webapps/BadAPPles/cache/LatLong.csv");

                List<String> keys = LatLong1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(3).map(Map.Entry::getKey).collect(Collectors.toList());
                int maxValueIntMap1 = (Collections.max(LatLong1.values()));
                LatLong1.replace(keys.get(0), 1);
                int maxValueIntMap2 = (Collections.max(LatLong1.values()));
                LatLong1.replace(keys.get(1), 1);
                int maxValueIntMap3 = (Collections.max(LatLong1.values()));
                LatLong1.replace(keys.get(2), 1);

                String[] stringArray = new String[3];
                stringArray[0] = keys.get(0) + ": " + String.valueOf(maxValueIntMap1);
                stringArray[1] = keys.get(1) + ": " + String.valueOf(maxValueIntMap2);
                stringArray[2] = keys.get(2) + ": " + String.valueOf(maxValueIntMap3);

                JSONstring parser = new JSONstring();
                String result = parser.toJSON(stringArray);

                inputStream.close();
                
                // write to file
                out.println(result);

                }

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
