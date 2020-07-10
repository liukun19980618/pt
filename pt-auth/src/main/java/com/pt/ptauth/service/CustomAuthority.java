package com.pt.ptauth.service;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author wl
 * @date 2020/5/19
 */
public class CustomAuthority implements GrantedAuthority
{
    private  String authority;

    public CustomAuthority(String authority) {
        this.authority = authority;
    }



    @Override
    public String getAuthority() {
        return authority;
    }
}
