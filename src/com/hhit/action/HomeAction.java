package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class HomeAction {
	public String index() throws Exception{
		return "index";
	}
}
