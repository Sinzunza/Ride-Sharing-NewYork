import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Top3Hours extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Top3Hours() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();    

        File oldCache = new File("webapps/BadAPPles/cache/Top3Hours.csv");    

        if (oldCache.exists()) {

        // read file into dataList
            InputStream inputStream = new FileInputStream(oldCache);
            CSVFile csvFile = new CSVFile(inputStream);
            List<String[]> csvList = csvFile.read();
            DataList data = new DataList(csvList);


        // create array to store distinct times and their counts
            ArrayList<String> distinctTimes = new ArrayList<String>();
            ArrayList<Integer> distinctTimesCnt = new ArrayList<Integer>();
            

        // get distinct times and their counts
            for(int i = 1; i < data.getData().size(); i++){
                String tempTime = data.getData().get(i)[0].trim();
                int tempCnt = Integer.parseInt(data.getData().get(i)[1].trim());
                distinctTimes.add(tempTime);
                distinctTimesCnt.add(tempCnt);
            }

        // create result (top3)
            String[] top3 = new String[3];
            int top3Count = 0;
            for(int i = 0; i < 3; i++) {
                int max = Collections.max(distinctTimesCnt);
                int index = distinctTimesCnt.indexOf(max);
                String time = distinctTimes.get(index);
                top3[i] = time + ": " + String.valueOf(max);
                distinctTimesCnt.remove(index);
                distinctTimes.remove(index);
            }

        // parse array into JSON string
            JSONstring parser = new JSONstring();
            String result = parser.toJSON(top3);

            inputStream.close();

        // send results to client
            out.println(result);

        }
        else {

        // read file into dataList
            File file = new File("webapps/BadAPPles/temp/other-Dial7_B00887.csv");
            InputStream inputStream = new FileInputStream(file);
            CSVFile csvFile = new CSVFile(inputStream);
            List<String[]> csvList = csvFile.read();
            DataList data = new DataList(csvList);


        // create array to store distinct times and their counts
            ArrayList<String> distinctTimes = new ArrayList<String>();
            ArrayList<Integer> distinctTimesCnt = new ArrayList<Integer>();
            

        // get distinct times and their counts
            for(int i = 1; i < data.getData().size(); i++){
                String tempTime = data.getData().get(i)[1].split(":")[0].trim();
                if ( !distinctTimes.contains(tempTime) ) {
                    distinctTimes.add(tempTime);
                    distinctTimesCnt.add(1);
                }
                else {
                    int index = distinctTimes.indexOf(tempTime);
                    int value = distinctTimesCnt.get(index) + 1;
                    distinctTimesCnt.set(index, value);
                }
            }

        // create result (top3) & cache data structures
            List<String[]> cachedResults = new ArrayList<String[]>();
            String[] columns = {"Hour", "Count"};
            cachedResults.add(columns);
            String[] top3 = new String[3];
            int top3Count = 0;
            for(int i = 0; i < distinctTimes.size() ; i++) {
                int max = Collections.max(distinctTimesCnt);
                int index = distinctTimesCnt.indexOf(max);
                String time = distinctTimes.get(index);
                if (i < 3) {
                    top3[i] = time + ": " + String.valueOf(max);
                }
                String[] values = {time, String.valueOf(max)};
                cachedResults.add(values);
                distinctTimesCnt.remove(index);
                distinctTimes.remove(index);
            }

        // parse array into JSON string
            JSONstring parser = new JSONstring();
            String result = parser.toJSON(top3);

            inputStream.close();

        // send results to client
            out.println(result);


        // create DataList object from cachedResults
            DataList dataCache = new DataList(cachedResults);

        // create cache file
            File fileCache = new File("webapps/BadAPPles/cache/Top3Hours.csv");
            fileCache.createNewFile();

        // write the DataList to the cache file
            csvFile.write(dataCache.getData(), "webapps/BadAPPles/cache/Top3Hours.csv");

        }

    }
}

//  javac -Xlint DataList.java CSVFile.java JSONstring.java CleanDir.java -cp ../../../../lib/servlet-api.jar *.java