package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class QuestionBankAction {

	/** 跳转题库批量导入界面 */
	public String bulkImportUI() throws Exception{
		return "bulkImportUI";
	}
}
