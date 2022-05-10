package testing.Api;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class ApiTest {

    static String APIbodyTOkenLOgin;
    static List<Header> headerlist = new ArrayList<Header>();
    static public String token;
    static public String name;
    static public String value;
    //static public String OTPtoken="eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJFdmVyeW9uZSxCVUlMVElOXFxVc2VycyxDT05TT0xFIExPR09OLE5UIEFVVEhPUklUWVxcQXV0aGVudGljYXRlZCBVc2VycyxOVCBBVVRIT1JJVFlcXFRoaXMgT3JnYW5pemF0aW9uLExPQ0FMIiwiVXNlcklkIjoiNzMiLCJVc2VyVHlwZXMiOiJJbnB1dHRlcixWaWV3ZXIiLCJleHAiOjE2MDA4NTQyMzcsImlzcyI6InNtZXNrLmluIiwiYXVkIjoicmVhZGVycyJ9.OgPW7c3n84rAzSQC1MMoObsCMpUheuu1_LwXyPEpYts";

    public  ApiTest()
    {


        RestAssured.baseURI  = "http://nwbtestapi.vizalys.com";


    }


    @Nested
    @DisplayName("Api's")
    class Api {


        @Test
        @Order(1)
        public void NWB_TokenLogin() {
            System.out.println("----------API_TokenLogIn----------");
            String APIBody = "{\"email\": \"superadmin@vizalys.com\",\n" +
                    "  \"password\": \"Admin123!@#\"}";

            List<Header> headerlist = new ArrayList<Header>();
            headerlist.add(new Header("Content-Type", "application/json"));
            //headerlist.add(new Header("device-id","1"));
            //headerlist.add(new Header("user-agents","postman"));
            //headerlist.add(new Header("device-type","mobile"));
            //headerlist.add(new Header("license-key","EF834317-1486-48E6-91EC-04D76FF720B8"));
            //headerlist.add(new Header("user-host-name","salman"));
            //headerlist.add(new Header("user-language","English"));
            //headerlist.add(new Header("user-host-address","::::0"));

            Headers headers = new Headers(headerlist);

            System.out.println(headers);


            Response r;

            r = given().body(APIBody).
                    headers(headers).
                    when().
                    post("/api/Auth/Login");


            ResponseBody body = r.getBody();
            int statusCode = r.getStatusCode();
            System.out.println(statusCode);
            //


            APIbodyTOkenLOgin = body.asString();    ///I COMMENTED THIS OUT FOR CHECKING response

            //	System.out.println("Response body "+APIbodyTOkenLOgin);


            JSONObject json = new JSONObject(body.asString());
            //json = json.getJSONObject("Model");
            token = json.getString("message");
            System.out.println("checking LoginToken " + token);

            //executOPTWebService();

            //System.out.println("checking apiTokenfromLogin "+APIbodyTOkenLOgin);

            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);
            //assertEquals(bodyAsString.contains("Fetched Successful") /*Expected value*/, true /*Actual Value*/);

            //APIbodyTOkenLOgin = body.asString(); 	///I COMMENTED THIS OUT FOR CHECKING response
            //	System.out.println("checking apiTokenfromLogin "+APIbodyTOkenLOgin); ///I COMMENTED THIS OUT FOR CHECKING response
            //	System.out.println("kaam hogaya------"+APIbodyTOkenLOgin);

            //if(bodyAsString.contains("Fetched Successful")==false)
            {
                //		System.out.println(" in iff");
                //	fail("Should not have thrown invalidity");
            }
            //fail("Should not have thrown any exception");
            //assertEquals(bodyAsString.contains("Fetched Successful") /*Expected value*/, true /*Actual Value*/);

            name = "Authorization";
            value = "bearer "+ token;
            //System.out.println(value);
        }

        @Test
        @Order(2)
        public void NWB_Get_allUsers()
        {
            System.out.println("----------***** NWB_GET_allUsers *****----------");


            String APIBody = "{}";


            List<Header> headerlist = new ArrayList<Header>();
            headerlist.add(new Header("Content-Type","application/json"));
//            headerlist.add(new Header("device-id","1"));
//            headerlist.add(new Header("user-agents","postman"));
//            headerlist.add(new Header("device-type","web")); //check this
//            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
//            headerlist.add(new Header("user-host-name","hakim"));
//            headerlist.add(new Header("user-language","English"));
//            headerlist.add(new Header("user-host-address","::::0"));
            //headerlist.add(new Header("AuthToken","bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiTmF2ZWVkIiwiRW1haWwiOiJzdXBlcmFkbWluQHZpemFseXMuY29tIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJkY2FjODk0NS05M2VlLTQ1ZGQtOWFjYS0wMjBlODE4YzE2YjAiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQWRtaW4iLCJTdXBlckFkbWluIl0sIlBlcm1pc3Npb24iOlsiUGVybWlzc2lvbnMuQXV0aENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5BdXRoQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5BdXRoQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5BdXRoQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkJ1c2luZXNzUGFydG5lckNsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5CdXNpbmVzc1BhcnRuZXJDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkJ1c2luZXNzUGFydG5lckNsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQnVzaW5lc3NQYXJ0bmVyQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLk9yZ2FuaXphdGlvbkNsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5Pcmdhbml6YXRpb25DbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLk9yZ2FuaXphdGlvbkNsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuT3JnYW5pemF0aW9uQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkRlcGFydG1lbnRzQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkRlcGFydG1lbnRzQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5EZXBhcnRtZW50c0NsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuRGVwYXJ0bWVudHNDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQ2FtcHVzQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkNhbXB1c0NsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQ2FtcHVzQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5DYW1wdXNDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuV2FyZWhvdXNlQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLldhcmVob3VzZUNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuV2FyZWhvdXNlQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5XYXJlaG91c2VDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuTG9jYXRpb25DbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuTG9jYXRpb25DbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkxvY2F0aW9uQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5Mb2NhdGlvbkNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5CYW5rQWNjb3VudENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5CYW5rQWNjb3VudENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQmFua0FjY291bnRDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkJhbmtBY2NvdW50Q2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkJhbmtTdGF0ZW1lbnRDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuQmFua1N0YXRlbWVudENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQmFua1N0YXRlbWVudENsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQmFua1N0YXRlbWVudENsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5DYXNoQWNjb3VudENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5DYXNoQWNjb3VudENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQ2FzaEFjY291bnRDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkNhc2hBY2NvdW50Q2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkNoYXJ0T2ZBY2NvdW50Q2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkNoYXJ0T2ZBY2NvdW50Q2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5DaGFydE9mQWNjb3VudENsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQ2hhcnRPZkFjY291bnRDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQ2F0ZWdvcmllc0NsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5DYXRlZ29yaWVzQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5DYXRlZ29yaWVzQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5DYXRlZ29yaWVzQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLlByb2R1Y3RzQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLlByb2R1Y3RzQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5Qcm9kdWN0c0NsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuUHJvZHVjdHNDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuV29ya2Zsb3dTdGF0dXNDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuV29ya2Zsb3dTdGF0dXNDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLldvcmtmbG93U3RhdHVzQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5Xb3JrZmxvd1N0YXR1c0NsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5Xb3JrZmxvd0NsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5Xb3JrZmxvd0NsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuV29ya2Zsb3dDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLldvcmtmbG93Q2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkxldmVsNENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5MZXZlbDRDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkxldmVsNENsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuTGV2ZWw0Q2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLkJhbmtSZWNvbkNsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5CYW5rUmVjb25DbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkJhbmtSZWNvbkNsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuQmFua1JlY29uQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLlRyYW5zYWN0aW9uUmVjb25DbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuVHJhbnNhY3Rpb25SZWNvbkNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuVHJhbnNhY3Rpb25SZWNvbkNsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuVHJhbnNhY3Rpb25SZWNvbkNsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5JbnZvaWNlQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkludm9pY2VDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkludm9pY2VDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkludm9pY2VDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQmlsbENsYWltcy5DcmVhdGUiLCJQZXJtaXNzaW9ucy5CaWxsQ2xhaW1zLlZpZXciLCJQZXJtaXNzaW9ucy5CaWxsQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5CaWxsQ2xhaW1zLkRlbGV0ZSIsIlBlcm1pc3Npb25zLlBheW1lbnRDbGFpbXMuQ3JlYXRlIiwiUGVybWlzc2lvbnMuUGF5bWVudENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuUGF5bWVudENsYWltcy5FZGl0IiwiUGVybWlzc2lvbnMuUGF5bWVudENsYWltcy5EZWxldGUiLCJQZXJtaXNzaW9ucy5DcmVkaXROb3RlQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkNyZWRpdE5vdGVDbGFpbXMuVmlldyIsIlBlcm1pc3Npb25zLkNyZWRpdE5vdGVDbGFpbXMuRWRpdCIsIlBlcm1pc3Npb25zLkNyZWRpdE5vdGVDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuRGViaXROb3RlQ2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkRlYml0Tm90ZUNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuRGViaXROb3RlQ2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5EZWJpdE5vdGVDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuSm91cm5hbEVudHJ5Q2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkpvdXJuYWxFbnRyeUNsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuSm91cm5hbEVudHJ5Q2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5Kb3VybmFsRW50cnlDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuQnVkZ2V0Q2xhaW1zLkNyZWF0ZSIsIlBlcm1pc3Npb25zLkJ1ZGdldENsYWltcy5WaWV3IiwiUGVybWlzc2lvbnMuQnVkZ2V0Q2xhaW1zLkVkaXQiLCJQZXJtaXNzaW9ucy5CdWRnZXRDbGFpbXMuRGVsZXRlIiwiUGVybWlzc2lvbnMuR2VuZXJhbExlZGdlckNsYWltcy5WaWV3Il0sImV4cCI6MTY0OTk1NzgyMiwiaXNzIjoiaHR0cDovL3dhbGVlZC5uZXQiLCJhdWQiOiJodHRwOi8vd2FsZWVkLm5ldCJ9.SiWm8tSAX4KksC-deeSLlHE3zZs7rZ6F0WCqYaJPRTc"));
            //headerlist.add(new Header("Authorization","bearer "+token));
            headerlist.add(new Header(name,value));
            Headers headers = new Headers(headerlist);



            Response r = given().body(APIBody).
                    headers(headers).
                    when().
                    get("/api/Auth/Users");


            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);


            //Assert that correct status code is returned.
            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }


        @Test
        @Order(3)
        public void NWB_Put_Users_ById()
        {
            System.out.println("----------***** NWB_PUT_Users_ById *****----------");

            String APIBody = "{\"email\": \"ali@gmail.com\",\n" +
                    "  \"userName\": \"ali\",\n" +
                    "  \"userId\": \"79ceeaa0-5567-4a91-b859-a2c3f4f85ede\",\n" +
                    "  \"userRoles\": [\n" +
                    "    {\n" +
                    "      \"roleName\": \"SuperAdmin\",\n" +
                    "      \"selected\": true\n" +
                    "    }\n" +
                    "  ]}";


            List<Header> headerlist = new ArrayList<Header>();
            headerlist.add(new Header("Content-Type","application/json"));
//            headerlist.add(new Header("device-id","1"));
//            headerlist.add(new Header("user-agents","postman"));
//            headerlist.add(new Header("device-type","web")); //check this
//            headerlist.add(new Header("license-key","213DD508-876F-4DD3-BBC1-0A33CC54A6C0")); //check this
//            headerlist.add(new Header("user-host-name","hakim"));
//            headerlist.add(new Header("user-language","English"));
//            headerlist.add(new Header("user-host-address","::::0"));
            //headerlist.add(new Header("AuthToken","bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsIlVzZXJJZCI6IjI2IiwiVXNlclR5cGUiOiJBZG1pbiIsImV4cCI6MTc0OTAzNzk0MCwiaXNzIjoic21lc2suaW4iLCJhdWQiOiJyZWFkZXJzIn0.X7a2_Hf9m5aW8ufa02qJhTlbb-Cg3fX1ljkk5szN6pw "));
            //headerlist.add(new Header("Authorization","bearer "+token));
            headerlist.add(new Header(name,value));
            Headers headers = new Headers(headerlist);



            Response r = given().body(APIBody).
                    headers(headers).
                    when().
                    put("/api/Auth/Users/79ceeaa0-5567-4a91-b859-a2c3f4f85ede");

            //r.prettyPrint();
            String body = r.getBody().asString();
            //ResponseBody  body = r.getBody();
            System.out.println(body);

            int statusCode = r.getStatusCode();
            System.out.println(statusCode);

            //Assert that correct status code is returned.
            //assertEquals(statusCode /*actual value*/, 200 /*expected value*/);


//            String bodyAsString = body.asString();
//            System.out.println(bodyAsString);

//            if(!bodyAsString.contains("Data Fetched Successfully from Database") || !bodyAsString.contains("true"))
//            {
//                System.out.println("(API responded wrong)");
//                fail();
//            }
        }

    }

}
