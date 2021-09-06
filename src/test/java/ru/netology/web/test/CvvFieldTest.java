package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class CvvFieldTest {
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
    @DisplayName("Should return Error notification when use debet card with empty cvv field")
    void shouldReturnErrorNotificationWhenUseDebetCardWithEmptyCvvField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardCvvError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use debet card with cvv less then 3 numbers")
    void shouldReturnErrorNotificationWhenUseDebetCardWithEmptyCvvLessThen3Numbers(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateIncorrectCardCvv());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardCvvError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should not accept letters in month field when use debet card")
    void shouldNotAcceptLettersInMonthFieldWhenUseDebetCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateLetter());
        String actual = debetCardPage.getCardMonth().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return Error notification when use credit with empty cvv field")
    void shouldReturnErrorNotificationWhenUseCreditWithEmptyCvvField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getContinueButton().click();
        creditPage.getCardCvvError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit with cvv less then 3 numbers")
    void shouldReturnErrorNotificationWhenUseCreditWithEmptyCvvLessThen3Numbers(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateIncorrectCardCvv());
        creditPage.getContinueButton().click();
        creditPage.getCardCvvError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should not accept letters in month field when use credit")
    void shouldNotAcceptLettersInMonthFieldWhenUseCredit(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardCvv().sendKeys(DataHelper.generateLetter());
        String actual = creditPage.getCardCvv().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }

}
