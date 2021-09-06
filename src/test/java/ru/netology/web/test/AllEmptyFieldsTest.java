package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.web.page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class AllEmptyFieldsTest {
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
    @DisplayName("Should return Error when usedebet card with all empty fields")
    void shouldReturnErrorWhenUseDebetCardWithAllEmptyFields(){
        var mainPage = new MainPage();
        var debetCardPage = mainPage.chooseDebetCard();
        debetCardPage.getContinueButton().click();
        debetCardPage.getCardNumberError().shouldBe(Condition.visible);
        debetCardPage.getCardMonthError().shouldBe(Condition.visible);
        debetCardPage.getCardYearError().shouldBe(Condition.visible);
        debetCardPage.getCardOwnerFullNameEmptyError().shouldBe(Condition.visible);
        debetCardPage.getCardCvvError().shouldBe(Condition.visible);
    }

    @Test
    @DisplayName("Should return Error when use credit with all empty fields")
    void shouldReturnErrorWhenTUseCreditWithAllEmptyFields(){
        var mainPage = new MainPage();
        var creditPage = mainPage.chooseCredit();
        creditPage.getContinueButton().click();
        creditPage.getCardNumberError().shouldBe(Condition.visible);
        creditPage.getCardMonthError().shouldBe(Condition.visible);
        creditPage.getCardYear().shouldBe(Condition.visible);
        creditPage.getCardOwnerFullNameEmptyError().shouldBe(Condition.visible);
        creditPage.getCardCvvError().shouldBe(Condition.visible);
    }

    
}
