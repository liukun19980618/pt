package com.pt.ptmanor.vo;


import com.pt.ptmanor.pojo.YunRoleMenus;

import java.util.List;

public class YunRoleMenusVo  extends YunRoleMenus {

    private List<YunMenusVo> yunMenus;

    private  List<String> children;

    public List<YunMenusVo> getYunMenus() {
        return yunMenus;
    }

    public void setYunMenus(List<YunMenusVo> yunMenus) {
        this.yunMenus = yunMenus;
    }

    public List<String> getChildren() {
        return children;
    }

    public void setChildren(List<String> children) {
        this.children = children;
    }
}
