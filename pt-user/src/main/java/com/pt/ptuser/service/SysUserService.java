package com.pt.ptuser.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pt.ptuser.dto.UserInfo;
import com.pt.ptuser.entity.SysUser;
import com.pt.ptuser.vo.UserVo;

import java.util.List;

public interface SysUserService extends IService<SysUser> {


   UserInfo findUserByUsername(String username,String clientId);

   /**
    * 分页查询部门用户信息（含有角色信息）
    *
    * @param page    分页对象
    * @param clientId 客户端ID
    * @return
    */
   IPage getDeptUserWithRolePage(Page page,String clientId,String deptId);

   /**
    * 分页查询所有用户信息（含有角色信息）
    *
    * @param page    分页对象
    * @return
    */
   IPage getAllUserWithRolePage(Page page, UserVo userVO);

   /**
    * 根据id查找用户
    * @param userId
    * @return
    */
   SysUser getByUserId(String userId);

   /**
    * 校验用户是否允许操作
    *
    * @param user 用户信息
    */
   Boolean checkUserAllowed(SysUser user);
   /**
    * 修改用户状态
    *
    * @param user 用户信息
    * @return 结果
    */
   Boolean updateUserStatus(SysUser user);

   /**
    * 新增用户信息
    *
    * @param user 用户信息
    * @return 结果
    */
   Boolean insertUser(SysUser user);
   /**
    * 校验用户名称是否唯一
    *
    * @param user 用户
    * @return 结果
    */
   Boolean checkUserNameUnique(SysUser user);

   /**
    * 校验手机号码是否唯一
    *
    * @param user 用户信息
    * @return 结果
    */
   Boolean checkPhoneUnique(SysUser user);

   /**
    * 校验email是否唯一
    *
    * @param user 用户信息
    * @return 结果
    */
   Boolean checkEmailUnique(SysUser user);

   /**
    * 重置用户密码
    *
    * @return 结果
    */
   Boolean resetUserPwd(String userName,String passWord);


   /**
    * 批量删除用户信息
    *
    * @param userIds 需要删除的用户ID
    * @return 结果
    */
   Boolean deleteUserByIds(String[] userIds);
   /**
    * 通过用户ID删除用户
    *
    * @param userId 用户ID
    * @return 结果
    */
   Boolean deleteUserById(String userId);

   /**
    * 修改用户信息
    *
    * @param user 用户信息
    * @return 结果
    */
   Boolean updateUser(SysUser user);
   /**
    * 获取用户列表
    * @return
    */
   List<SysUser> listUser();
   /**
    * 获取部门用户列表
    * @param deptId
    * @return
    */
   List<SysUser> listUserByDept(String deptId);
   /**
    * 根据职位获取用户列表
    * @return
    */
   List<SysUser> listUserByPost(String deptId,String[] post);
   /**
    * 根据权限获取用户列表
    * @param perms
    * @return
    */
   List<SysUser> listUserByPerms(String[] perms);
   /**
    * 根据用户ID查询用户所属角色组
    *
    * @param userName 用户名
    * @return 结果
    */
   String selectUserRoleGroup(String userName);

   /**
    * 根据用户ID查询用户所属岗位组
    *
    * @param userName 用户名
    * @return 结果
    */
   String selectUserPostGroup(String userName);

}
