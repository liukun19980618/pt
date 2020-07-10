package com.pt.ptuser.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptuser.entity.SysUserPost;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pt.ptuser.mapper.SysUserPostMapper;
import com.pt.ptuser.service.SysUserPostService;

import java.util.List;

@Service
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements SysUserPostService{

    @Override
    public Boolean deleteUserPostByUserId(String userId) {
        return baseMapper.deleteUserPostByUserId(userId);
    }

    @Override
    public int countUserPostById(String postId) {
        return baseMapper.countUserPostById(postId);
    }

    @Override
    public Boolean deleteUserPost(String[] ids) {
        return baseMapper.deleteUserPost(ids);
    }

    @Override
    public Boolean batchUserPost(List<SysUserPost> userPostList) {
        return baseMapper.batchUserPost(userPostList);
    }
}
