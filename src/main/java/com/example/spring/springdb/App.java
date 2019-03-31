package com.example.spring.springdb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/example/spring/springdb/beans/beans.xml");

		NoticesDao noticesDao = (NoticesDao) context.getBean("noticesdao");
		try {
			Notice notice1 = new Notice(4,"imran", "imran@livingoncodes.com", "i love slepping");
			Notice notice2 = new Notice(2,"habib", "habib@livingoncodes.com", "i love slepping");
			Notice notice3 = new Notice(5,"joy", "joy@livingoncodes.com", "i love slepping");
			
			List<Notice> noticeList = new ArrayList<Notice>();
			noticeList.add(notice1);
			noticeList.add(notice2);
			noticeList.add(notice3);
			noticesDao.create(noticeList);

			List<Notice> notices = noticesDao.getNotices();
			for (Notice notice : notices) {
				System.out.println(notice);
			}
		} catch (CannotGetJdbcConnectionException e) {
			System.out.println("Could not get jdbc connecton.");
		} catch (DataAccessException e) {
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}

		((ClassPathXmlApplicationContext) context).close();
	}
}
