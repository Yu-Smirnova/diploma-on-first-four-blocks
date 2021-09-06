package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.Value;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

@Value
public class DebetCardPage {
    private SelenideElement pageName = $(byText("Оплата по карте"));
    private SelenideElement cardNumber = $x("//span[text()='Номер карты']/following::span[1]/input");
    private SelenideElement cardMonth = $x("//span[text()='Месяц']/following::span[1]/input");
    private SelenideElement cardYear = $x("//span[text()='Год']/following::span[1]/input");
    private SelenideElement cardOwnerFullName = $x("//span[text()='Владелец']/following::span[1]/input");
    private SelenideElement cardCvv = $x("//span[text()='CVC/CVV']/following::span[1]/input");
    private SelenideElement continueButton =$(byText("Продолжить"));
    private SelenideElement cardNumberError = $x("//span[text()='Номер карты']/following::span[text()='Неверный формат']");
    private SelenideElement cardMonthValidityError = $x("//span[text()='Месяц']/following::span[text()='Неверно указан срок действия карты']");
    private SelenideElement cardMonthError = $x("//span[text()='Месяц']/following::span[text()='Неверный формат']");
    private SelenideElement cardYearValidityError = $x("//span[text()='Год']/following::span[text()='Истёк срок действия карты']");
    private SelenideElement cardYearError = $x("//span[text()='Год']/following::span[text()='Неверный формат']");
    private SelenideElement cardOwnerFullNameEmptyError = $x("//span[text()='Владелец']/following::span[text()='Поле обязательно для заполнения']");
    private SelenideElement cardOwnerFullNameError = $x("//span[text()='Владелец']/following::span[text()='Неверный формат']");
    private SelenideElement cardCvvError = $x("//span[text()='CVC/CVV']/following::span[text()='Неверный формат']");

    public DebetCardPage(){
        pageName.shouldBe(Condition.visible);
    }

    public SuccessPage successBuyWithDebetCard(){
        cardNumber.sendKeys(DataHelper.getActiveCardInfo().getNumber());
        cardMonth.sendKeys(DataHelper.generateMonthForCard());
        cardYear.sendKeys(DataHelper.generateYearForCard());
        cardOwnerFullName.sendKeys(DataHelper.generateFullName("ru"));
        cardCvv.sendKeys(DataHelper.generateCvvCode());
        continueButton.click();
        return new SuccessPage();
    }

    public ErrorPage errorBuyWithDebetCard(){
        cardNumber.sendKeys(DataHelper.getDeclinedCardInfo().getNumber());
        cardMonth.sendKeys(DataHelper.generateMonthForCard());
        cardYear.sendKeys(DataHelper.generateYearForCard());
        cardOwnerFullName.sendKeys(DataHelper.generateFullName("ru"));
        cardCvv.sendKeys(DataHelper.generateCvvCode());
        continueButton.click();
        return new ErrorPage();
    }

}
