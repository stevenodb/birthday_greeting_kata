package com.cegeka.birthday.domain;

public interface MessagingService {
    void sendMessage(Employee employee, BirthdayMessage birthdayMessage);
}
