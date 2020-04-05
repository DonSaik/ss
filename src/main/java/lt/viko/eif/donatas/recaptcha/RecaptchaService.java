package lt.viko.eif.donatas.recaptcha;

public interface RecaptchaService {
	boolean isResponseValid(String response) throws Exception;

}
