import java.io.InputStream;
import java.util.List;

public class DataList {

    List<String[]> data;

    public DataList(List<String[]> data) {
        this.data = data;
    }

    public long search(int column, String search) {
        long result = 0;
        for(int i = 0; i < data.size(); i++) {
            if(data.get(i)[column].equals(search) || data.get(i)[column].contains(search)){
                result++;
            }
        }
        return result;
    }

    public void insert( String[] line ) {
        String[] newLine = new String[line.length - 1];
        for (int i = 0; i < line.length-1; i++){
            newLine[i] = line [i+1];
        }
        data.add(newLine);
    }

    public void update(int row, int column, String value) {
        data.get(row)[column] = value;
    }

    public void delete(int row) {
        if(row < data.size()){
            data.remove(row);
        }
    }

    // getter 
    public List<String[]> getData(){
        return data;
    }

}
