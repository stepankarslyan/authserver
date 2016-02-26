package hello;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SpringSessionInitializer extends AbstractHttpSessionApplicationInitializer {
	public SpringSessionInitializer() {
	    super(SessionConfiguration.class); 
	
	}
}
