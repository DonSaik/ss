package lt.viko.eif.donatas.recaptcha;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class RecaptchaServiceImpl implements RecaptchaService {
	
	
		@Autowired
		@Qualifier("recapthcaSecret")
	  String recaptchaSecret;
		 
	    private static class RecaptchaResponse {
	        @JsonProperty("success")
	        private boolean success;
	        @JsonProperty("error-codes")
	        private Collection<String> errorCodes;
	    }
	    private final RestTemplate restTemplate;

		private static final String recaptchaUrl =
			    "https://www.google.com/recaptcha/api/siteverify";
		@Autowired
	    public RecaptchaServiceImpl(RestTemplate restTemplate) {
	        this.restTemplate = restTemplate;
	    }

		@Override
		public boolean isResponseValid( String response) throws Exception {
			RecaptchaResponse recaptchaResponse;
	        try {
	            recaptchaResponse = restTemplate.postForEntity(
	                    recaptchaUrl, createBody(recaptchaSecret,  response), RecaptchaResponse.class)
	                    .getBody();
	        } catch (RestClientException e) {
	            throw new Exception("Recaptcha API not available due to exception", e);
	        }
	        return recaptchaResponse.success;
		}
		 private MultiValueMap<String, String> createBody(String secret, String response) {
		        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		        form.add("secret", secret);
		        form.add("response", response);
		        return form;
		    }
}
