package abcbankinggroup;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ApiController {

    private static final Logger LOG = LoggerFactory.getLogger(ApiController.class);


    /**
     * Add some comments
     */
    @ApiOperation("Create a new Lead")
    @RequestMapping(value = "/register-new",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    @ResponseBody
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 406, message = "Not Acceptable. Validation of data failed.") })
    public User register(@RequestBody User user) {

        boolean usernameAlreadyExists = false;
        if(usernameAlreadyExists) {
            throw new IllegalArgumentException("error.username");
        }
        User returnedUser = new User();

        returnedUser.setAddress(user.getAddress());
        returnedUser.setLastName(user.getLastName());
        returnedUser.setMail(user.getMail());
        returnedUser.setName(user.getName());
        returnedUser.setPassword(user.getPassword());

        return returnedUser;
    }

    @ExceptionHandler
    void handleIllegalArgumentException(
            IllegalArgumentException e,
            HttpServletResponse response) throws IOException {

        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
