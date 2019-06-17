package com.cegeka.birthday.application;

import com.cegeka.birthday.domain.BirthdayMessage;
import com.cegeka.birthday.domain.MessagingService;
import com.cegeka.birthday.domain.Employee;
import com.cegeka.birthday.domain.EmployeeRepo;

import java.time.LocalDate;

public class BirthdayService {
    private MessagingService messagingService;
    private EmployeeRepo employeeRepo;

    public BirthdayService(MessagingService messagingService, EmployeeRepo employeeRepo) {
        this.messagingService = messagingService;
        this.employeeRepo = employeeRepo;
    }

    public void sendGreetings(LocalDate date) {
        employeeRepo.findAll().stream()
            .filter(employee -> employee.isBirthday(date))
            .forEach(this::sendEmailOnBirthday);
    }

    public void sendEmailOnBirthday(Employee employee) {
        BirthdayMessage msg =
            new BirthdayMessage("Happy Birthday!", "Happy Birthday, dear %NAME%!".replace("%NAME%", employee.getFirstName()));
        messagingService.sendMessage(employee, msg);
    }
}
