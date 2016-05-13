package com.hhit.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hhit.base.BaseAction;
import com.hhit.entity.TeaAnswer;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class TeaAnswerAction extends BaseAction<TeaAnswer>{

}
