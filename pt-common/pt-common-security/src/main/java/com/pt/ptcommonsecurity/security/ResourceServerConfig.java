package com.pt.ptcommonsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.UserAuthenticationConverter;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author wl
 */
@Configuration
@EnableResourceServer
@ComponentScan("com.pt.ptcommonsecurity")
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected RemoteTokenServices remoteTokenServices;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/info/**").permitAll()
                .antMatchers("/files/**").permitAll()
                .antMatchers("/file/**").permitAll()
                .antMatchers("/News/**").permitAll()
                .antMatchers("/Announcement/**").permitAll()
                .antMatchers("/Blogcomment/**").permitAll()
                .antMatchers("/blog/**").permitAll()
                .antMatchers("/comment/**").permitAll()
                .antMatchers("/download/**").permitAll()
                .antMatchers("/comment/**").permitAll()
                .antMatchers("/notice/**").permitAll()
                .antMatchers(
                        "/webjars/**",
                        "/resources/**",
                        "/swagger-ui.html",
                        "/swagger-resources/**",
                        "/v2/api-docs")
                .permitAll()
                .anyRequest().authenticated();
    }

    @Override
    @CrossOrigin
    public void configure(ResourceServerSecurityConfigurer resources) {
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        UserAuthenticationConverter userTokenConverter = new CustomUserAuthenticationConverter();
        accessTokenConverter.setUserTokenConverter(userTokenConverter);
        remoteTokenServices.setAccessTokenConverter(accessTokenConverter);
        resources
                .tokenServices(remoteTokenServices);
    }

}
