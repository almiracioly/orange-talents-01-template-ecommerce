package br.com.zup.ecommerce.shared.builder;

import br.com.zup.ecommerce.shared.service.mail.MailMessage;

public class MailMessageBuilder implements MailMessage {

    private String title;
    private String content;
    private String destination;

    public MailMessageBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public MailMessageBuilder withContent(String content) {
        this.content = content;
        return this;
    }

    public MailMessageBuilder to(String destination) {
        this.destination = destination;
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getDestination() {
        return destination;
    }
}
