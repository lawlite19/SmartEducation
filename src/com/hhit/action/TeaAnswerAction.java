package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.StuQuestion;
import com.hhit.entity.TeaAnswer;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeaAnswerAction extends BaseAction<TeaAnswer>{

	private Integer stuQuestionId;
	
//app
//========================
	public String appTeaAnswer() throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		//找到问题
		StuQuestion stuQuestionFind=stuQuestionService.findById(stuQuestionId);
		if(stuQuestionFind==null){
			map.put("name", "noStuQuestion");
		}
		else{
			//设置未封装的属性
			model.setAddTime(new Timestamp(new Date().getTime()));
			model.setStuQuestion(stuQuestionFind);
			//保存
			teaAnswerService.save(model);
			//设置问题已回答
			stuQuestionFind.setDone(1);
			stuQuestionService.update(stuQuestionFind);
			map.put("name", "success");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	
	
	
//=========
	public Integer getStuQuestionId() {
		return stuQuestionId;
	}
	public void setStuQuestionId(Integer stuQuestionId) {
		this.stuQuestionId = stuQuestionId;
	}
}
