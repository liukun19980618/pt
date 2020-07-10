package com.pt.ptcommoncore.constant.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 微服务uri
 *
 * @author wl
 * @date 2020/5/20
 */
@Getter
@AllArgsConstructor
public enum UriEnum {


    DEALER("http://localhost:9003"),
    CENTER("http://localhost:9004"),
    USER_CLIENT("http://localhost:9003");

    private final String uri;


}
