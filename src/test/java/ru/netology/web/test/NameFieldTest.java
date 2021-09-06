package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class NameFieldTest {
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
    @DisplayName("Should return Error notification when use debet card with empty name field")
    void shouldReturnErrorNotificationWhenUseDebetCardWithEmptyNameField(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardOwnerFullNameEmptyError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should not accept numbers in name field when use debet card")
    void shouldNotAcceptNumberInNameFieldWhenUseDebetCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        debetCardPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        debetCardPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        debetCardPage.getCardOwnerFullName().sendKeys(DataHelper.generateIncorrectCardCvv());
        debetCardPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardOwnerFullNameError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error notification when use credit with empty name field")
    void shouldReturnErrorNotificationWhenUseCreditWithEmptyNameField(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardOwnerFullNameEmptyError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should not accept numbers in name field when use credit")
    void shouldNotAcceptNumberInNameFieldWhenUseCredit(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseDebetCard();
        creditPage.getCardNumber().sendKeys(DataHelper.getActiveCardInfo().getNumber());
        creditPage.getCardMonth().sendKeys(DataHelper.generateMonthForCard());
        creditPage.getCardYear().sendKeys(DataHelper.generateYearForCard());
        creditPage.getCardOwnerFullName().sendKeys(DataHelper.generateIncorrectCardCvv());
        creditPage.getCardCvv().sendKeys(DataHelper.generateCvvCode());
        creditPage.getContinueButton().click();
        creditPage.getCardOwnerFullNameError().shouldBe(Condition.visible);
    }
}
