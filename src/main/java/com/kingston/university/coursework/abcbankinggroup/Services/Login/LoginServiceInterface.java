package com.kingston.university.coursework.abcbankinggroup.Services.Login;

import com.kingston.university.coursework.abcbankinggroup.DTOs.Credentials;
import com.kingston.university.coursework.abcbankinggroup.DTOs.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.SQLException;

/**
 * Main interface for the Login-Service. It describes and defines all needed
 * methods for the mentioned service.
 *
 * @Author Ahmed Al-Adaileh <k1530383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
 */
@Service
@EnableSwagger2
public interface LoginServiceInterface {

    public User verifyCredentials(@RequestBody Credentials credentials) throws SQLException;

    public User getUser(@RequestBody String username) throws SQLException;

}
