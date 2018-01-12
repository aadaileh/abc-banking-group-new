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
