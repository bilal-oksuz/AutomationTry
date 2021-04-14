package com.Common.Base;

import com.Common.Clients.RestTrigger;
import com.mashape.unirest.http.HttpResponse;

public class HttpRequirements
{
    public RestTrigger restTrigger=new RestTrigger();
    public Config config=Config.getConfig();
    public HttpResponse<String> response;


    public HttpRequirements()
    {

    }

    public void responseForGetMethods(String url)
    {
        setResponse(restTrigger.SendRequestGet(url));
    }


    public void responseForPostMethods(String url,String body)
    {
        setResponse(restTrigger.SendRequestPost(url,body));
    }


    public void responseForPutMethods(String url,String body)
    {
        setResponse(restTrigger.SendRequestPUT(url,body));
    }

    public String getResponseBody()
    {
        return getResponse().getBody();
    }

    public int getResponseCode()
    {
        return getResponse().getStatus();
    }

    public HttpResponse<String> getResponse() {
        return response;
    }

    public void setResponse(HttpResponse<String> response) {
        this.response = response;
    }
}
