package com.fittrio.fitday.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fittrio.fitday.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@AutoConfigureOrder
	@Qualifier("commentservice")
	CommentService commentService;
	
	//코멘트 등록
	
	
	
	
}