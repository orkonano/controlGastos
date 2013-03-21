package orko.dev.controlgastos.security;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
public class PasswordEncodeTest {
	
	@Autowired
	private StandardPasswordEncoder encoder;


	@Test
	public void test() {

		String s = "hola";
		String encode = this.encoder.encode(s);
		Assert.assertTrue(this.encoder.matches(s, encode));
		
	}

}
