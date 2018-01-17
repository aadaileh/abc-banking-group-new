package com.kingston.university.coursework.abcbankinggroup.Services.Login;

import com.kingston.university.coursework.abcbankinggroup.DTOs.Credentials;
import com.kingston.university.coursework.abcbankinggroup.DTOs.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

public interface LoginServiceInterface {
    public User verifyLogin(@RequestBody Credentials credentials) throws SQLException;

}
