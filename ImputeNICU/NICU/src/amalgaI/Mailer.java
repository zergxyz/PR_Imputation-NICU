package amalgaI;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;



public class Mailer {

	private static Logger logger = Logger.getLogger("edu.mayo.ctsa.awards");
	

	public static void postMail(String subject, String content, String[] recipients, String[] cc, String from) throws MessagingException, IOException {
		logger.trace("content:" + content + " recipients:");
		for(int i=0; i<recipients.length; i++)
			logger.trace("recipient:" + recipients[i]);
		
		boolean debug = false;

		// create some properties and get the default Session
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtprelay.mayo.edu");
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);

		// create a message
	    MimeMessage msg = new MimeMessage(session);

		// set the 'from' address
		try {
			if( from != null )
				msg.setFrom(new InternetAddress(from));
		}catch(Exception e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			logger.warn("'from' email not valid!  Continue on...");
		}

		// set the 'to' address(s)
		InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);

		// set the 'cc' address(s)
		InternetAddress[] copyTo = new InternetAddress[cc.length]; 
		for (int i = 0; i < cc.length; i++) {
			copyTo[i] = new InternetAddress(cc[i]);
		}
		msg.setRecipients(Message.RecipientType.CC, copyTo);

//		// set the 'bcc' address(s)
//		InternetAddress[] blindCopyTo = new InternetAddress[1]; 
//		blindCopyTo[0] = new InternetAddress("Picha.Andrew@mayo.edu");
//		msg.setRecipients(Message.RecipientType.BCC, blindCopyTo);

		// Set the subject
		msg.setSubject(subject);
		
	    // create and fill the first message part
	    MimeBodyPart mbp1 = new MimeBodyPart();
	    mbp1.setDataHandler( new DataHandler( new ByteArrayDataSource(content, "text/html") ) );
	    mbp1.setContent(content, "text/html");


	    // create the Multipart and add its parts to it
	    Multipart mp = new MimeMultipart();
	    mp.addBodyPart(mbp1);

	    // add the Multipart to the message
	    msg.setContent(mp);

	    // set the Date: header
	    msg.setSentDate(new Date());

		// finally, send the message!
		Transport.send(msg);
			
	}
	
	
	
	public static void mailWithAttachment(String subject, String content, String[] recipients,  String from) throws MessagingException, IOException {
	

		
		boolean debug = false;

		// create some properties and get the default Session
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtprelay.mayo.edu");
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(debug);

		// create a message
	    MimeMessage msg = new MimeMessage(session);

		// set the 'from' address
		try {
			if( from != null )
				msg.setFrom(new InternetAddress(from));
		}catch(Exception e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			logger.warn("'from' email not valid!  Continue on...");
		}

		// set the 'to' address(s)
		InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
		for (int i = 0; i < recipients.length; i++) {
			addressTo[i] = new InternetAddress(recipients[i]);
		}
		msg.setRecipients(Message.RecipientType.TO, addressTo);




		// Set the subject
		msg.setSubject(subject);
		
	    // create and fill the first message part
	    MimeBodyPart mbp1 = new MimeBodyPart();
	    DataSource source =  new FileDataSource("metricReport.csv");
	    mbp1.setDataHandler( new DataHandler( source) ) ;
	    mbp1.setFileName("metricReport.csv");
	    //mbp1.setContent(content, "text/html");

	    // create the Multipart and add its parts to it
	    Multipart mp = new MimeMultipart();
	    mp.addBodyPart(mbp1);
	    // add the Multipart to the message
	    msg.setContent(mp);
	    // set the Date: header
	    msg.setSentDate(new Date());
		// finally, send the message!
		Transport.send(msg);
			
	}
	
}
