package org.swz.com.family.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;

import org.javasimon.aop.Monitored;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.swz.com.family.common.constants.Constants;
import org.swz.com.family.common.constants.StaticCodeType;
import org.swz.com.family.common.exception.BusinessException;
import org.swz.com.family.common.mail.MailSenderInfo;
import org.swz.com.family.common.mail.SimpleMailSender;
import org.swz.com.family.common.util.Sampler;
import org.swz.com.family.common.util.Send;
import org.swz.com.family.entity.User;
import org.swz.com.family.repository.mybatis.UserDao;
@Component
@Monitored
public class UserService {
	
	@Autowired
	private UserDao userDao;
	 
	public void saveUser(User user) throws BusinessException{ 
			user.setConfirmCode(getConfirmCode()); 
			user.setStatus(1); 
			userDao.save(user);  
			//调用接口发送验证码 todo
		
		if(user.getEmail() != null){
			//发送确认邮件
			 MailSenderInfo mailInfo = new MailSenderInfo();    
		     mailInfo.setMailServerHost("smtp.163.com");    
		     mailInfo.setMailServerPort("25");    
		     mailInfo.setValidate(true);    
		     mailInfo.setUserName("llaysz@163.com");    
		     mailInfo.setPassword("1303285l0");//您的邮箱密码    
		     mailInfo.setFromAddress("llaysz@163.com");    
		     mailInfo.setToAddress(user.getEmail());    
		     mailInfo.setSubject("请通过你的验证，和我们一起完成您的家谱的创建");    
		     mailInfo.setContent("请点击以下连接来确认你的注册<br>" + "http://watchmen.org.cn:8080/family/user/confirm?a=" + user.getId() + "&b=1&k=" + UUID.randomUUID().toString() + "&c=" + user.getConfirmCode());    
		        //这个类主要来发送邮件   
		     SimpleMailSender sms = new SimpleMailSender();   
	         sms.sendTextMail(mailInfo );//发送文体格式    
		}else{
			
		}
	} 
	
	public User getUserByName(String userName){
		 Map<String, Object> pm = new HashMap<String, Object>();
		 pm.put("username", userName);
		 User user = userDao.getUserByParam(pm); 
		 return user;
	}
	
	public User getUserByUserId(String userId){
		 Map<String, Object> pm = new HashMap<String, Object>();
		 pm.put("userId", userId);
		 User user = userDao.getUserByParam(pm); 
		 return user;
	}
	
	public User sendConfirmCode(String userName) throws BusinessException, UnsupportedEncodingException {
		// TODO Auto-generated method stub 
		User confirmUser = getUserByName(userName); 
		if(confirmUser != null){
			if(confirmUser.getStatus() == 1){ 
				if(confirmUser.getModifyTime().getTime() + 5 * 60 * 1000  < new Date().getTime()){ 
					confirmUser.setConfirmCode(getConfirmCode()); 
					userDao.updateUserConfirmCode(confirmUser);  
					//调用接口发送验证码 todo 
					sendMM(confirmUser.getConfirmCode(), confirmUser.getUserName());
					//throw new BusinessException(StaticCodeType.USER_CREATE_CONFIRMOUTOFDATE.getCode(), StaticCodeType.USER_CREATE_CONFIRMOUTOFDATE.getDescription());
				}else{
					//调用接口发送当前验证码 todo 
					sendMM(confirmUser.getConfirmCode(), confirmUser.getUserName());
//					throw new BusinessException(StaticCodeType.USER_CREATE_CONFIRMCREATEDCODEINDATE.getCode(), StaticCodeType.USER_CREATE_CONFIRMCREATEDCODEINDATE.getDescription());
				}
			}else{
				throw new BusinessException(StaticCodeType.USER_CREATE_ISCREATED.getCode(), StaticCodeType.USER_CREATE_ISCREATED.getDescription());
			} 
		}else{ 
			confirmUser = new User();
			confirmUser.setId(UUID.randomUUID().toString());
			confirmUser.setUserName(userName);
			confirmUser.setConfirmCode(getConfirmCode()); 
			confirmUser.setStatus(1); 
			userDao.save(confirmUser);  
			//调用接口发送验证码 todo
			sendMM(confirmUser.getConfirmCode(), confirmUser.getUserName());
		} 
		//防止confirmcode被用户抓取  测试打开
//		confirmUser.setConfirmCode("");
		return confirmUser;
	} 
	
