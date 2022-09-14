package ru.netology.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SqlHelper;
import ru.netology.page.CreditPage;
import ru.netology.page.DashboardPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardPaymentTest {
    @BeforeEach
    void setUp() {
        Configuration.startMaximized = true;
        open("http://localhost:8080");
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SqlHelper.deleteTables();
    }

    @Test
    void shouldPaymentByCreditCardWithStatusApproved() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkSuccessCreditNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPaymentByCreditCardWithStatusDeclined() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getDeclinedCardNumber();
        val month = DataHelper.getMonth(1);
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkErrorCreditNotification();
        val paymentStatus = SqlHelper.getStatusCreditRequestEntity();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPaymentByCreditCardWithRussianOwner() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getRussianOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkErrorCreditNotification();
    }

    @Test
    void shouldPaymentByCreditCardWithChineseOwner() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getChineseOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkErrorCreditNotification();
    }

    @Test
    void shouldPaymentByCreditCardWithInvalidCardNumber() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getInvalidCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(1);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkErrorCreditNotification();
    }

    @Test
    void shouldPaymentByCreditCardWithIncorrectCardNumber() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getIncorrectCardNumber();
        val month = DataHelper.getMonth(2);
        val year = DataHelper.getYear(1);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkWrongCreditFormat();
    }

    @Test
    void shouldPaymentByCreditCardWithInvalidMonth() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getInvalidMonth();
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkInvalidCreditCardExpirationDate();
    }

    @Test
    void shouldPaymentByCreditCardWithExpiredOneYear() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(-1);
        val year = DataHelper.getYear(-1);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.verifyCreditCardExpired();
    }

    @Test
    void shouldPaymentByCreditCardWithTheWrongYear() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(6);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkInvalidCreditCardExpirationDate();
    }

    @Test
    void shouldPaymentByCreditCardWithInvalidOwner() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(3);
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getInvalidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkWrongCreditFormat();
    }

    @Test
    void shouldPaymentByCreditCardWithIncorrectCVC() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(-3);
        val year = DataHelper.getYear(0);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getIncorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkWrongCreditFormat();
    }

    @Test
    void shouldPaymentByCreditCardWithBlankFields() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getCardWithoutNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getEmptyYear();
        val owner = DataHelper.getEmptyOwner();
        val cvc = DataHelper.getEmptyCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year, owner, cvc);
        creditPage.checkWrongCreditFormat();
        creditPage.verifyCreditEmptyField();
    }

    @Test
    void shouldPaymentByCreditCardWithEmptyYear() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(6);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year,owner, cvc);
        creditPage.checkInvalidCreditCardExpirationDate();
    }

    @Test
    void shouldPaymentByCreditCardWithEmptyMonth() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getEmptyMonth();
        val year = DataHelper.getYear(6);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getCorrectCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year,owner, cvc);
       creditPage.checkWrongCreditFormat();
    }

    @Test
    void shouldPaymentByCreditCardWithEmptyCvc() {
        val dashboardPage = new DashboardPage();
        val creditPage = dashboardPage.getPaymentByCreditCard();
        val cardNumber = DataHelper.getApprovedCardNumber();
        val month = DataHelper.getMonth(0);
        val year = DataHelper.getYear(6);
        val owner = DataHelper.getValidOwner();
        val cvc = DataHelper.getEmptyCVC();
        creditPage.fillCreditPaymentFormat(cardNumber, month, year,owner, cvc);
        creditPage.checkErrorCreditNotification();
    }
}

