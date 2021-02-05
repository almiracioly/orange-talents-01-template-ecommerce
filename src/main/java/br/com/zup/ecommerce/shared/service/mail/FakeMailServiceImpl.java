package br.com.zup.ecommerce.shared.service.mail;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class FakeMailServiceImpl implements MailService{
    @Override
    public void send(MailMessage mailMessage) {
        System.out.println(
                    "Para: " + mailMessage.getDestination()
                    + " \nEmail: " + mailMessage.getTitle()
                    + " \n" + mailMessage.getContent());
    }
}
