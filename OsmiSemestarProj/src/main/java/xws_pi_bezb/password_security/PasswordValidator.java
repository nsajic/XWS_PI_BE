package xws_pi_bezb.password_security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator{

	  private Pattern pattern;
	  private Matcher matcher;

	  private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";


	  
	  public boolean validate(final String password){

		  pattern = Pattern.compile(PASSWORD_PATTERN);
		  matcher = pattern.matcher(password);
		  return matcher.matches();
	  }
}
