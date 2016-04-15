package com.etaofinance.api.service.inter;

import java.util.List;

import com.etaofinance.entity.ADVert;
import com.etaofinance.entity.AccountInfo;
import com.etaofinance.entity.Bank;
import com.etaofinance.entity.Message;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.common.PagedResponse;
import com.etaofinance.entity.req.PagedADVertReq;
import com.etaofinance.entity.req.PagedAccountInfoReq;
import com.etaofinance.entity.req.UpdatePwdReq;
import com.etaofinance.entity.resp.*;


public interface IMessageService {
   

    List<Message> getList(Message record);	
    
    HttpResultModel<Object> readMsg(Message msg);
    
    HttpResultModel<Object> delMsg(Message msg);
    
    HttpResultModel<Object> addMsg(Message record);

    HttpResultModel<Object> addMsgSelective(Message record);

    List<Message> getList(Long memberid);	
    Message getByid(Long id);
}
