//package dataStorage;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.Iterator;
//
//import org.json.*;
//
//public class JsonTestUse {
//	public static void main(String[] args) {
//		JSONArray a = (JSONArray) JSON.parse(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\" 
//				+ "ressources\\InputDay7Of2022"));
//
//		  for (Object o : a)
//		  {
//		    JSONObject person = (JSONObject) o;
//
//		    String name = (String) person.get("name");
//		    System.out.println(name);
//
//		    String city = (String) person.get("city");
//		    System.out.println(city);
//
//		    String job = (String) person.get("job");
//		    System.out.println(job);
//
//		    JSONArray cars = (JSONArray) person.get("cars");
//
//		    for (Object c : cars)
//		    {
//		      System.out.println(c+"");
//		    }
//		  }
//
//        JSONParser parser = new JSONParser();
//
//        try {     
//            Object obj = parser.parse(new FileReader("C:\\Users\\matys\\WorkspaceApplication\\AdventOfCode\\Year2022\\src\\"
//            		+ "ressources\\InputDay7Of2022"));
//
//            JSONObject jsonObject =  (JSONObject) obj;
//
//            String name = (String) jsonObject.get("name");
//            System.out.println(name);
//
//            String city = (String) jsonObject.get("city");
//            System.out.println(city);
//
//            String job = (String) jsonObject.get("job");
//            System.out.println(job);
//
//            // loop array
//            JSONArray cars = (JSONArray) jsonObject.get("cars");
//            Iterator<String> iterator = cars.iterator();
//            while (iterator.hasNext()) {
//             System.out.println(iterator.next());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
//}
