import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.json.JSONObject;

public class Service {

    public static void main(String[] args) {
    	
    	try {
			String jsonContent = getJSONFromFile();
			JSONObject jsonData = new JSONObject(jsonContent);
			HashMap<String, String> params = convertToKeyValuePair(args);
			String countryCode = params.get("countryCode");
			JSONObject data = jsonData.getJSONObject("data");
			
			//If the country code is present in the JSON
			if(countryCode!=null && data.has(countryCode)) {
				JSONObject countryInfo = data.getJSONObject(countryCode);
				String country=countryInfo.getString("name");
				System.out.println(country);
			}
			else {
				System.out.println(countryCode!=null?"Country Code Invalid":"Please enter a valid country code in format --countryCode=<code>");
			}
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
    }
    
    /**
     * Utility function to read the JSON content from the fine data.json
     * @return String with JSON Content
     * @throws IOException	
     */
    private static String getJSONFromFile() throws IOException {
    	InputStream is = Service.class.getResourceAsStream("/data.json");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        reader.close();
        return stringBuilder.toString();
    }
    
    /**
     * Utility function to convert input arguments to key value pair
     * @param args
     * @return
     */
    private static HashMap<String, String> convertToKeyValuePair(String[] args) {

        HashMap<String, String> params = new HashMap();

        for (String arg: args) {

            String[] splitFromEqual = arg.split("=");

            String key = splitFromEqual[0].substring(2);
            String value = splitFromEqual[1];

            params.put(key, value);

        }

        return params;
    }
}
