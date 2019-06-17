package com.cegeka.birthday.infrastructure;

import com.cegeka.birthday.application.BirthdayService;
import com.cegeka.birthday.domain.MessagingService;
import com.cegeka.birthday.domain.EmployeeRepo;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MessagingService messagingService = new EmailServiceImpl("localhost", 25, "sender@here.com");
        EmployeeRepo employeeRepo = new EmployeeRepoImpl("employee_data.txt");
        BirthdayService service = new BirthdayService(messagingService, employeeRepo);
        service.sendGreetings(LocalDate.now());
    }
}
