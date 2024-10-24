package restAPI;

import files.Payloads;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses() {
        int sum = 0;
        JsonPath js = new JsonPath(Payloads.mockResponse());
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
