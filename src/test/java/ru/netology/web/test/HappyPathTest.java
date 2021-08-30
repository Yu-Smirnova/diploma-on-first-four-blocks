package ru.netology.web.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.data.SqlHelper;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class HappyPathTest {
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
    @DisplayName("Should successful buy using approved debet card")
    void shouldSuccessfulBuyUsingDebetCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.successBuyWithDebetCard();
        String expectedStatus = "APPROVED";
        String actualStatus = SqlHelper.getPaymentStatus(SqlHelper.getPaymentId());
        Assertions.assertEquals(expectedStatus, actualStatus);
        String expected = SqlHelper.getTransactionId();
        String actual = SqlHelper.getPaymentId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should successful buy with credit using approved debet card")
    void shouldSuccessfulBuyWithCreditUsingActiveDebetCard(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.successBuyWithCredit();
        String expectedStatus = "APPROVED";
        String actualStatus = SqlHelper.geCreditStatus(SqlHelper.getCreditBankId());
        Assertions.assertEquals(expectedStatus, actualStatus);
        String expected = SqlHelper.getCreditBankId();
        String actual = SqlHelper.getCreditId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return Error when buy using declined card")
    void shouldReturnErrorWhenBuyUsingDeclinedCard(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.errorBuyWithDebetCard();
        String expectedStatus = "DECLINED";
        String actualStatus = SqlHelper.getPaymentStatus(SqlHelper.getPaymentId());
        Assertions.assertEquals(expectedStatus, actualStatus);
        String expected = SqlHelper.getTransactionId();
        String actual = SqlHelper.getPaymentId();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Should return Error when buy with credit using declined card")
    void shouldReturnErrorWhenBuyWithCreditUsingDeclinedCard(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.errorBuyWithCredit();
        String expectedStatus = "DECLINED";
        String actualStatus = SqlHelper.geCreditStatus(SqlHelper.getCreditBankId());
        Assertions.assertEquals(expectedStatus, actualStatus);
        String expected = SqlHelper.getCreditBankId();
        String actual = SqlHelper.getCreditId();
        Assertions.assertEquals(expected, actual);
    }
}
