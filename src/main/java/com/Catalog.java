package com;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Catalog {
    private List<Item> items;

    public Item findById(String id) {
        return items.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public Catalog() {
        this.items= new ArrayList<>();
    }

    public void addItem(Item i) {
        items.add(i);
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "items=" + items +
                '}';
    }

    public boolean saveCatalog() {
        JSONArray ItemList = new JSONArray();
        for(Item it : items){
            JSONObject itemDetails = new JSONObject();
            itemDetails.put("id",it.getId());
            itemDetails.put("title", it.getTitle());
            itemDetails.put("location", it.getLocation());
            itemDetails.put("year", it.getYear());
            itemDetails.put("author", it.getAuthor());
            itemDetails.put("type", it.getType());
            if ( it instanceof Book){
                itemDetails.put("description", ((Book) it).getDescription());
            }
            JSONObject itemObject = new JSONObject();
            itemObject.put("item", itemDetails);
            ItemList.add(itemObject);
        }
        try (FileWriter file = new FileWriter("catalog.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(ItemList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean loadCatalog() {
        JSONParser jsonParser = new JSONParser();

        try(FileReader reader = new FileReader("catalog.json")){
            Object obj = jsonParser.parse(reader);
            JSONArray itemList = (JSONArray) obj;
            System.out.println(itemList);

            itemList.forEach( emp -> parseItemObject( (JSONObject) emp ) );

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    private static void parseItemObject(JSONObject item)
    {
        JSONObject itemObject = (JSONObject) item.get("item");

        System.out.println("");

        String id = (String) itemObject.get("id");
        System.out.println("ID: " + id);

        String title = (String) itemObject.get("title");
        System.out.println("Title: " + title);

        String location = (String) itemObject.get("location");
        System.out.println("Location: " + location);

        String year = (String) itemObject.get("year");
        System.out.println("Year: " + year);

        String author = (String) itemObject.get("author");
        System.out.println("Author: " + author);

        String type = (String) itemObject.get("type");
        System.out.println("Type: " + type);

        if(itemObject.get("type").equals("book") ){
            String description = (String) itemObject.get("description");
            System.out.println("Description: " + description);
        }
    }
}
