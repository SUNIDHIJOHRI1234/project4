package in.co.rays.Project_4.Utility;

import java.util.HashMap;


/**
 *  Class that build Application Email messages.
 * @author Darshan
 *
 */
public class EmailBuilder {
	/**
	 * Returns Successful User Registration Message
	 *
	 * @param map : Message parameters
	 * @return
	 */
	public static String getUserRegistrationMessage(HashMap<String,String> map) {
		StringBuilder msg = new StringBuilder();
		
		msg.append("<HTML><BODY>");
		msg.append("Registration is successful for ORS Project SunilOS");
		msg.append("<H1>Hi!Greetings from SunilOS!</H1>");
		msg.append("<P>Congratulations for registering on ORS!You can now access your ORS account online-anywhere,anytime and enjoy the flexibility to check the Marksheet details.</P>");
		msg.append("<P>Login today at<a href='http://ors.sunraystechnologies.com'>http://ors.sunraystechnologies.com</a>with your follwing credentials:</P>");
		msg.append("<P><B>Login Id:"+map.get("login")+"<BR>"+"Password:"+map.get("password")+"</B></P>");
		msg.append("<P>As a security measure,we recommend that you change your password after your first login.</P>");
		msg.append("<P>For any assistance,please fell free to call as at +91 98273 60504 or 0731-4249244 helpline numbers</P>");
		msg.append("<P>You may also write as at hrd@sunrays.co.in.</P>");
		msg.append("<P>We assure you the best service at all times and look forward to a warm and long-standing association with you.</P>");
		msg.append("<P><a href='http://www.sunrays.co.in'>-SUNRAYS Technolgies</a></P>");
		msg.append("</BODY></HTML>");
		
		return msg.toString();
	}
	/**
	 * Returns Email message of Forget Password
	 *
	 * @param map : params
	 * @return
	 */

	public static String getForgotPasswordMessage(HashMap<String,String> map) {
	StringBuilder msg = new StringBuilder();
	
	msg.append("<HTML><BODY>");
	msg.append("<H1>Your password id recovered!!"+map.get("firstName")+""+map.get("lastName")+"</H1>");
	msg.append("<P><B>To access account user Login Id:"+map.get("login")+"<BR>"+"Password:"+map.get("password"+"</B></P>"));
	msg.append("</BODY></HTML>");
	
	return msg.toString();
	}
	/**
	 * Returns Email message of Change Password
	 *
	 * @param map
	 * @return
	 */
	public static String getChangePasswordMessage(HashMap<String,String> map) {
	StringBuilder msg = new StringBuilder();
	
	msg.append("<HTML><BODY>");
	msg.append("<H1>Your password has been changed successfully!!"+map.get("firstName")+""+map.get("lastName")+"</H1>");
	msg.append("<P><B>To access account user Login Id:\"+map.get(\"login\")+\"<BR>\"+\"Password:\"+map.get(\"password\"+\"</B></P>");
	msg.append("</BODY></HTML>");
	
	return msg.toString();
	}
}