package com.hhit.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.UserDetails;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class UserDetailsAction extends BaseAction<UserDetails>{
	public String list() throws Exception{
		List<UserDetails> userDetails=userDetailsService.findAll();
		//userDetails.get(0).
		ActionContext.getContext().put("userDetailsList",userDetails);
		return "list";
	}
}
