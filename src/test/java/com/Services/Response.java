package com.Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;

import java.io.IOException;
import java.util.ArrayList;

public class Response
{
    private ArrayList<BookData> books;

    public Response() {
    }

    public Response responseMap(HttpResponse<String> response)
    {
        Response responseReturn=new Response();
        com.fasterxml.jackson.databind.ObjectMapper om = new ObjectMapper();
        try
        {
            responseReturn=om.readValue(response.getBody(), Response.class);
        }
        catch (IOException e)
        {
            return null;
        }
        return responseReturn;
    }

    public ArrayList<BookData> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<BookData> books) {
        this.books = books;
    }
}
