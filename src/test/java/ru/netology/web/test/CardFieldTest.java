package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class CardFieldTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Should return Error notification when use debet card with empty card field")
    void shouldReturnErrorNotificationWhenUseDebetCardWithEmptyCardField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardNumberError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use debet card when card number less then 16 numbers")
    void shouldReturnErrorNotificationWhenUseDebetCardWhenCardNumberLessThen16Numbers(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.generateIncorrectCardNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardNumberError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should left empty card field when enter letters when uer debet card")
    void shouldLeftCardFieldEmptyWhenEnterLettersWhenUseDebetCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.generateLetter());
        String actual = debetCardPage.getCardNumber().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return Error notification when use credit with empty card field")
    void shouldReturnErrorNotificationWhenUseCreditWithEmptyCardField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardNumberError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit when card number less then 16 numbers")
    void shouldReturnErrorNotificationWhenUseCreditWhenCardNumberLessThen16Numbers(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.generateIncorrectCardNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardNumberError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should left empty card field when enter letters when uer credit")
    void shouldLeftCardFieldEmptyWhenEnterLettersWhenUseCredit(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.generateLetter());
        String actual = creditPage.getCardNumber().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }

}
