package com.Services;

import com.Common.Base.HttpRequirements;
import com.mashape.unirest.http.HttpResponse;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

public class BookRequest extends HttpRequirements
{
    public Book request;

    public BookRequest()
    {

    }

    public void getAllBooks()
    {
        responseForGetMethods(config.getBooksAllURL());
    }

    public void sendBook(Book request)
    {
        setRequest(request);
        responseForPutMethods(config.getBooksAllURL(),new JSONObject(getRequest()).toString());
    }

    public void getById(int bookId)
    {
        responseForGetMethods(config.getBookByIdURL()+ String.format(config.getBookByIdURL(),bookId));
    }

    public Book requestedBook()
    {
        return getRequest();
    }

    public Book getRequest() {
        return request;
    }

    public void setRequest(Book request) {
        this.request = request;
    }
}
