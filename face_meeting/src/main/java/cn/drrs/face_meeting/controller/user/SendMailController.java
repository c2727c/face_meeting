package cn.drrs.face_meeting.controller.user;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.drrs.face_meeting.util.MailUtil;


/**
 * created by viking on 2018/07/17
 * 测试邮件发送controller
 */
@RestController
@RequestMapping("mail")
public class SendMailController {

	@Autowired
    private JavaMailSender javaMailSender;//在spring中配置的邮件发送的bean
 
	@Autowired
	private MailUtil mailUtil;
	@RequestMapping("groupSend.do")
	public Object groupSend() {
		System.out.println("groupSend.do");
		List<String> qqList = new ArrayList<String>();
		qqList.add("2319277867@qq.com");
		qqList.add("1659010991@qq.com");
		mailUtil.send(qqList, "Spring的邮件发送类，剩下的步骤都是在这个类实现的。代码中的SimpleMail是封装了邮件主题和内容的一个POJO。觉得在一个方法参数中既包含主题又包含内容，不太合适，故重载了此方法。还有就是因为大多数邮箱的SMTP服务器地址都是可以通过邮箱地址算出来，简单起见，提供了一个不需要SMTP服务器地址的构造器。发送", "<p>这是使用邮件发送类，剩下的步骤都是在这个类实现的。代码中的SimpleMail是封装了邮件主题和内容的一个POJO。觉得在一个方法参数中既包含主题又包含内容，不太合适，故重载了此方法。还有就是因为大多数邮箱的SMTP服务器地址都是可以通过邮箱地址算出来，简单起见，提供了一个不需要SMTP服务器地址的构造器。的邮件功能发送的一封邮件</p>");
		return "发送成功";
	}
    @RequestMapping("send.do")
    public Object sendMail03(){
        MimeMessage mMessage=javaMailSender.createMimeMessage();//创建邮件对象
        MimeMessageHelper mMessageHelper;
        Properties prop = new Properties();
        String from;
        try {
            //从配置文件中拿到发件人邮箱地址
            prop.load(this.getClass().getResourceAsStream("/config/mail.properties"));
            from = prop.get("mail.smtp.username")+"";
            mMessageHelper=new MimeMessageHelper(mMessage,true);
            mMessageHelper.setFrom(from);//发件人邮箱
            mMessageHelper.setTo("2319277867@qq.com");//收件人邮箱
            mMessageHelper.setSubject("Spring的邮件发送");//邮件的主题
            mMessageHelper.setText("<p>这是使用spring的邮件功能发送的一封邮件</p><br/>" +
                    "<a href='https://www.baidu.com>打开我的博客主页</a><br/>" +
                    "<img src='cid:fengye'>",true);//邮件的文本内容，true表示文本以html格式打开
            File file=new File("E:/review/1.png");//在邮件中添加一张图片
            FileSystemResource resource=new FileSystemResource(file);
            mMessageHelper.addInline("fengye", resource);//这里指定一个id,在上面引用
            mMessageHelper.addAttachment("枫叶.jpg", resource);//在邮件中添加一个附件
            javaMailSender.send(mMessage);//发送邮件
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "发送成功";
    }
}
