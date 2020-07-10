package com.pt.ptmanor.service.user;

import com.pt.ptmanor.pojo.user.SysUser;

import java.util.List;

public interface UserService {


    List<SysUser> getCheckInvoiceUserList();

    List<SysUser> getFinancialUserList();
}
