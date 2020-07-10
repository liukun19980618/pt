package com.pt.ptauth.feign;

import com.pt.ptuser.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author wl
 */
@FeignClient(name = "pt-user")
public interface RemotePtUserClient {

    /**
     * 获取用户信息
     * @param username
     * @param clientId
     * @return
     */
    @GetMapping("/user/info/{username}")
    UserInfo info(@PathVariable String username, @RequestParam(value = "clientId") String clientId);
}
