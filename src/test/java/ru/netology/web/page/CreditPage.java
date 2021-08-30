package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CreditPage {
    private SelenideElement pageName = $(byText("Кредит по данным карты"));
    private SelenideElement cardNumber = $x("//span[text()='Номер карты']/following::span[1]/input");
    private SelenideElement cardMonth = $x("//span[text()='Месяц']/following::span[1]/input");
    private SelenideElement cardYear = $x("//span[text()='Год']/following::span[1]/input");
    private SelenideElement cardOwnerFullName = $x("//span[text()='Владелец']/following::span[1]/input");
    private SelenideElement cardCvv = $x("//span[text()='CVC/CVV']/following::span[1]/input");
    private SelenideElement continueButton =$(byText("Продолжить"));

    public CreditPage(){
        pageName.shouldBe(Condition.visible);
    }

    public SuccessPage successBuyWithCredit(){
        cardNumber.sendKeys(DataHelper.getActiveCardInfo().getNumber());
        cardMonth.sendKeys(DataHelper.generateMonthForCard());
        cardYear.sendKeys(DataHelper.generateYearForCard());
        cardOwnerFullName.sendKeys(DataHelper.generateFullName("ru"));
        cardCvv.sendKeys(DataHelper.generateCvvCode());
        continueButton.click();
        return new SuccessPage();
    }

    public ErrorPage errorBuyWithCredit(){
        cardNumber.sendKeys(DataHelper.getDeclinedCardInfo().getNumber());
        cardMonth.sendKeys(DataHelper.generateMonthForCard());
        cardYear.sendKeys(DataHelper.generateYearForCard());
        cardOwnerFullName.sendKeys(DataHelper.generateFullName("ru"));
        cardCvv.sendKeys(DataHelper.generateCvvCode());
        continueButton.click();
        return new ErrorPage();
    }
}
