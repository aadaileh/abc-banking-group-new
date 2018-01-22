package com.kingston.university.coursework.abcbankinggroup.Clients;

import com.kingston.university.coursework.abcbankinggroup.DTOs.*;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.ArrayList;
import java.util.List;

public interface FeignClient {
    @RequestLine("GET /{username}")
    User getUserDetails(@Param("username") String username);

    @RequestLine("GET /{clientId}")
    @Headers("Content-Type: application/json")
    ArrayList<Transaction> getAccountDetailsFromClient(@Param("clientId") String clientId);

    @RequestLine("GET /{clientId}")
    @Headers("Content-Type: application/json")
    double getAccountBalance(@Param("clientId") String clientId);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    FundTransferResponse verifyTransfer(FundTransferRequest fundTransferRequest);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    Boolean performTransfer(FundTransferRequest fundTransferRequest);

    @RequestLine("PUT")
    @Headers("Content-Type: application/json")
    Boolean updateAccountTable(FundTransferRequest fundTransferRequest);

    @RequestLine("GET")
    List<User> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    User loginServiceVerifyLogin(Credentials credentials);

}

// Unit-Testing Code..
//http://www.baeldung.com/intro-to-feign
//    @Test
//    public void givenBookClient_shouldRunSuccessfully() throws Exception {
//        List<Book> books = bookClient.findAll().stream()
//                .map(BookResource::getBook)
//                .collect(Collectors.toList());
//
//        assertTrue(books.size() > 2);
//    }
//
//    @Test
//    public void givenBookClient_shouldFindOneBook() throws Exception {
//        Book book = bookClient.findByIsbn("0151072558").getBook();
//        assertThat(book.getAuthor(), containsString("Orwell"));
//    }
//
//    @Test
//    public void givenBookClient_shouldPostBook() throws Exception {
//        String isbn = UUID.randomUUID().toString();
//        Book book = new Book(isbn, "Me", "It's me!", null, null);
//        bookClient.create(book);
//        book = bookClient.findByIsbn(isbn).getBook();
//
//        assertThat(book.getAuthor(), is("Me"));
//    }