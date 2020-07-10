package com.pt.ptuser.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptcommoncore.util.IdUtils;
import com.pt.ptcommonsecurity.exception.CustomException;
import com.pt.ptuser.entity.SysMenu;
import com.pt.ptuser.entity.SysPost;
import com.pt.ptuser.service.SysUserPostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pt.ptuser.mapper.SysPostMapper;
import com.pt.ptuser.service.SysPostService;

import java.util.List;

/**
 * @author wl
 */
@Service
@AllArgsConstructor
public class SysPostServiceImpl extends ServiceImpl<SysPostMapper,SysPost> implements SysPostService {

    private SysUserPostService sysUserPostService;

    @Override
    public IPage getPostPage(Page page, SysPost sysPost) {
        return baseMapper.getPostPage(page,sysPost);
    }

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @return 岗位信息集合
     */
    @Override
    public List<SysPost> selectPostList(SysPost post)
    {
        return baseMapper.selectPostList(post);
    }



    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll()
    {
        return baseMapper.selectPostAll();
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPost selectPostById(String postId)
    {
        return baseMapper.selectPostById(postId);
    }

    /**
     * 根据用户ID获取岗位选择框列表
     *
     * @param userId 用户ID
     * @return 选中岗位ID列表
     */
    @Override
    public List<String> selectPostListByUserId(String userId)
    {
        return baseMapper.selectPostListByUserId(userId);
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public Boolean checkPostNameUnique(SysPost post)
    {
        if(StrUtil.isEmpty(post.getPostId())){
            return Boolean.TRUE;
        }
        SysPost sysPost = baseMapper.checkPostNameUnique(post.getPostName());

        if (sysPost != null && !sysPost.getPostId().equals(sysPost.getPostId()))
        {
            throw new CustomException("新增岗位'" + post.getPostName() + "'失败，岗位名称已存在");
        }
        return Boolean.TRUE;

    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public Boolean checkPostCodeUnique(SysPost post)
    {
        if(StrUtil.isEmpty(post.getPostId())){
            return Boolean.TRUE;
        }
        SysPost sysPost = baseMapper.checkPostCodeUnique(post.getPostCode());

        if (sysPost != null && !sysPost.getPostId().equals(sysPost.getPostId()))
        {
            throw new CustomException("新增岗位'" + post.getPostName() + "'失败，岗位编码已存在");
        }
        return Boolean.TRUE;
    }

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int countUserPostById(String postId)
    {
        return sysUserPostService.countUserPostById(postId);
    }

    /**
     * 删除岗位信息
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public Boolean deletePostById(String postId)
    {
        return baseMapper.deletePostById(postId);
    }

    /**
     * 批量删除岗位信息
     *
     * @param postIds 需要删除的岗位ID
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    public Boolean deletePostByIds(String[] postIds)
    {
        for (String postId : postIds)
        {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0)
            {
                throw new CustomException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
        }
        return baseMapper.deletePostByIds(postIds);
    }

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public Boolean insertPost(SysPost post)
    {
        post.setPostId(IdUtils.simpleUUID());
        return baseMapper.insertPost(post);
    }

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public Boolean updatePost(SysPost post)
    {
        return baseMapper.updatePost(post);
    }
}
