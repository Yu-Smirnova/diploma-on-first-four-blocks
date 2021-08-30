package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ErrorPage {
    private SelenideElement errorNotificationTitle = $(byText("Ошибка"));
    private SelenideElement errorNotification = $(byText("Ошибка! Банк отказал в проведении операции."));

    public ErrorPage(){
        errorNotificationTitle.shouldBe(Condition.visible, Duration.ofSeconds(15));
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
