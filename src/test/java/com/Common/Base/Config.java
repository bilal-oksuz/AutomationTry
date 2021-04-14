package com.Common.Base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Config
{
    private Map<String, String> trendyolUrl;

    private Map<String, String> alerts;

    private Map<String, String> gridConfiguration;

    private Map<String, String> books;

    private String env;

    public static Config config;



    public Map<String, String> getTrendyolUrl() { return trendyolUrl; }
    public void setTrendyolUrl(Map<String, String> trendyolUrl) { this.trendyolUrl =trendyolUrl; }

    public String getTrendyolBaseURL() { return trendyolUrl.get("BaseUrl"); }
    public String getTrendyolWomenButiksURL() { return trendyolUrl.get("WomenButik"); }
    public String getTrendyolMenButiksURL() { return trendyolUrl.get("MenButik"); }
    public String getTrendyolLoginURL() { return trendyolUrl.get("Login"); }

    public Map<String, String> getAlerts() {
        return alerts;
    }

    public void setAlerts(Map<String, String> alerts) {
        this.alerts = alerts;
    }


    public Map<String, String> getGridConfiguration() { return gridConfiguration; }
    public void setGridConfiguration(Map<String, String> gridConfiguration) { this.gridConfiguration =gridConfiguration; }

    public String getTrendyolAlertChannel() { return alerts.get("TrendyolAlert"); }
    public boolean isSendNotifiication() { return Boolean.parseBoolean(alerts.get("SendNotification")); }
    public String getSlackApiURL() { return alerts.get("SlackAPI"); }
    public String getSlackToken() { return alerts.get("APIToken"); }
    public String getFileUploadURL() { return alerts.get("UploadFile"); }

    public boolean getGridFeature() { return Boolean.parseBoolean(gridConfiguration.get("gridFeature")); }
    public String getGridURL() { return gridConfiguration.get("gridURL"); }


    public Map<String, String> getBooks() { return books; }
    public void setBooks(Map<String, String> books) { this.books =books; }

    public String getBooksAllURL() { return books.get("getBook"); }
    public String getBookPutURL() { return books.get("putBook"); }
    public String getBookByIdURL() { return books.get("getBookById"); }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public void setConfig(Config config) {
        Config.config = config;
    }

    public static Config getConfig() {
        return config;
    }

    public Config()
    {

    }

    public Config(String env)
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Config config = null;
        try
        {
            config=mapper.readValue(new File(System.getProperty("user.dir")+"/Config-"+env+".yaml"),Config.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        config.setEnv(env);
        setConfig(config);

    }

    public Config config(String env)
    {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Config config = null;
        try
        {
            config=mapper.readValue(new File(System.getProperty("user.dir")+"/Config-"+env+".yaml"),Config.class);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        config.setEnv(env);
        config.setConfig(config);
        setConfig(config);
        return  config;

    }



}
