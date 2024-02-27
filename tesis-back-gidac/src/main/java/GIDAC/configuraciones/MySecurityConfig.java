package GIDAC.configuraciones;

import GIDAC.servicios.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .cors()
                .disable()
                .authorizeRequests()
                .antMatchers("/appWeb/mostrar-informacion-app-web",
                        "/dato-recolectado/listar-todos-datos-unidos/{id}/{idVariable}/{codigoDataset}",
                        "/dato-recolectado/listar-todos-datos-catalogo-unidos/{id}",
                        "/dato-recolectado/listar-todos-datos-nominal/{id}",
                        "/dato-recolectado/listar-todos-datos-catalogo-nominal/{id}",
                        "/variable/listar-variable-difucion/{id}/{idOrganizacion}",
                        "/variable-familia/listar-familias-difusion",
                        "/organizacion/listar",
                        "/variable/listar-variable",
                        "/api/listar-director-proyecto/{id}",
                        "/dato-recolectado/listar-todos-datos/{id}",
                        "/dato-recolectado/listar-todos-datos-catalogo/{id}",
                        "/catalogo-organizacion/listar-catalogo-organizacion",
                        "/rol/guardar-rol",
                        "/appWeb/obtener-informacion-app-web",
                        "/appWeb/guardar-informacion-app-web",
                        "/api/investigacion-publicas",
                        "/api/investigacion-mapa",
                        "/api/investigacion",
                        "/solicitud/guardar-solicitud",
                        "/guardar-visitante",
                        "/api/carbono",
                        "/enviar-email-recuperar-contrasenia",
                        "/generate-token",
                        "/usuarios/",
                        "/solicitud/guardar-solicitud-descarga",
                        "/app-web/mostrar-informacion-app-web-vigente",
                        "/director-area-investigacion/obtener-director-area-investigacion/{id}",
                        "/api/lista-investigadores-en-proyectors-investigacion/{id}",
                        "/api/obtener-proyecto-investigacion/{id}",
                        "/area-investigacion-proyecto/listar-por-proyecto/{id}",
                        "/linea-investigacion-proyecto/listar-por-proyecto/{id}",
                        "/localizacion-proyecto/listar-por-proyecto/{id}",
                        "/sector-impacto-proyecto/listar-por-proyecto/{id}",
                        "/dataset/obtener-dataset-por-proyecto-asc/{id}",
                        "/editar-perfil").permitAll()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
