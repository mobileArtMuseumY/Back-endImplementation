package test.com.artmall.email; 

import com.artmall.email.EmailService;
import com.artmall.email.EmailServiceImpl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* EmailServiceImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮһ�� 9, 2018</pre> 
* @version 1.0 
*/ 
public class EmailServiceImplTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: registerEmail(RegisterDto business, String token) 
* 
*/ 
@Test
public void testRegisterEmail() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: userValidate(Long id, String token) 
* 
*/ 
@Test
public void testUserValidate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: sendResetEmail(Business user, String code) 
* 
*/ 
@Test
public void testSendResetEmailForUserCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: codeVerify(Business business, String code) 
* 
*/ 
@Test
public void testCodeVerifyForBusinessCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: codeVerify(Student student, String code) 
* 
*/ 
@Test
public void testCodeVerifyForStudentCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: payFinish(OrderForm order) 
* 
*/ 
@Test
public void testPayFinish() throws Exception { 
//TODO: Test goes here...
} 


/** 
* 
* Method: sendEmail(String to, String subject, String text) 
* 
*/ 
@Test
public void testSendEmail() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = EmailServiceImpl.getClass().getMethod("sendEmail", String.class, String.class, String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
