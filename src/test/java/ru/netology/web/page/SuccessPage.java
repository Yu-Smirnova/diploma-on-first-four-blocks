package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SuccessPage {
    private SelenideElement successNotificationTitle = $(byText("Успешно"));
    private SelenideElement successNotification = $(byText("Операция одобрена Банком."));

    public SuccessPage(){
        successNotificationTitle.shouldBe(Condition.visible, Duration.ofSeconds(15));
        successNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
