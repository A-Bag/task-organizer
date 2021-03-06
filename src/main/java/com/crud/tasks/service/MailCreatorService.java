package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.CompanyConfig;
import com.crud.tasks.domain.MailType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Arrays;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private CompanyConfig companyConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    private List<String> functionality = Arrays.asList(
            "You can manage your tasks",
            "Provides connection with Trello Account",
            "Application allows sending tasks to Trello");

    public String buildEmail(String message, MailType mailType) {
        switch(mailType) {
            case DAILY_INFO:
                return buildDailyInfoEmail(message);
            case NEW_TRELLO_CARD_INFO:
                return buildTrelloCardEmail(message);
            default:
                return message;
        }
    }

    private String buildTrelloCardEmail(String message) {

        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://a-bag.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("admin_config", adminConfig);
        context.setVariable("goodbye_message", "Best wishes, ");
        context.setVariable("company_info", companyConfig.getCompanyName() +
                "   |   " + companyConfig.getCompanyEmail() + "   |   " + companyConfig.getCompanyPhone());
        context.setVariable("show_button", false);
        context.setVariable("is_friend", false);
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    private String buildDailyInfoEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://a-bag.github.io/");
        context.setVariable("button", "Visit website");
        context.setVariable("show_button", true);
        context.setVariable("admin_config", adminConfig);
        context.setVariable("company_info", companyConfig);
        context.setVariable("is_friend", true);
        context.setVariable("is_morning", true);
        context.setVariable("goodbye_morning_message", "Have a nice day, ");
        context.setVariable("goodbye_message", "Best wishes, ");
        context.setVariable("application_functionality", functionality);
        return templateEngine.process("mail/daily-info-email", context);
    }
}
