package com.Services;

import java.util.ArrayList;

public class BookDatabase extends Response
{
    public BookDatabase()
    {

    }

    public void setBook(BookData bookData)
    {
        ArrayList<BookData> books=getBooks();
        books.add(bookData);
        setBooks(books);
    }

    public BookData getBookByTitle(String title)
    {
        for (BookData bookTemp:getBooks())
        {
            if(bookTemp.getTitle().equals(title))
            {
                return bookTemp;
            }
        }
        return null;
    }

    public BookData getBookById(int id)
    {
        for (BookData bookTemp:getBooks())
        {
            if(bookTemp.getId()==id)
            {
                return bookTemp;
            }
        }
        return null;
    }
}
