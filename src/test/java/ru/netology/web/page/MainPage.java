package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement debetCardButton = $(byText("Купить"));
    private SelenideElement creditButton = $(".button_view_extra");

    public DebetCardPage chooseDebetCard(){
        debetCardButton.click();
        return new DebetCardPage();
    }

    public CreditPage chooseCredit(){
        creditButton.click();
        return new CreditPage();
    }
}
