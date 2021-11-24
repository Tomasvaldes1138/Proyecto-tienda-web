package com.example.proyecto.sitio.seguridad;

import com.example.proyecto.sitio.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Autowired
    private DataSource dataSource;

    @Bean
   public static BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

/*    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }*/

    @Autowired
    public void configurerSecurityGlobals(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(bcrypt)
                .usersByUsernameQuery("SELECT correo, clave, enabled FROM usuario WHERE correo=?")
                .authoritiesByUsernameQuery("SELECT u.correo, r.rol from roles r inner join usuario u ON r.usuario_correo=u.correo WHERE u.correo=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.err.println(SecurityContextHolder.getContext().getAuthentication());


        http.authorizeRequests().antMatchers("/home","/registro","/insertar_usuario",
                        "/img/**","/gulp_sass/**" ).permitAll() //Aqui se indican las paginas de acceso publico (tambien los archivos estaticos como CSS)
                .antMatchers("/categoria/procesadores").hasAnyRole("USER") //Vistas disponibles segun roles
                .antMatchers("/pedidos_realizados").hasAnyRole("ADMIN")
                .antMatchers("/info_productos").hasAnyRole("ADMIN")
                .antMatchers("/actualizar_producto").hasAnyRole("ADMIN")
                .antMatchers("/nuevo_administrador").hasAnyRole("ADMIN")
                .antMatchers("/nuevo_producto").hasAnyRole("ADMIN")
                .anyRequest().authenticated()    //Indicando que cualquier solicitud debe ser AUTENTICADA
                .and()
                .formLogin().loginPage("/login") //Seteando nuestro login personalizado
                .defaultSuccessUrl("/home", true)
                .permitAll()//Solicitando login al acceder a una url protegida
                .and()
                .logout().permitAll();
    }
}
