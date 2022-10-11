package com.cl.foodApp.foodApp.javaMail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cl.foodApp.foodApp.dao.FoodOrderDao;
import com.cl.foodApp.foodApp.dto.FoodOrder;
import com.cl.foodApp.foodApp.dto.Item;

@Service
public class Mail {
	@Autowired
	private FoodOrderDao foodOrderDao;
	
	private static String toTitleCase(String input) {
	    StringBuilder titleCase = new StringBuilder(input.length());
	    boolean nextTitleCase = true;

	    for (char c : input.toCharArray()) {
	        if (Character.isSpaceChar(c)) {
	            nextTitleCase = true;
	        } else if (nextTitleCase) {
	            c = Character.toTitleCase(c);
	            nextTitleCase = false;
	        }

	        titleCase.append(c);
	    }

	    return titleCase.toString();
	}
	
	private void sendEmail(String to, String subject, String message) {
		String from = "tastify.me@gmail.com";
		String password = "jpuqratvvokegngl";
		//variable for host
		String host = "smtp.gmail.com";
		
		Properties properties = System.getProperties();
		System.out.println("Properties" + properties);
		
		//setting imp. properties
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		// step:1 get the session
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() { 
				return new PasswordAuthentication(from, password);
			}
		});
		
		session.setDebug(true);
		
		// step 2: compose the message
		MimeMessage m = new MimeMessage(session);
		try {
			//from
			m.setFrom(from);	
			
			// to
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			
			//subject
			m.setSubject(subject);
			
			//message
			m.setContent(message, "text/html");
			
			//step:3 send the message using transport class
			Transport.send(m);
			System.out.println("sent success");
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public  void createOrderMail(int orderid, List<Item> items){
		FoodOrder foodOrder = foodOrderDao.getFoodOrderById(orderid).get();
		String name = toTitleCase(foodOrder.getCustomerName());
		float totalprice = foodOrder.getTotalPrice();
		String to = foodOrder.getCustomerEmail();
		String subject = "Order Placed";
		String status = toTitleCase(foodOrder.getStatus());
		
		LocalDateTime delTime = foodOrder.getOrderDeliveryTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String message = String.format("<h1>Order Receive: %s</h1>\r\n"
				+ "<p>Hi, <b>%s</b> thanks for ordering on <b>Tastify</b>. Your ordered will be delivered by %s.</p>\r\n", orderid, name,  delTime.format(formatter));
		for (Item item : items) {
			message += String.format("<p>%s %s x %s = %s</p>\r\n",toTitleCase(item.getName()), item.getPrice(), item.getQuantity(),item.getPrice()*item.getQuantity());
		}
		message += String.format("<h2>Order Status : %s</h2>", status);
		message += String.format("<h2>Total Price : %s</h2>", totalprice);
		
		this.sendEmail(to, subject, message);
	}
	
	public void updateOrderMail(FoodOrder foodOrder) {
		String name = toTitleCase(foodOrder.getCustomerName());
		int orderid = foodOrder.getId();
		float totalprice = foodOrder.getTotalPrice();
		String to = foodOrder.getCustomerEmail();
		String subject = "Order Updated";
		String status = toTitleCase(foodOrder.getStatus());
		
		LocalDateTime delTime = foodOrder.getOrderDeliveryTime();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		
		String message = String.format("<h1>Order Updated: %s</h1>\r\n"
				+ "<p>Hi, <b>%s</b> thanks for ordering on <b>Tastify</b>. Your ordered will be delivered by %s.</p>\r\n", orderid, name,  delTime.format(formatter));
		message += String.format("<h2>Order Status : %s</h2>", status);
		message += String.format("<h2>Total Price : %s</h2>", totalprice);
		
		this.sendEmail(to, subject, message);
	}
	
}
