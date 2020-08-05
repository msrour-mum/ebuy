package edu.miu.ebuy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
// configure AuthenticationManager so that it knows from where to load
// user for matching credentials
// Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf().disable()
//			// dont authenticate this particular request
//                .authorizeRequests().antMatchers(HttpMethod.POST, "/signin").permitAll().antMatchers("/login")
//                .permitAll().antMatchers("/auth").permitAll().antMatchers("/register").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/user").permitAll().antMatchers("/error").permitAll()
//                .antMatchers("/forwardLogin", "/facebook").permitAll()
//
//                // all other requests need to be authenticated
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()
//        // make sure we use stateless session; session won't be used to
//        // store user's state.
//        //.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
//        //.exceptionHandling().accessDeniedPage("/403")
//        ;
//        // Add a filter to validate the tokens with every request
//        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.csrf().disable()
                //.cors()
                //.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                //.and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/signin").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/*").permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**");
    }

//    public void successAuthenticate(HttpServletRequest req, HttpServletResponse res, Authentication auth){
//
//        try {
//
//            //Success handler invoked after successful authentication
//            String token = JwtToke(auth);
//            System.out.println("----- success login ----"+auth.getName() + "   token : " + token);
//
//            req.getSession().setAttribute("token", token);
//            req.getSession().setAttribute("loginMessage", "login successfully");
//
////                                        SecurityContext sc = SecurityContextHolder.getContext();
////                                        sc.setAuthentication(auth);
////                                        HttpSession session = req.getSession(true);
////                                        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
////                                        res.sendRedirect("/user/index");
//
//            ///> If user is an Admin -> redirect to admin dashboard
//            String userRole = Role.USER.toString();
//
//            for (GrantedAuthority authority : auth.getAuthorities()) {
//                userRole = authority.getAuthority();
//                System.out.println(userRole);
//                if ( userRole.equals(Role.ADMIN.toString())) break;
//            }
//
//
//            boolean isAdmin = userRole.equals(Role.ADMIN.toString());
//            String userPath = "/user/index";
//            if(!isAdmin){
//                User user = userService.findByEmail(auth.getName());
//                if(user.getBlocked()){
//                    //id will change to unhealthyContent number
//                    userPath = "/user/blocked?num="+user.getId();
//                }
//            }
//            redirectStrategy.sendRedirect(req, res, isAdmin ? "/admin/dashboard" : userPath); // Redirect user
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
