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

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Bean
   public static BCryptPasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
    }

//    @Autowired
//    public void configurerSecurityGlobals(AuthenticationManagerBuilder builder) throws Exception {
//        builder.jdbcAuthentication()
//                .dataSource(userDetailsService)
//                .passwordEncoder(bcrypt)
//                .usersByUsernameQuery("SELECT correo, clave FROM usuario WHERE correo=?")
//                .authoritiesByUsernameQuery("SELECT c.correo, r.rol FROM roles r INNER JOIN usuario c ON r.correo_usuario=c.correo WHERE c.correo =?");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.err.println(SecurityContextHolder.getContext().getAuthentication());


        http.authorizeRequests().antMatchers("/home","/img/**","/gulp_sass/**" ).permitAll() //Aqui se indican las paginas de acceso publico (tambien los archivos estaticos como CSS)
                .antMatchers("/mis_comprobantes/").hasAnyRole("USER") //Vistas disponibles segun roles
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
