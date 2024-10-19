package restAPI;

import files.payloads;
import io.restassured.path.json.JsonPath;

public class jsonParse {
    public static void main(String [] args){
        JsonPath js = new JsonPath(payloads.mockResponse());
        // System.out.println(js.getInt("dashboard.purchaseAmount"));
        int count = js.getInt("courses.size()");
        // System.out.println(count);
        // System.out.println(js.getString("courses[0].title"));

        for(int i = 0; i < count; i++) {
            System.out.println(js.getString("courses["+ i +"].title"));
            System.out.println(js.getInt("courses["+ i +"].price"));
        }

        for(int i = 0; i < count; i++) {
            if(js.getString("courses["+ i +"].title").equals("RPA")) {
                System.out.println(js.getString("courses["+ i +"].copies"));
            }
        }
    }
}
