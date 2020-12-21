package com.example.goodsom.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import com.example.goodsom.service.UserService;

@Configuration
//@EnableWebSecurity
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/ {
//
//	@Autowired
//	private final UserService userService;
//	
//    @Bean
//    public PasswordEncoder passwordEncoder() { // 비밀번호 암호화 시 사용할 인코더
//        return new BCryptPasswordEncoder();
//    } 
//	
//    // 정적 자원에 대해서는 Security 설정을 적용하지 않음.
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }
//    
////    @Override
////    public void configure(WebSecurity web) {
////      web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
////    }
//
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//    	http
//	        .authorizeRequests() // 7
//	          .antMatchers("/user/login.do", "/user/register.do").permitAll() // 누구나 접근 허용
//	          .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
//	        .and()
//		      .formLogin() // 8
//	            .loginPage("/user/login.do") // 로그인 페이지 링크
//	            .defaultSuccessUrl("/index.do") // 로그인 성공 후 리다이렉트 주소
//	        .and()
//	          .logout() // 9
//	            .logoutSuccessUrl("/user/login.do") // 로그아웃 성공시 리다이렉트 주소
//	            .invalidateHttpSession(true) // 세션 날리기
//	     ;
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 10
//      auth.userDetailsService(userService).passwordEncoder(passwordEncoder()); // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
//    }
}
