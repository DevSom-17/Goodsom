package com.example.goodsom.config;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
//
//    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeRequests()
//                // /about 요청에 대해서는 로그인을 요구함
//                .antMatchers("/about").authenticated()
//                // /admin 요청에 대해서는 ROLE_ADMIN 역할을 가지고 있어야 함
//                .antMatchers("/admin").hasRole("ADMIN")
//                // 나머지 요청에 대해서는 로그인을 요구하지 않음
//                .anyRequest().permitAll()
//                .and()
//                // 로그인하는 경우에 대해 설정함
//            http.formLogin()
//                // 로그인 페이지를 제공하는 URL을 설정함
//                .loginPage("/user/login.do")
//                // 로그인 성공 URL을 설정함
//                .successForwardUrl("/index")
//                // 로그인 실패 URL을 설정함
//                .failureForwardUrl("/index")
//                .permitAll()
//                .and();
//                .addFilterBefore(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
}
