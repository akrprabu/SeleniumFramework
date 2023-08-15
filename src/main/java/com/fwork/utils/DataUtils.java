package com.fwork.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DataUtils {

    @DataProvider(name="getDataFromJson")
    public Object[] getData(Method m, ITestContext context) {

        System.out.println("Class name: " +context.getAttribute("classname"));
        System.out.println("Method name: " + m.getName());

        try {

            ObjectMapper mapper = new ObjectMapper();
            JSONParser parser = new JSONParser();
            JSONObject jsonobj = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\testdata\\"+context.getAttribute("classname")+".json"));
            JSONArray jsons = (JSONArray) jsonobj.get(m.getName());
            Map<Integer, Map<String, String>> jsonmaps = new HashMap<Integer, Map<String, String>>();
            int i=0;
            for (Object j : jsons) {
                Map<String, String> map = new HashMap<String, String>();
                JSONObject o = (JSONObject) j;
                map = mapper.readValue(j.toString(), new TypeReference<Map<String, String>>() {
                });
                jsonmaps.put(i, map);
                i++;
            }
            //System.out.println(jsonmaps.get(0));
            Object[] data = new Object[jsonmaps.size()];

            for(int j=0; j<jsonmaps.size(); j++) {
                data[j] = jsonmaps.get(j);
            }

            return data;


        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }



    }

}
