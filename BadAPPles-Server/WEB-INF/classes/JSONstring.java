public class JSONstring {
    
    public String toJSON(String[] stringArray){
        String input = "";
        String splitBy = ": ";
        String columnName = "JSON_Objects";
        String json = "{ \"" + columnName + "\" : [ ";
        for (int i = 0; i < stringArray.length; i++) {
            input = stringArray[i];
            String[] column = input.split(splitBy);  // use : as separator
            json += "{ \"" + column[0] + "\" : \"" + column[1] + "\" }";
            if (i+1 != stringArray.length) {
                json += ", ";
            } 
            else {
                 return json += " ]}";
            }
        }
        
        return json += " ]}"; // this is still valid JSON     
    }

}
