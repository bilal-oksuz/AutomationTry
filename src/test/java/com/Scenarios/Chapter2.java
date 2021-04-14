package com.Scenarios;

import com.Common.Base.Config;
import com.Common.Base.Customer;
import com.Common.Clients.MongoDB;
import com.Common.Clients.RestTrigger;
import com.Common.UI.SetDriver;
import com.Pages.LoginPage;
import com.Pages.MainPage;
import com.Pages.RegisterPage;
import com.Services.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class Chapter2
{
    MongoDB mongoDB;
    RestTrigger restTrigger;
    Config config;
    BookRequest getBooksRequest;
    BookRequest sendBookRequest;
    BookRequest getBookByIdRequest;
    BookDatabase bookDatabase;
    Response responseGetById;
    Response responseGetAll;
    Response response;
    BookData bookData;

    public Chapter2()
    {
        mongoDB = new MongoDB();
        restTrigger = new RestTrigger();
    }

    @Given("^Get all books$")
    public void getAllBook()
    {
        getBooksRequest.getAllBooks();
        responseGetAll=new Response().responseMap(getBooksRequest.getResponse());
    }

    @Then("^Any book not exits$")
    public void checkBooksNotFound()
    {
        Assert.assertEquals(getBooksRequest.getResponseCode(),HttpStatus.SC_NO_CONTENT);
    }

    @When("^Create a book$")
    public void createBook()
    {
        sendBookRequest.sendBook(new Book());
    }

    @Then("^Check create book result$")
    public void checkCreateBookProcess()
    {
        Assert.assertNotEquals(sendBookRequest.getResponseBody(),HttpStatus.SC_INTERNAL_SERVER_ERROR);
        if(sendBookRequest.getResponseCode()!= HttpStatus.SC_OK)
        {
            switch (sendBookRequest.getResponseCode())
            {
                case HttpStatus.SC_BAD_REQUEST:
                    Assert.assertEquals(sendBookRequest.getResponseBody(),"'<field_name>' cannot be empty.");
                case HttpStatus.SC_NOT_ACCEPTABLE:
                    Assert.assertEquals(sendBookRequest.getResponseBody(),"'<field_name>' is required.");
                case HttpStatus.SC_CONFLICT:
                    Assert.assertEquals(sendBookRequest.getResponseBody(),"Another book with similar title and author already exists.");
            }
        }
    }

    @When("^Get book by Id$")
    public void getBookById()
    {
        getBookByIdRequest.getById(bookData.getId());
        responseGetById=new Response().responseMap(getBookByIdRequest.getResponse());
    }

    @Then("^Check book result$")
    public void checkBookFromDatasource()
    {
        bookDatabase=new BookDatabase();
        bookData=bookDatabase.getBookByTitle(sendBookRequest.getRequest().getTitle());
        Assert.assertEquals(bookData.getAuthor(),sendBookRequest.getRequest().getAuthor());
        Assert.assertEquals(bookData.getTitle(),sendBookRequest.getRequest().getTitle());
    }

    @Then("^Check book result from getById response$")
    public void checkBookFromDatasourceGetById()
    {
        bookDatabase=new BookDatabase();
        bookData=bookDatabase.getBookById(responseGetById.getBooks().get(0).getId());
        Assert.assertEquals(bookData.getAuthor(),sendBookRequest.getRequest().getAuthor());
        Assert.assertEquals(bookData.getTitle(),sendBookRequest.getRequest().getTitle());
    }


}







