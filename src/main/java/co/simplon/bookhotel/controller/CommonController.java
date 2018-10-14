package co.simplon.bookhotel.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.simplon.bookhotel.model.ContactMail;
import co.simplon.bookhotel.service.CommonService;


@Controller
@RequestMapping("/")
public class CommonController {

	@Inject
	CommonService commonService;
	
	public CommonController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value = "/sendContactMail", method=RequestMethod.POST) 
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Boolean sendContactMail(@RequestBody ContactMail contactMail) {
		return commonService.sendContactMail(contactMail);		
	}

}
