package com.Common.Base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import net.bytebuddy.utility.RandomString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JavaInfastructure {

    public JavaInfastructure() {

    }

    public static boolean isNullOrEmpty(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNull(Object object) {
        if (object == null | object == "[]" ) {
            return true;
        }
        return false;
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date addDaysWithoutWeekend(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i <days+1 ; i++)
        {
            date=JavaInfastructure.addDays(date,1);
            if(date.getDay()!=6 && date.getDay()!=0)
            {
                continue;
            }
            else
            {
                date=JavaInfastructure.addDays(date,1);
            }
        }
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        //minus number would decrement the days
        return String.valueOf(cal.getTimeInMillis());
    }


    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }

    public static String randomStringWithRange(int length) {
        RandomString gen = new RandomString(length);
        return gen.nextString();
    }

    public static double randomWithRangeDouble(int min, int max) {
        int range = (max - min) + 1;
        return (Math.random() * range) + min;
    }

    public static String upperCaseTurkish(String text) {
        text = text.replace('i', 'İ');
        return text.toUpperCase();
    }

    public static void createFile(String fileName, String text) {
        FileWriter file = null;
        try {
            file = new FileWriter(fileName, true);
            BufferedWriter out = new BufferedWriter(file);
            out.write(text + "\n");
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static List readFile(String fileName)
    {
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null) {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    public static String readFileString(String fileName)
    {
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (br != null) {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return result.get(0);
    }

    public static File readFileByFile(String fileName)
    {
        File file = new File(fileName);
        return file;
    }

    public static Date getDateWithTurkishFormat()
    {
        Date date = new Date();
        Date dateFromString = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            dateFromString=dateFormat.parse(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateFromString;
    }


    public static String getEnglishDateFromTrFormat(String date) {
        SimpleDateFormat sdfOut = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfIn = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date dateTime = null;
        try {
            dateTime = sdfIn.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  sdfOut.format(dateTime);
    }

    public static void createExcelFile(String fileName, XSSFWorkbook workbook) {
        FileOutputStream outputStream = null;
        try
        {
            outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public String hasStringJson(JSONObject response, String object)
    {
        if(response.has(object))
        {
            return response.get(object).toString();
        }
        else
        {
            return null;
        }
    }

    public JSONObject hasObjectJSON(JSONObject response, String object)
    {
        if(response.has(object))
        {
            return response.getJSONObject(object);
        }
        else
        {
            return null;
        }
    }


    public static String formatJSON (String response)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(response);
        String prettyJsonString = gson.toJson(je);
        return prettyJsonString;
    }

    public static Object objectMapperByFile(String filePath,String className)
    {
        Object datasource=new Object();
        Class class2=null;
        try {
            class2=Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            datasource = objectMapper.readValue(JavaInfastructure.readFileByFile(filePath),class2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return datasource;
    }

    public static int getNumber() {
        Random rnd = new Random();
        return rnd.nextInt(99999999);
    }

    public static void main(String[] args) {
    }

}
