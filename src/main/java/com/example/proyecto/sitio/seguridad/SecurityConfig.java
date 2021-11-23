package com.example.proyecto.sitio.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Autowired
    public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passEncoder)
                .usersByUsernameQuery("SELECT username, password, enabled FROM usuario WHERE username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.rol from roles r inner join usuario u ON r.usuario_id=u.id WHERE u.username=?");
    }

}
