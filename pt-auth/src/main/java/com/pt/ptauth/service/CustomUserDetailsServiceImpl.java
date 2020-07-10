package com.pt.ptauth.service;


import cn.hutool.core.util.ArrayUtil;
import com.pt.ptauth.feign.RemotePtUserClient;
import com.pt.ptauth.feign.RemoteUserFeignClient;
import com.pt.ptauth.util.RedisUtils;
import com.pt.ptcommoncore.constant.CacheConstants;
import com.pt.ptcommoncore.constant.SecurityConstants;
import com.pt.ptcommoncore.security.CustomUser;
import com.pt.ptuser.dto.UserInfo;
import com.pt.ptuser.entity.SysUser;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author wl
 * @date 2020/5/19
 */
@Service("CustomUserDetailsServiceImpl")
@Slf4j
@AllArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private RedisUtils redisUtils;
    @Autowired
    RemotePtUserClient remotePtUserClient;

    /**
     * 通过clientId区分访问客户端，但并未考虑并发登录
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Object clientIdObject = redisUtils.get(CacheConstants.CLIENT_DETAILS_KEY);
//        String clientId = clientIdObject.toString();
//        String uri = UriEnum.valueOf(clientId.toUpperCase()).getUri();
//        RemoteUserFeignClient service = Feign.builder()
//                .decoder(new JacksonDecoder())
//                .encoder(new JacksonEncoder())
//                .target(RemoteUserFeignClient.class, uri);
//        UserInfo info = service.info(username);
//        CustomUser user = getUserDetails(info);
//        return user;
//
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Object clientIdObject = redisUtils.get(CacheConstants.CLIENT_DETAILS_KEY);
        String clientId = clientIdObject.toString();
        UserInfo info = remotePtUserClient.info(username,clientId);
        CustomUser user = getUserDetails(info);
        user.setClientId(clientId);
        return user;

    }
    /**
     * 构建userdetails
     *
     * @param userInfo 用户信息
     * @return
     */
    private CustomUser getUserDetails(UserInfo userInfo) {
        if (userInfo == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
//        //将LinkHashMap转换类型
//        JSONObject jsonObject = JSONUtil.parseObj(result.getData());
//        UserInfo userInfo = JSONUtil.toBean(jsonObject, UserInfo.class);
        SysUser user = userInfo.getSysUser();
        //添加权限
        Collection<CustomAuthority> customAuthorityCollection = new ArrayList<>();
        if(ArrayUtil.isNotEmpty(userInfo.getRoles())){
            Arrays.stream(userInfo.getRoles()).forEach(role ->customAuthorityCollection.add(new CustomAuthority(SecurityConstants.ROLE+role)));
        }
        //构建user对象
        return new CustomUser(user.getUserName(),user.getPassword(),user.getUserId(),user.getUserName(),user.getNickName(),user.getDeptId(),"", customAuthorityCollection);
   }
}
