package com.pt.ptmanor.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.pt.ptmanor.pojo.YunRole;
import com.pt.ptmanor.pojo.YunUser;

import java.util.Set;

public class YunUserVo extends YunUser {
    private Set<String> menuList;
    private Set<String> permissionList;
    @TableField(exist = false)
    private YunRole role;

    private  String roleName;


    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public void setAge(Integer age) {
        this.age = age;
    }

    private Integer age;


    private String phoneNumber;

    private String gender;
    @Override
    public String getRoleName() {
        return roleName;
    }

    @Override
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public YunRole getRole() {
        return role;
    }

    public void setRole(YunRole role) {
        this.role = role;
    }
    public void setMenuList(Set<String> menuList) {
        this.menuList = menuList;
    }

    public void setPermissionList(Set<String> permissionList) {
        this.permissionList = permissionList;
    }

    public Set<String> getMenuList() {
        return menuList;
    }

    public Set<String> getPermissionList() {
        return permissionList;
    }
}
