package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class YearFieldTest {

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
    @DisplayName("Should return Error notification when use debet card with empty year field")
    void shouldReturnErrorNotificationWhenUseDebetCardWithEmptyYearField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardYearError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use  expired debet card")
    void shouldReturnErrorNotificationWhenUseExpiredDebetCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys("20");
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardYearValidityError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit with empty year field")
    void shouldReturnErrorNotificationWhenUseCreditWithEmptyYearField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardYearError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use expired debet card in credit")
    void shouldReturnErrorNotificationWhenUseExpiredDebetCardInCredit(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys("20");
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateFullName("ru"));
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardYearValidityError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should not accept letters in year field when use debet card")
    void shouldNotAcceptLettersInYearFieldWhenUseDebetCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardYear().sendKeys(DataHelper.generateLetter());
        String actual = debetCardPage.getCardYear().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should not accept letters in year field when use credit")
    void shouldNotAcceptLettersInMonthFieldWhenUseCredit(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardYear().sendKeys(DataHelper.generateLetter());
        String actual = creditPage.getCardYear().getValue();
        String expected = "";
        Assertions.assertEquals(expected, actual);
    }
}
