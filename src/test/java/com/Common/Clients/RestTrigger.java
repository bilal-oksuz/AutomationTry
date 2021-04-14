package com.Common.Clients;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.rules.Stopwatch;

import java.io.File;

public class RestTrigger
{
    public StopWatch stopwatch=new StopWatch();

    public  RestTrigger()
    {
        Unirest.setTimeouts(60000,60000);
    }

    public HttpResponse<String> SendRequestPost(String url, String request)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(request)
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<String> SendRequestPUT(String url)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.put(url)
                    .header("Content-Type", "application/json")
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<String> SendRequestPUT(String url, String request)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.put(url)
                    .header("Content-Type", "application/json")
                    .body(request)
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<JsonNode> SendRequestPostAsJSON(String url, String request)
    {
        HttpResponse<JsonNode> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .body(request)
                    .asJson();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<String> SendRequestPostWithAuthorization(String url, String value,String request)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.post(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization", value)
                    .body(request)
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

        public HttpResponse<String> SendRequestGet(String url) {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public  HttpResponse<JsonNode> SendRequestGetAsJson(String url)  {
        HttpResponse<JsonNode> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.get(url)
                    .header("Content-Type", "application/json").asJson();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<String> SendRequestGetWithAuthorization(String url,String value)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.get(url)
                    .header("Content-Type", "application/json")
                    .header("Authorization",value)
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<String> SendRequestGetWithTimeout(String url)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.get(url)
                    .header("Content-Type", "application/json").asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }

        setStopwatch(stopwatch);
        return response;
    }

    public HttpResponse<String> SendRequestDataWithAuthorization(String url, String token, File request,String channel)
    {
        HttpResponse<String> response = null;
        try
        {
            stopwatch.reset();
            stopwatch.start();
            response = Unirest.post(url)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Authorization", token)
                    .field("file",request)
                    .field("channels",channel)
                    .asString();
        }
        catch (UnirestException e)
        {
            e.printStackTrace();
        }
        finally
        {
            stopwatch.stop();
        }
        setStopwatch(stopwatch);
        return response;
    }

    public StopWatch getStopwatch() {
        return stopwatch;
    }

    public void setStopwatch(StopWatch stopwatch) {
        this.stopwatch = stopwatch;
    }


}
