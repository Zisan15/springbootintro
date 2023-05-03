package com.tpe.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration//bu classın configurasyon class'ı olduğunu söylüyorum
@EnableWebSecurity//bütün and pointlerim security katmanıyla karşılaşıcak
@EnableGlobalMethodSecurity(prePostEnabled = true)//method seviyede yetkilendirme yapacağımı söylüyorum
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //!!! bu classta amacımız : AutManager, Provider, PastEncoder'larımı oluşturup birbirleriyle tanıştırmak

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().//csrf korumasını disable yapıyoruz
                authorizeHttpRequests().//gelen bütün requestleri yetkili mi diye kontrol edeceğiz
                antMatchers("/",
                "index.html",
                "/css/*",
                "/js/*").permitAll().//bu end-pointleri yetkili mi diye kontrol etme
                anyRequest().//muaf tutulan end-pointler dışında gelen herhangi bir requesti
                authenticated().//yetkili mi diye kontrol et
                and().
                httpBasic();// bunu yaparak Basic Auth kullanılacağını belirtiyoruz
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    private DaoAuthenticationProvider authProvider() {//dönen değer class veya interface ise bean yazarım

        DaoAuthenticationProvider autProvider = new DaoAuthenticationProvider();
        autProvider.setPasswordEncoder(passwordEncoder());//encoder ile tanıştırdım
        autProvider.setUserDetailsService(userDetailsService);//Service katımı tanıştırmış oldum

        return autProvider;

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//manager'ı provider ile taıştırdık
       auth.authenticationProvider(authProvider());
    }





}
