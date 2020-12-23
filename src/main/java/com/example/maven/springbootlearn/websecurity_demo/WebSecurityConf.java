package com.example.maven.springbootlearn.websecurity_demo;

import javax.sql.DataSource;

import org.apache.coyote.http11.Http11AprProtocol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@SuppressWarnings("unused")
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        useMemoryStoreUser(auth,passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    }

    /**
     * 使用内存存储 账户,密码,角色类型
     * 
     * @param auth
     * @throws Exception
     */
    private void useMemoryStoreUser(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> conf = auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder);
        conf.withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN", "USER");
        conf.withUser("user").password(passwordEncoder.encode("admin")).roles("USER");
    }

    @Autowired
    DataSource  dataSource;
     /**
     * 使用数据库 账户,密码,角色类型
     * 
     * @param auth
     */
    private void useJDBCStoreUser(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception {
        passwordEncoder= new Pbkdf2PasswordEncoder("defaultPrivateValue");
        JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> conf = auth.jdbcAuthentication()
                .passwordEncoder(passwordEncoder).dataSource(dataSource);
        conf.usersByUsernameQuery("admin").authoritiesByUsernameQuery("query");
    }

    private void useRedisStoreUser(AuthenticationManagerBuilder auth, PasswordEncoder passwordEncoder) throws Exception{

    }
}