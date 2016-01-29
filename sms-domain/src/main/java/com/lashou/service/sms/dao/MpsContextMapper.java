package com.lashou.service.sms.dao;

import com.lashou.service.sms.domain.MpsContext;
import com.lashou.service.sms.domain.MpsContextExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MpsContextMapper {
    int countByExample(MpsContextExample example);

    int deleteByExample(MpsContextExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MpsContext record);

    int insertSelective(MpsContext record);

    List<MpsContext> selectByExample(MpsContextExample example);

    MpsContext selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MpsContext record, @Param("example") MpsContextExample example);

    int updateByExample(@Param("record") MpsContext record, @Param("example") MpsContextExample example);

    int updateByPrimaryKeySelective(MpsContext record);

    int updateByPrimaryKey(MpsContext record);
}