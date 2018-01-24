package com.abcbankinggroup.Factory;

import com.abcbankinggroup.Clients.FeignClient;
import com.abcbankinggroup.Connection.DatabaseConnectionSingleton;
import com.abcbankinggroup.Services.Main.MainServiceController;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;

@Component
public abstract class CommonFactoryAbstract {

    protected static final Logger LOG = LoggerFactory.getLogger(MainServiceController.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.feign.url}")
    private String feignUrl;

    @Value("${spring.basicauthentication.username}")
    private String basicAuthenticationUsername;

    @Value("${spring.basicauthentication.password}")
    private String basicAuthenticationPassword;

    @Autowired
    private DataSource dataSource;

    /**
     * Build connection to database
     *
     * @return Datasource to the database
     *
     * @throws SQLException
     *
     * @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    protected DataSource getDataSource() {

        DataSource dataSource = null;

        DatabaseConnectionSingleton databaseConnectionSingleton = DatabaseConnectionSingleton.getInstance();
        databaseConnectionSingleton.setDbUrl(dbUrl);
        databaseConnectionSingleton.setUsername(username);
        databaseConnectionSingleton.setPassword(password);

        try {
            dataSource = databaseConnectionSingleton.dataSource();
            LOG.info("DataSource successfully initialized");
            return dataSource;
        } catch (SQLException e) {
            LOG.error("something went wrong while initializing the DataSource Message: [" + e.getMessage() + "]");
        }

        return dataSource;
    }

    /**
     * Prepare Feign-client for communication with other services
     * @param path
     * @return FeignClient
     *
     *  @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    protected FeignClient getFeignClient(String path) {

        FeignClient feignClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(getRequestInterceptor())
                .logger(new Slf4jLogger(FeignClient.class))
                .logLevel(feign.Logger.Level.FULL)
                .target(FeignClient.class, feignUrl + path);

        return feignClient;
    }

    /**
     * Gets the Basic Authentication Interceptor
     * @return BasicAuthRequestInterceptor
     *
     *  @Author Ahmed Al-Adaileh <k1560383@kingston.ac.uk> <ahmed.adaileh@gmail.com>
     */
    private BasicAuthRequestInterceptor getRequestInterceptor() {
        return new BasicAuthRequestInterceptor(basicAuthenticationUsername, basicAuthenticationPassword);
    }
}
