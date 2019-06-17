package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

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
        birthdayService = new BirthdayService();
    }

    @After
    public void tearDown() throws Exception {
        mailServer.stop();
        Thread.sleep(200);
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        birthdayService.sendGreetings("employee_data.txt", LocalDate.of(2008, 10, 8), "localhost", NONSTANDARD_PORT);

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
        birthdayService.sendGreetings("employee_data.txt", LocalDate.of(2008, 1, 1), "localhost", NONSTANDARD_PORT);

        assertEquals("what? messages?", 0, mailServer.getReceivedEmails().size());
    }
}
