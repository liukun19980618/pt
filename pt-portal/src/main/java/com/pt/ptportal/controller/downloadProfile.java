package com.pt.ptportal.controller;

import com.pt.ptportal.entity.file;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

@RestController
 public class downloadProfile {
     @Autowired
     com.pt.ptportal.dao.fileDao fileDao;
    @GetMapping("/findAllFile/{page}/{size}")
    public Page<file> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageRequest request = PageRequest.of(page,size);
        return fileDao.findAll(request);
    }
     @GetMapping("/download/{id}")
     public void downFile(@PathVariable(name = "id") Integer id, HttpServletResponse res) {

         try {
             //获取要下载的模板名称
              String fileName=fileDao.findById(id).get().getModel();
             System.out.println(fileName);
             //设置要下载的文件的名称
             res.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));

//             res.setHeader("Content-disposition", "attachment;fileName=" + fileName);
             //通知客服文件的MIME类型
//             res.setContentType("application/vnd.ms-excel;charset=UTF-8");
               res.setContentType("multipart/form-data;charset=UTF-8");

             //excel模板路径
             File cfgFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/model/"+fileName);
             FileInputStream input = new FileInputStream(cfgFile);
             OutputStream out = res.getOutputStream();
             byte[] b = new byte[2048];
             int len;
             while ((len = input.read(b)) != -1) {
                 out.write(b, 0, len);
             }
             //修正 Excel在“xxx.xlsx”中发现不可读取的内容。是否恢复此工作薄的内容？如果信任此工作簿的来源，请点击"是"
             res.setHeader("Content-Length", String.valueOf(input.getChannel().size()));
             input.close();
             System.out.println("应用导入模板下载完成");
             }
         catch (Exception ex) {
             System.out.println("应用导入模板下载失败！");
             }
         }
     }