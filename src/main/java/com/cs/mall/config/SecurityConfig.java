package com.cs.mall.config;

import com.cs.mall.component.JwtAuthenticationTokenFilter;
import com.cs.mall.component.RestAuthenticationEntryPoint;
import com.cs.mall.component.RestfulAccessDeniedHandler;
import com.cs.mall.dto.AdminUserDetails;
import com.cs.mall.mbg.model.UmsAdmin;
import com.cs.mall.mbg.model.UmsPermission;
import com.cs.mall.service.UmsAdminService;
import com.cs.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @Author Caosen
 * @Date 2022/8/8 14:45
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig {

    @Autowired
    private UmsAdminService adminService;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    // jwt ????????????????????? http ?????? Authorization ???????????? token ?????????
    @Bean
    public JwtAuthenticationTokenFilter authFilter() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // ?????? token???????????? csrf
                .csrf().disable()
                // ?????? token???????????? session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //todo ???????????????????????????????????????????????? ?????? jwtAuthError ?????????????????????????????????
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).accessDeniedHandler(restfulAccessDeniedHandler).and()
                // ????????????????????????
                .authorizeRequests(authorize -> authorize
                        // ????????????
                        .antMatchers("/**/*.html").permitAll()
                        .antMatchers("/**/*.css").permitAll()
                        .antMatchers("/**/*.js").permitAll()
                        .antMatchers("/**/*.jpg").permitAll()
                        .antMatchers("/swagger-resources/**").permitAll()
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/admin/*").permitAll()
                        .antMatchers("/quartz/*").permitAll()
                        .antMatchers("/order/*").permitAll()
                        .antMatchers("/product/*").permitAll()
                        // ???????????????????????????????????????
                        .anyRequest().authenticated()
                )
                // ?????? JWT ????????????JWT ????????????????????????????????????????????????
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class)
                // ????????????????????????????????????????????????springAuthUserService
                .userDetailsService(userDetailsService())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //????????????????????????
        return username -> {
            //???????????????????????????
            UmsAdmin admin = adminService.getAdminByUsername(username);
            //???????????????????????????
            if (admin != null) {
                List<UmsPermission> permissionList = adminService.getPermissionList(admin.getId());
                return new AdminUserDetails(admin,permissionList);
            }
            throw new UsernameNotFoundException("????????????????????????");
        };
    }



}
