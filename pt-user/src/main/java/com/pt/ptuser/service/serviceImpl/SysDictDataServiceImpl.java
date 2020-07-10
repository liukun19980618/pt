package com.pt.ptuser.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pt.ptuser.entity.SysDictData;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pt.ptuser.mapper.SysDictDataMapper;
import com.pt.ptuser.service.SysDictDataService;

import java.util.List;

/**
 * @author wl
 */
@Service
public class SysDictDataServiceImpl extends ServiceImpl<SysDictDataMapper, SysDictData> implements SysDictDataService{

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType)
    {
        return baseMapper.selectDictDataByType(dictType);
    }
}
