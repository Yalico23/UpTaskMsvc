package com.msvc_test.infrastructure.configuration.auth;

import com.msvc_test.infrastructure.configuration.auth.filter.JwtAuthenticationFilter;
import com.msvc_test.infrastructure.configuration.auth.filter.JwtValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@EnableMethodSecurity() // Habilita la seguridad a nivel de método, permitiendo usar anotaciones como @PreAuthorize
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }

    /**
     * Define un bean de AuthenticationManager que se obtiene a través de la configuración de autenticación.
     * Este bean es esencial para manejar la autenticación de usuarios en la aplicación, permitiendo validar las credenciales proporcionadas.
     *
     * @return AuthenticationManager - El gestor de autenticación configurado para la aplicación.
     * @throws Exception - Si ocurre un error al obtener el AuthenticationManager desde la configuración de autenticación.
     *                   Nota: Este método es crucial para la seguridad de la aplicación, ya que el AuthenticationManager es responsable de autenticar a los usuarios y gestionar sus credenciales.
     *
     */
    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configura la cadena de filtros de seguridad para la aplicación utilizando HttpSecurity.
     * Define las reglas de autorización para las solicitudes HTTP, permitiendo el acceso a la ruta
     *
     * @return SecurityFilterChain - La cadena de filtros de seguridad configurada para la aplicación.
     * @throws Exception - Si ocurre un error al configurar la cadena de filtros de seguridad.
     *
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login")
                        .permitAll()
                        .requestMatchers("/api/rols/**", "/api/rols", "/api/users/**", "/api/users")
                        .permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-resources/**", "/webjars/**")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita la protección CSRF, lo cual es común en aplicaciones RESTful donde no se utilizan cookies para la autenticación.
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // Configura CORS utilizando la configuración definida en el método corsConfigurationSource(), lo que permite controlar qué orígenes, métodos y encabezados están permitidos para las solicitudes entrantes.
                .sessionManagement(sessionManager -> sessionManager
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));// Configura la gestión de sesiones para que sea sin estado, lo que es típico en aplicaciones RESTful donde se utilizan tokens (como JWT) para la autenticación en lugar de sesiones tradicionales.


        return http.build();
    }

    /**
     * Configura la fuente de configuración de CORS para la aplicación, permitiendo solicitudes desde cualquier origen, con métodos HTTP específicos y encabezados permitidos.
     * Esta configuración es esencial para permitir que la aplicación sea accesible desde diferentes dominios, lo que es común en aplicaciones web modernas donde el frontend y el backend pueden estar alojados en diferentes servidores.
     *
     * @return CorsConfigurationSource - La fuente de configuración de CORS configurada para la aplicación.
     * @throws Exception - Si ocurre un error al configurar la fuente de configuración de CORS.
     *
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Permite solicitudes desde cualquier origen
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Permite todos los métodos HTTP (GET, POST, etc.)
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type")); // Permite todos los encabezados
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica esta configuración a todas las rutas

        return source;
    }

    /**
     * Registra un filtro de CORS utilizando la configuración definida en el método corsConfigurationSource(), lo que permite controlar el comportamiento de CORS para las solicitudes entrantes.
     * Este filtro se asegura de que las solicitudes CORS sean manejadas correctamente, permitiendo
     * que la aplicación sea accesible desde diferentes dominios, lo que es esencial para aplicaciones web modernas donde el frontend y el backend pueden estar alojados en diferentes servidores.
     *
     * @return FilterRegistrationBean<CorsFilter> - El bean de registro del filtro de CORS configurado para la aplicación.
     * @throws Exception - Si ocurre un error al registrar el filtro de CORS.
     *
     */
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource())); // Registra el filtro de CORS utilizando la configuración definida en el método corsConfigurationSource(), lo que permite controlar el comportamiento de CORS para las solicitudes entrantes.
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE); // Asegura que el filtro de CORS se ejecute antes que otros filtros de seguridad
        return bean;
    }
}
