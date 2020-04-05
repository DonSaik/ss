package lt.viko.eif.donatas.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;



@Component
@PropertySource("classpath:recapthca.properties")
public class CaptchaSettings {

	@Autowired
	private Environment env;
	
	@Bean(name="recapthcaSite")
	public String site (){
		String  site= env.getProperty("google.recaptcha.key.site");
		return site;
	}
	@Bean(name="recapthcaSecret")
	public String secret (){
		String  secret= env.getProperty("google.recaptcha.key.secret");
		return secret;
	}
}
