package abcbankinggroup;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface SampleFeignClient {
    @RequestLine("GET /{isbn}")
    User findByIsbn(@Param("isbn") String isbn);

    @RequestLine("GET")
    List<User> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void db();
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