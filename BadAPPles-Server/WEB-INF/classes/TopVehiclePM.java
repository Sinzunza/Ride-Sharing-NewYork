import java.io.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// # of requested vehicles at a certain time

public class TopVehiclePM extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TopVehiclePM() {
        super();
    }

    private static final String col_separator = " , ";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();

        Map<String, Integer> vehicleType = new HashMap<String, Integer>();  //OVERALL FIRSTCLASS & HIGHCLASS for new csv

        Map<String, Integer> firstClass1 = new HashMap<String, Integer>();  //AM FIRSTCLASS 0-6
        Map<String, Integer> firstClass2 = new HashMap<String, Integer>();  //AM FIRSTCLASS 7-12

        Map<String, Integer> highClass1 = new HashMap<String, Integer>();   //AM HIGHCLASS 0-6
        Map<String, Integer> highClass2 = new HashMap<String, Integer>();   //AM HIGHCLASS 7-12

        File file = new File("webapps/BadAPPles/cache/TopVehiclePM.csv");

        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/cache/TopVehiclePM.csv"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    String[] col = line.split(",");
                    String time = col[0].trim();
                    String hour = time.split(":")[0].trim();
                    String vehicle = col[1].trim();
                    String count = col[2].trim();

                    boolean notFirstLine = !col[0].equals("Time");

                   // skip first line of csv
                    if(notFirstLine){
                        Integer cnt = Integer.parseInt(count);
                        vehicleType.put(vehicle, cnt);
                    }

                    NumberFormat nf = NumberFormat.getInstance();

                    try {
                        int x = (int) nf.parse(hour).doubleValue();
                        int y = (int) nf.parse(count).doubleValue();

                        if ((x < 6 || x == 12) && vehicle.contains("FirstClass")) {
                            if (firstClass1.containsKey(hour)) {
                                firstClass1.replace(hour, firstClass1.get(hour) + 1);
                            } 
                            else {
                                firstClass1.put(hour, y);
                            }
                        }

                        else if (x < 12 && vehicle.contains("FirstClass")) {
                            if (firstClass2.containsKey(hour)) {
                                firstClass2.replace(hour, firstClass2.get(hour) + 1);
                            } 
                            else {
                                firstClass2.put(hour, y);
                            }
                        }

                        else if ((x < 6 || x == 12) && vehicle.contains("HighClass")) {
                            if (highClass1.containsKey(hour)) {
                                highClass1.replace(hour, highClass1.get(hour) + 1);
                            } 
                            else {
                                highClass1.put(hour, y);
                            }
                        }

                        else if (x < 12 && vehicle.contains("HighClass")) {
                            if (highClass2.containsKey(hour)) {
                                highClass2.replace(hour, highClass2.get(hour) + 1);
                            } 
                            else {
                                highClass2.put(hour, y);
                            }
                        }
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                br.close();

                List<String> key1 = firstClass1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());
                List<String> key2 = highClass1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());
                List<String> key3 = firstClass2.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());
                List<String> key4 = highClass2.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());

                int maxValueInMap1 = (Collections.max(firstClass1.values()));
                int maxValueInMap2 = (Collections.max(highClass1.values()));
                int maxValueInMap3 = (Collections.max(firstClass2.values()));
                int maxValueInMap4 = (Collections.max(highClass2.values()));

                String[] stringArray = new String[4];

                stringArray[0] = "FC-" + key1.get(0) + ": " + String.valueOf(maxValueInMap1);
                stringArray[1] = "HC-" + key2.get(0) + ": " + String.valueOf(maxValueInMap2);
                stringArray[2] = "FC-" + key3.get(0) + ": " + String.valueOf(maxValueInMap3);
                stringArray[3] = "HC-" + key4.get(0) + ": " + String.valueOf(maxValueInMap4);

                JSONstring parser = new JSONstring();
                String result = parser.toJSON(stringArray);
                out.println(result);
            }
            else {
                BufferedReader br = new BufferedReader(new FileReader("webapps/BadAPPles/temp/other-Firstclass_B01536.csv"));
                BufferedReader br2 = new BufferedReader(new FileReader("webapps/BadAPPles/temp/other-Highclass_B01717.csv"));
                String line = null;

                while ((line = br.readLine()) != null) {
                    String[] col = line.split(",");
                    String time = col[1].trim();
                    String hour = time.split(":")[0].trim();

                    NumberFormat nf = NumberFormat.getInstance();

                    try {
                        int x = (int) nf.parse(hour).doubleValue();

                        if (time.trim().isEmpty()) { 
                        }
                        else if ((x < 6 || x == 12) && time.contains("PM")) {
                            if (firstClass1.containsKey(hour)) {
                                firstClass1.replace(hour, firstClass1.get(hour) + 1);
                            } 
                            else {
                                firstClass1.put(hour, 1);
                            }
                        }

                        else if (x < 12 && time.contains("PM")) {
                            if (firstClass2.containsKey(hour)) {
                                firstClass2.replace(hour, firstClass2.get(hour) + 1);
                            } 
                            else {
                                firstClass2.put(hour, 1);
                            }
                        }
                    } 
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                br.close();

                while ((line = br2.readLine()) != null) {
                    String[] col = line.split(",");
                    String time = col[1].trim();
                    String hour = time.split(":")[0].trim();

                    NumberFormat nf = NumberFormat.getInstance();

                    try {
                        int x = (int) nf.parse(hour).doubleValue();

                        if (time.trim().isEmpty()) { 
                        }
                        else if ((x < 6 || x == 12) && time.contains("PM")) {
                            if (highClass1.containsKey(hour)) {
                                highClass1.replace(hour, highClass1.get(hour) + 1);
                            } 
                            else {
                                highClass1.put(hour, 1);
                            }
                        } else if (x < 12 && time.contains("PM")) {
                            if (highClass2.containsKey(hour)) {
                                highClass2.replace(hour, highClass2.get(hour) + 1);
                            } 
                            else {
                                highClass2.put(hour, 1);
                            }
                        }
                    } 
                    catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                br2.close();

                List<String> key1 = firstClass1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());
                List<String> key2 = highClass1.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());
                List<String> key3 = firstClass2.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());
                List<String> key4 = highClass2.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).limit(1).map(Map.Entry::getKey).collect(Collectors.toList());

                int maxValueInMap1 = (Collections.max(firstClass1.values()));
                int maxValueInMap2 = (Collections.max(highClass1.values()));
                int maxValueInMap3 = (Collections.max(firstClass2.values()));
                int maxValueInMap4 = (Collections.max(highClass2.values()));

                String[] stringArray = new String[4];

                stringArray[0] = "FC-" + key1.get(0) + ": " + String.valueOf(maxValueInMap1);
                stringArray[1] = "HC-" + key2.get(0) + ": " + String.valueOf(maxValueInMap2);
                stringArray[2] = "FC-" + key3.get(0) + ": " + String.valueOf(maxValueInMap3);
                stringArray[3] = "HC-" + key4.get(0) + ": " + String.valueOf(maxValueInMap4);

                JSONstring parser = new JSONstring();
                String result = parser.toJSON(stringArray);
                out.println(result); //send to client initial

                //write cache
                File myObj = new File("webapps/BadAPPles/cache/TopVehiclePM.csv");

                if (myObj.createNewFile()){
                    FileWriter myWriter = new FileWriter("webapps/BadAPPles/cache/TopVehiclePM.csv");
                    myWriter.write("Time, Vehicle Type, Count\n"); //add time

                    for (Map.Entry<String, Integer> entry : firstClass1.entrySet()) {  // 0-6PM
                        myWriter.write(entry.getKey() + "," + "FirstClass" + "," + entry.getValue() + "\n");
                    }
                    for (Map.Entry<String, Integer> entry : highClass1.entrySet()) {
                        myWriter.write(entry.getKey() + "," + "HighClass" + "," + entry.getValue() + "\n");
                    }
                    for (Map.Entry<String, Integer> entry : firstClass2.entrySet()) {  // 7-12 PM
                        myWriter.write(entry.getKey() + "," + "FirstClass" + "," + entry.getValue() + "\n");
                    }
                    for (Map.Entry<String, Integer> entry : highClass2.entrySet()) {
                        myWriter.write(entry.getKey() + "," + "HighClass" + "," + entry.getValue() + "\n");
                    }
                    myWriter.close();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//javac -Xlint CSVFile.java -cp ../../../../lib/servlet-api.jar JSONstring.java DataList.java TopVehicleAM.java
