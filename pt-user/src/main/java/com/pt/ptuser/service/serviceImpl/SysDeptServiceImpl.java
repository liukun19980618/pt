package com.pt.ptuser.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.pt.ptcommoncore.constant.CommonConstants;
import com.pt.ptcommonsecurity.exception.CustomException;
import com.pt.ptuser.dto.TreeSelect;
import com.pt.ptuser.entity.SysDept;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pt.ptuser.mapper.SysDeptMapper;
import com.pt.ptuser.service.SysDeptService;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService{

    @Override
    public List<SysDept> selectDeptList( SysDept sysDept) {
        return baseMapper.selectDeptList(sysDept);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<SysDept> depts)
    {
        List<SysDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect::new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */

    public List<SysDept> buildDeptTree(List<SysDept> depts)
    {
        List<SysDept> returnList = new ArrayList<SysDept>();
        List<String> tempList = new ArrayList<String>();
        for (SysDept dept : depts)
        {
            tempList.add(dept.getDeptId());
        }
        for (Iterator<SysDept> iterator = depts.iterator(); iterator.hasNext();)
        {
            SysDept dept = (SysDept) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }
    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t)
    {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysDept tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                // 判断是否有子节点
                Iterator<SysDept> it = childList.iterator();
                while (it.hasNext())
                {
                    SysDept n = (SysDept) it.next();
                    recursionFn(list, n);
                }
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t)
    {
        List<SysDept> tlist = new ArrayList<SysDept>();
        Iterator<SysDept> it = list.iterator();
        while (it.hasNext())
        {
            SysDept n = (SysDept) it.next();
            if (StrUtil.isNotEmpty(n.getParentId()) && n.getParentId().equals(t.getDeptId()))
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public Boolean checkDeptExistUser(String deptId)
    {
        if(baseMapper.checkDeptExistUser(deptId) != 0){
            throw new CustomException("部门存在用户,不允许删除");
        }
        return Boolean.TRUE;
    }

    /**
     * 校验部门名称是否唯一
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public Boolean checkDeptNameUnique(SysDept dept)
    {
        if(StrUtil.isEmpty(dept.getDeptId())){

            return Boolean.TRUE;
        }

        SysDept sysDept = baseMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (sysDept != null && !sysDept.getDeptId().equals(dept.getDeptId()))
        {
            throw new CustomException("新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
        }
        return Boolean.TRUE;
    }
    /**
     * 新增保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public Boolean insertDept(SysDept dept)
    {
        SysDept sysDept = baseMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (CommonConstants.DEPT_DISABLE.equals(sysDept.getStatus()))
        {
            return Boolean.FALSE;
        }
        dept.setAncestors(sysDept.getAncestors() + "," + dept.getParentId());
        return baseMapper.insertDept(dept);
    }
    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(String deptId)
    {
        return baseMapper.selectDeptById(deptId);
    }

    /**
     * 删除部门管理信息
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public Boolean deleteDeptById(String deptId)
    {
        return baseMapper.deleteDeptById(deptId);
    }
    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public Boolean hasChildByDeptId(String deptId)
    {
        int result = baseMapper.hasChildByDeptId(deptId);
        if (result > 0) {
            throw new CustomException("存在下级部门,不允许删除");
        }
        return  true;
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(String deptId)
    {
        return baseMapper.selectNormalChildrenDeptById(deptId);
    }
    /**
     * 修改保存部门信息
     *
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int updateDept(SysDept dept)
    {
        if (dept.getParentId().equals(dept.getDeptId())) {
            throw new CustomException("修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
        }
        else if (CommonConstants.DEPT_DISABLE.equals(dept.getStatus())
                && this.selectNormalChildrenDeptById(dept.getDeptId()) > 0)
        {
            throw new CustomException("该部门包含未停用的子部门！");
        }
        SysDept newParentDept = baseMapper.selectDeptById(dept.getParentId());
        SysDept oldDept = baseMapper.selectDeptById(dept.getDeptId());
        if (newParentDept != null && oldDept != null)
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = baseMapper.updateDept(dept);
        if (CommonConstants.DEPT_NORMAL.equals(dept.getStatus()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatus(dept);
        }
        return result;
    }
    /**
     * 修改该部门的父级部门状态
     *
     * @param dept 当前部门
     */
    private void updateParentDeptStatus(SysDept dept)
    {
        String updateBy = dept.getUpdateBy();
        dept = baseMapper.selectDeptById(dept.getDeptId());
        dept.setUpdateBy(updateBy);
        baseMapper.updateDeptStatus(dept);
    }

    /**
     * 修改子元素关系
     *
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(String deptId, String newAncestors, String oldAncestors)
    {
        List<SysDept> children = baseMapper.selectChildrenDeptById(deptId);
        for (SysDept child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            baseMapper.updateDeptChildren(children);
        }
    }
}
