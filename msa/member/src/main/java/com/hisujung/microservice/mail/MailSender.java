package com.hisujung.microservice.mail;


import com.hisujung.microservice.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Random;

@Slf4j
@PropertySource("classpath:application.properties")
@RequiredArgsConstructor
@Service
public class MailSender {

    private final Environment env;
    private final RedisUtil redisUtil;
    //인증번호 생성
    private String ePw = createKey();
    //private final JavaMailSender javaMailSender;

    public String send(String to){
        ePw = createKey();
        log.info("Sender send() start ...");
        String host = env.getProperty("aws.ses.host");
        String port = env.getProperty("aws.ses.port");
        String userName = env.getProperty("aws.ses.access-id");
        String password = env.getProperty("aws.ses.secret-key");
        String from = env.getProperty("aws.ses.from");

        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", Integer.parseInt(port));
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);


        //메일 내용 메일의 subtype을 html로 지정하여 html 문법 사용 가능
        String msgt="";
        msgt += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msgt += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요. 유효시간은 1분입니다.</p>";
        msgt += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msgt += ePw;
        msgt += "</td></tr></tbody></table></div>";



        //MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            msg.setSubject("하이수정이 회원가입 인증 코드");
            msg.setText(msgt, StandardCharsets.UTF_8.name(), "html");
            msg.setFrom(new InternetAddress(from,"하이수정이")); //보내는 사람의 메일 주소, 보내는 사람 이름
            //메일 전송
            log.info("mail send start ...");
            Transport transport = session.getTransport();
            transport.connect(host, userName, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            log.info("mail send complete ...");

            // 유효시간 1분
            redisUtil.setDataExpire(ePw, to, 60 * 1L);
        } catch (UnsupportedEncodingException e) {
            // InternetAddress 만들 때 Exception
            e.printStackTrace();
            //return false;
        } catch (MessagingException e) {
            // setForm 할 때 Exception
            e.printStackTrace();
            //return ePw;
        }
        return ePw;
    }

    //인증코드 만들기
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { //인증코드 6자리
            key.append((rnd.nextInt(10)));
        }
        return key.toString();
    }

    //이메일 발송 코드 인증
    public String verifyEmail(String key) throws ChangeSetPersister.NotFoundException {
        String memberEmail = redisUtil.getData(key);
        if (memberEmail == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        redisUtil.deleteData(key);
        return ePw;
    }

}
