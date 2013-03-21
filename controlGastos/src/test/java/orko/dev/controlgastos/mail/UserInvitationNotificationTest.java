package orko.dev.controlgastos.mail;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import orko.dev.controlgastos.email.NotificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class UserInvitationNotificationTest {
	
	@Autowired
	private NotificationService notificationService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testSendMessage() {
		String mailTo = "orquito@gmail.com";
		String message = "Te invito";
		notificationService.sendMessage(mailTo, message);
	}

}
