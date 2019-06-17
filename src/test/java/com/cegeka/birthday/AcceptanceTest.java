package com.cegeka.birthday;

import static org.junit.Assert.*;

import com.cegeka.birthday.application.BirthdayService;
import com.cegeka.birthday.domain.MessagingService;
import com.cegeka.birthday.domain.EmployeeRepo;
import com.cegeka.birthday.infrastructure.EmailServiceImpl;
import com.cegeka.birthday.infrastructure.EmployeeRepoImpl;
import org.junit.*;

import com.dumbster.smtp.*;

import java.time.LocalDate;
import java.util.List;


public class AcceptanceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private BirthdayService birthdayService;
    private SimpleSmtpServer mailServer;

    @Before
    public void setUp() throws Exception {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
        MessagingService messagingService = new EmailServiceImpl("localhost", NONSTANDARD_PORT, "sender@here.com");
        EmployeeRepo employeeRepo = new EmployeeRepoImpl("employee_data.txt");
        birthdayService = new BirthdayService(messagingService, employeeRepo);
    }

    @After
    public void tearDown() throws Exception {
        mailServer.stop();
        Thread.sleep(200);
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        birthdayService.sendGreetings(LocalDate.of(2008, 10, 8));

        assertEquals("message not sent?", 1, mailServer.getReceivedEmails().size());
        SmtpMessage message = mailServer.getReceivedEmails().get(0);
        assertEquals("Happy Birthday, dear John!", message.getBody());
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
        List<String> recipients = message.getHeaderValues("To");
        assertEquals(1, recipients.size());
        assertEquals("john.doe@foobar.com", recipients.get(0));
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        birthdayService.sendGreetings(LocalDate.of(2008, 1, 1));

        assertEquals("what? messages?", 0, mailServer.getReceivedEmails().size());
    }
}
