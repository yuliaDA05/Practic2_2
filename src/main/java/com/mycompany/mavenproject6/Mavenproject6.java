/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mavenproject6;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author yulia
 */
public class Mavenproject6 {

    public static void main(String[] args) {
        System.out.println("Start programm!");
        String server = "https://android-for-students.ru";
        String serverPath = "/materials/practical/hello.php";
        HashMap<String, String> map = new HashMap();
        map.put("name","Doyneko");
        map.put("group", "RIBO-01-22");
        HTTPRunnable httpRunnable = new HTTPRunnable(server+ serverPath, map);
        Thread th = new Thread(httpRunnable);
        th.start();
        try{
            th.join();
        }catch(InterruptedException ex) {
            
        }catch (JSONException e) {
            System.out.println("Error parsing JSON response: " + e.getMessage());
        } catch (Exception ex) {
            System.out.println("Error processing server response: " + ex.getMessage());
        }
        finally{
            JSONObject jSONObject = new JSONObject(httpRunnable.getResponseBody());
            int result = jSONObject.getInt("result_code");
            System.out.println("Result"+ result);
            System.out.println("Type: "+ jSONObject.getString("message_type"));
            System.out.println("Text"+ jSONObject.getString("message_text"));
            switch(result){
                case 1: 
                    JSONArray jSONArray = jSONObject.getJSONArray("task_list");
                    System.out.println("Task list: ");
                    for(int i = 0; i < jSONArray.length(); i++){
                        System.out.println((i+1) +") " +jSONArray.get(i));  
                    }
                    break;
                case 0:
                    break;
                default:
                    break;
            }
               
//            try{
//                FileWriter fw = new FileWriter ("D:\\Javac\\resp.html");
//                fw.write(httpRunnable.getResponseBody());
//                fw.close();
//                System.out.println("Success save response from server:" + server);
//            } catch(IOException ex){
//                System.out.println("Error response saving : " + ex.getMessage());
//                
//            }
//            }
//            System.out.println("Response from server: " + server );
            System.out.println(httpRunnable.getResponseBody());   
    }
}
}

