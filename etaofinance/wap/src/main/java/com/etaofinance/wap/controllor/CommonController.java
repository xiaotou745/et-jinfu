package com.etaofinance.wap.controllor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.etaofinance.api.service.inter.IMemberService;
import com.etaofinance.entity.common.HttpResultModel;
import com.etaofinance.entity.req.SendCodeReq;


/**
 * ����ģ��
 * @author hulingbo
 * @date 2016��4��5��16:15:06
 *
 */
@Controller
@RequestMapping("common")
public class CommonController {
	@Autowired
	IMemberService memberService;	
	
	@RequestMapping("/swagger")
	public ModelAndView suggAdd() {
		ModelAndView model = new ModelAndView("common/swagger");
		return model;
	}

	/**
	 * 图形验证码֤
	 * @param 
	 * @author ruhuaxiao 
	 * @date 2016??3??25??16:53:16
	 * @return
	 */
	@RequestMapping("code")
	public ModelAndView code(int type) {
		ModelAndView mv = new ModelAndView("user/code");
		mv.addObject("CodeType", type);
		return mv;
	}
	/**
	 * 短信验证码
	 * @param req
	 * @return
	 */
	@RequestMapping("sendcode")
	@ResponseBody
	public  HttpResultModel<Object> sendcode(@RequestBody SendCodeReq req) {
		return memberService.sendCode(req);
	}

}
