package orko.dev.controlgastos.model;

import java.util.ArrayList;

import org.junit.Before;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class CommonTestClass {
	
	@Before
	public void setUp(){
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(new User("demo","sad",new ArrayList()), "jhs"));
	}

}
