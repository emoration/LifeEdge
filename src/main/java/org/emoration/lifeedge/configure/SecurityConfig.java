//package org.emoration.lifeedge.configure;
//
//import org.emoration.lifeedge.filter.JwtAuthenticationTokenFilter;
//import org.emoration.lifeedge.handler.AccessDeniedHandlerImpl;
//import org.emoration.lifeedge.handler.AuthenticationEntryPointImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.stereotype.Component;
//
///**
// * @Author czh
// * @Description spring-security配置
// * @Date 2023/11/20
// */
//@Component
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig {
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    private AuthenticationConfiguration authenticationConfiguration;
//
//    @Autowired
//    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
//
//    @Autowired
//    AuthenticationEntryPointImpl authenticationEntryPoint;
//
//    @Autowired
//    AccessDeniedHandlerImpl accessDeniedHandler;
//
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
//        return authenticationManager;
//    }
//
//    //    @Bean
////    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        return http
////                .csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeHttpRequests()
////                .requestMatchers("/login", "/register").anonymous()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
////                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler)
////                .and()
////                .cors()
////                .and()
////                .build();
////    }
////    @Bean
////    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        return http.csrf().disable()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
////                .and()
////                .authorizeHttpRequests()
////                .requestMatchers(request ->
////                        request.getRequestURI().equals("/login") ||
////                                request.getRequestURI().equals("/register") ||
////                                request.getRequestURI().equals("/forgetpwd") ||
////                                request.getRequestURI().equals("/contact") ||
////                                request.getRequestURI().startsWith("/socket/") ||
////                                request.getRequestURI().equals("/getCode") ||
////                                request.getRequestURI().equals("/login/code") ||
////                                request.getRequestURI().equals("/test")
////                ).anonymous()
////                .anyRequest()
////                .authenticated()
////                .and()
////                .addFilterBefore(rateLimitFilter, UsernamePasswordAuthenticationFilter.class)
////                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
////                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler)
////                .and()
////                .cors()
////                .and()
////                .build();
////    }
//
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(csrf -> csrf.disable())
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                ./*authorizeRequests*/authorizeRequests(authorize -> authorize
//                        .requestMatchers("/user/account/login", "/user/account/register","/user/account/hello").anonymous()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(exception -> exception
//                        .authenticationEntryPoint(authenticationEntryPoint)
//                        .accessDeniedHandler(accessDeniedHandler)
//                )
//                .cors(Customizer.withDefaults())
//                .build();
//    }
//
//}
