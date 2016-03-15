package com.etaofinance.apihttp.common;

import org.springframework.stereotype.Component;

import com.etaofinance.api.common.TransactionalRuntimeException;
import com.etaofinance.core.enums.returnenums.HttpReturnRnums;
import com.etaofinance.core.util.StringUtils;
import com.etaofinance.entity.common.HttpResultModel;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;






import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import java.util.Locale;

/**
 * 全局异常处理
 * 
 * @author zhaohailong
 */
@Component
public class GlobalExceptionHandler implements ExceptionMapper {
	@Override
    public Response toResponse(Throwable ex) {
    	HttpResultModel<String> rep=new HttpResultModel<String>();
    	if (ex instanceof TransactionalRuntimeException||
        	ex instanceof InvalidFormatException) {
	        rep.setCode(HttpReturnRnums.ParaError.value());
	        rep.setMsg(HttpReturnRnums.ParaError.desc());
	        rep.setData(ex.getMessage());
		}else {
	        rep.setCode(HttpReturnRnums.SystemError.value());
	        rep.setMsg(HttpReturnRnums.SystemError.desc());
	        rep.setData(StringUtils.getStackTrace(ex));
		}
        
        ResponseBuilder rb = Response.status(Response.Status.OK);
        rb.type("application/json;charset=UTF-8");
        rb.entity(rep);
        rb.language(Locale.SIMPLIFIED_CHINESE);
        Response r = rb.build();
        return r;
    }  
}


