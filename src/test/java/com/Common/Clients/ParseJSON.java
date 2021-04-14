package com.Common.Clients;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class ParseJSON
{
    public String ParseJson (String Json,String parsingValue)
    {
        JSONObject obj = new JSONObject(Json);
        String value = obj.get(parsingValue).toString();
        return value;
    }

    public ArrayList<String> ParseJson (String jsonArray)
    {
        ArrayList<String> list=new ArrayList<String>();
        JSONArray array = new JSONArray(jsonArray);
        for (int i = 0; i <array.length() ; i++)
        {
            list.add(array.getJSONObject(i).toString());
        }
        return list;
    }

    public Document ParseJson2 (String jsonArray)
    {
        Document list=new Document();
        JSONArray array = new JSONArray(jsonArray);
        for (int i = 0; i <array.length() ; i++)
        {
            list.put(String.valueOf(array.iterator()),array.getJSONObject(i).toString());
        }
        return list;
    }





}
