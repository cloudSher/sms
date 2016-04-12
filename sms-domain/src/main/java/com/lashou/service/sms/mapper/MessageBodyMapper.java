package com.lashou.service.sms.mapper;

import com.lashou.service.sms.domain.MessageBody;
import com.lashou.service.sms.domain.MessageBodyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageBodyMapper {
    int countByExample(MessageBodyExample example);

    int deleteByExample(MessageBodyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageBody record);

    int insertSelective(MessageBody record);

    List<MessageBody> selectByExampleWithBLOBs(MessageBodyExample example);

    List<MessageBody> selectByExample(MessageBodyExample example);

    MessageBody selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageBody record, @Param("example") MessageBodyExample example);

    int updateByExampleWithBLOBs(@Param("record") MessageBody record, @Param("example") MessageBodyExample example);

    int updateByExample(@Param("record") MessageBody record, @Param("example") MessageBodyExample example);

    int updateByPrimaryKeySelective(MessageBody record);

    int updateByPrimaryKeyWithBLOBs(MessageBody record);

    int updateByPrimaryKey(MessageBody record);
}