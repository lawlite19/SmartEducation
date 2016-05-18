package com.hhit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.Judgement;
import com.hhit.entity.QuestionErrorRecord;
import com.hhit.entity.SingleChoice;
import com.hhit.util.JsonUtil;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class QuestionErrorRecordAction extends BaseAction<QuestionErrorRecord> {

	private Integer questionId;

	/**删除判断*/
	public String deleteJudgement() throws Exception{
		questionErrorRecordService.delete(model.getId());
		return "toMyJudgementError";
	}
	/**删除单选*/
	public String deleteSingle() throws Exception{
		questionErrorRecordService.delete(model.getId());
		return "toMySingleError";
	}
	
	// app
	// ================
	public String appQueErrorRecord() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (model.getQuestionType().equals("judgement")) {
			Judgement judgementFind=judgementService.findById(questionId);
			QuestionErrorRecord recordModel = new QuestionErrorRecord(
					model.getStuNum(), new Timestamp(new Date().getTime()),"judgement",
					judgementFind,null);
			questionErrorRecordService.save(recordModel);
			map.put("name", "success");
		}
		else if(model.getQuestionType().equals("singleChoice")){
			SingleChoice singleChoiceFind=singleChoiceService.findById(questionId);
			QuestionErrorRecord recordModel = new QuestionErrorRecord(
					model.getStuNum(), new Timestamp(new Date().getTime()),"judgement",
					null,singleChoiceFind);
			questionErrorRecordService.save(recordModel);
			map.put("name", "success");
		}
		else{
			map.put("name", "notFindQuestion");
		}
		JsonUtil.toJson(ServletActionContext.getResponse(), map);
		return null;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
}
