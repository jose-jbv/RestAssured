package restAPI;

import files.payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sumValidation {

    @Test
    public void sumOfCourses() {
        int sum = 0;
        JsonPath js = new JsonPath(payloads.mockResponse());
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        int count = js.getInt("courses.size()");
        for(int i = 0; i < count; i++) {
            int price = js.getInt("courses["+ i +"].price");
            int copies = js.getInt("courses["+ i +"].copies");
            int amount = price * copies;
            sum = sum + amount;
        }
        Assert.assertEquals(sum,purchaseAmount);
    }
}
