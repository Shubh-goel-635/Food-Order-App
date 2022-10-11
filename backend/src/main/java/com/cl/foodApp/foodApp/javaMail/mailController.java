package com.cl.foodApp.foodApp.javaMail;

import java.util.List;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class mailController {
	@Autowired 
	private Mail mail;
	
	@PostMapping("/sendmail/{orderid}")
	public List<Item> sendMail(@PathVariable int orderid) {
		mail.createOrderMail(orderid, null);
		return null;
	}
}
