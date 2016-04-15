package com.etaofinance.api.dao.inter;

import java.util.List;

import com.etaofinance.entity.Message;

public interface IMessageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);
    
    List<Message> getList(Long memberid);
}