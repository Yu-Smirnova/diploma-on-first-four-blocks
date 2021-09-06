package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class MonthFieldTest {
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
    @DisplayName("Should return Error notification when use debet card with empty month field")
    void shouldReturnErrorNotificationWhenUseDebetCardWithEmptyMonthField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardMonthError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use debet card when enter 00 in month field")
    void shouldReturnErrorNotificationWhenUseDebetCardWhenEnter00InMonthField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys("00");
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardMonthValidityError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use debet card when enter 13 in month field")
    void shouldReturnErrorNotificationWhenUseDebetCardWhenEnter13InMonthField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys("13");
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardMonthValidityError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit with empty month field")
    void shouldReturnErrorNotificationWhenUseCreditWithEmptyMonthField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardMonthError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit when enter 00 in month field")
    void shouldReturnErrorNotificationWhenUseCreditWhenEnter00InMonthField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys("00");
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardMonthValidityError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit when enter 13 in month field")
    void shouldReturnErrorNotificationWhenUseCreditWhenEnter13InMonthField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys("13");
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardMonthValidityError().shouldBe(Condition.visible);
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
    @DisplayName("Should not accept letters in month field when use credit")
    void shouldNotAcceptLettersInMonthFieldWhenUseCredit(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardMonth().sendKeys(DataHelper.generateLetter());
        String actual = creditPage.getCardMonth().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }
}