	private String getConfirmCode(){
		String confirmCode = "";
		Random rd = new Random();
		while(confirmCode.length() < 6){
			confirmCode += rd.nextInt(10);
		} 
		return confirmCode;
	}
	
	 
	private void sendMM(String confirmCode, String phone) throws UnsupportedEncodingException{
		ResourceBundle rb = ResourceBundle.getBundle(Constants.SYS_CONFIG_FILE);
//		String PostData = "account=" + rb.getString("mm_user") +"&password=" + rb.getString("mm_password")  + "&mobile=" + phone + "&content="+ java.net.URLEncoder.encode(confirmCode + "是您的验证码，请你在5分钟内完成注册，如果超过5分钟请重新申请验证码。 X家谱网欢饮您的加入，感谢你和我们一起创建和谐大家园" ,"utf-8");
		try {
			Sampler.sendOnce(phone, confirmCode + "是您的验证码，请你在5分钟内完成注册，如果超过5分钟请重新申请验证码。 X家谱网欢饮您的加入，感谢你和我们一起创建和谐大家园");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//发送短信
		System.out.println("发送短信:" + confirmCode);
//		Send.SMS(PostData, rb.getString("mm_post_url"));
	}

	public boolean confirmUser(String a, String c) {
		// TODO Auto-generated method stub
		 Map<String, Object> pm = new HashMap<String, Object>();
		 pm.put("userId", a);
		 pm.put("status", "1");
		 User user = userDao.getUserByParam(pm); 
		 if(user == null){
			 return false;
		 }
		 //验证成功
		 if(user.getConfirmCode().equals(c) ){
			 user.setStatus(3);
			 userDao.updateUserStatus(user); 
		 } 
		 
		 return true;
	}

	public boolean validateConfirmCode(String userName, String confirmCode) throws BusinessException {
		User confirmUser = getUserByName(userName); 
		if(confirmUser != null){
			if(confirmUser.getStatus() == 1){ 
				if(confirmUser.getModifyTime().getTime() + 5 * 60 * 1000  < new Date().getTime()){  
					throw new BusinessException(StaticCodeType.USER_CREATE_CONFIRMOUTOFDATE.getCode(), StaticCodeType.USER_CREATE_CONFIRMOUTOFDATE.getDescription());
				}else{
					//调用接口发送当前验证码 todo 
					if(!confirmCode.equals(confirmUser.getConfirmCode())){
						throw new BusinessException(StaticCodeType.USER_CREATE_CONFIRMERROR.getCode(), StaticCodeType.USER_CREATE_CONFIRMERROR.getDescription());
					}
				}
			}else{
				throw new BusinessException(StaticCodeType.USER_CREATE_ISCREATED.getCode(), StaticCodeType.USER_CREATE_ISCREATED.getDescription());
			} 
		}else{
			throw new BusinessException(StaticCodeType.USER_CREATE_NOUSER.getCode(), StaticCodeType.USER_CREATE_NOUSER.getDescription());
		}
		return true;
	}

	public void setPassword(User user) {
		// TODO Auto-generated method stub 
		userDao.setPassword(user);
		
	}
	
	public boolean validateConfirmCodeForFindPassword(String userName, String confirmCode) throws BusinessException {
		User confirmUser = getUserByName(userName); 
		if(confirmUser != null){
			if(!confirmCode.equals(confirmUser.getConfirmCode())){
				throw new BusinessException(StaticCodeType.USER_CREATE_CONFIRMERROR.getCode(), StaticCodeType.USER_CREATE_CONFIRMERROR.getDescription());
			}
		} 
		return true;
	}
		

	public User sendFindPasswordConfirmRequest(String userName) throws BusinessException {
		// TODO Auto-generated method stub 
		User confirmUser = getUserByName(userName); 
		if(confirmUser != null){ 
			if(confirmUser.getModifyTime().getTime() + 5 * 60 * 1000  < new Date().getTime()){ 
				confirmUser.setConfirmCode(getConfirmCode()); 
				userDao.updateUserConfirmCode(confirmUser);  
				//调用接口发送验证码 todo 
//				sendMM(confirmUser.getConfirmCode(), confirmUser.getUserName());
				//throw new BusinessException(StaticCodeType.USER_CREATE_CONFIRMOUTOFDATE.getCode(), StaticCodeType.USER_CREATE_CONFIRMOUTOFDATE.getDescription());
			} 
		}else{ 
			throw new BusinessException(StaticCodeType.USER_CREATE_NOUSER.getCode(), StaticCodeType.USER_CREATE_NOUSER.getDescription());
		} 
		//防止confirmcode被用户抓取  测试打开
//		confirmUser.setConfirmCode("");
		return confirmUser;
	}  
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		ResourceBundle rb = ResourceBundle.getBundle(Constants.SYS_CONFIG_FILE);
		String PostData = "account=" + rb.getString("mm_user") +"&password=" + rb.getString("mm_password")  + "&mobile=" + "13880529348" + "&content="+ java.net.URLEncoder.encode("123456" + "是您的验证码，请你在5分钟内完成注册" ,"utf-8");
		//发送短信
		System.out.println("发送短信:" + PostData);
		Send.SMS(PostData, rb.getString("mm_post_url"));
//		String postData = "account=llabbhmm&password=1303285l0&mobile=13880529348&content=524576%E6%98%AF%E6%82%A8%E7%9A%84%E9%AA%8C%E8%AF%81%E7%A0%81%EF%BC%8C%E8%AF%B7%E4%BD%A0%E5%9C%A85%E5%88%86%E9%92%9F%E5%86%85%E5%AE%8C%E6%88%90%E6%B3%A8%E5%86%8C%EF%BC%8C%E5%A6%82%E6%9E%9C%E8%B6%85%E8%BF%875%E5%88%86%E9%92%9F%E8%AF%B7%E9%87%8D%E6%96%B0%E7%94%B3%E8%AF%B7%E9%AA%8C%E8%AF%81%E7%A0%81%E3%80%82+X%E5%AE%B6%E8%B0%B1%E7%BD%91%E6%AC%A2%E9%A5%AE%E6%82%A8%E7%9A%84%E5%8A%A0%E5%85%A5%EF%BC%8C%E6%84%9F%E8%B0%A2%E4%BD%A0%E5%92%8C%E6%88%91%E4%BB%AC%E4%B8%80%E8%B5%B7%E5%88%9B%E5%BB%BA%E5%92%8C%E8%B0%90%E5%A4%A7%E5%AE%B6%E5%9B%AD";
//	
//		Send.SMS(postData, "http://sms.106jiekou.com/utf8/sms.aspx");
	}
	
}
