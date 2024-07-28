package org.demoqa.hwselenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import java.time.Duration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.stream.Stream;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ElementsTests {

  protected static final Logger LOGGER = LogManager.getLogger();

  @BeforeEach
  void setUp() {
    Configuration.pageLoadStrategy = "eager";
    open("https://demoqa.com/elements");
    getWebDriver().manage().window().maximize();
  }

  @Test
  @DisplayName("Успешное нажатие элемента radio button")
  void successfulRadioBtnClick() {
    $("#item-2").click();
    executeJavaScript("arguments[0].click();", $("#yesRadio"));
    $("span.text-success").shouldHave(text("Yes"));
    LOGGER.info("Radio button successfully verified");
  }

  static Stream<String[]> provideData() {
    return Stream.of(
        new String[]{"Strelkov Stas", "test@mail.ru", "new street, 111, 123"},
        new String[]{"Nikita", "test2@m.com", ""}
    );
  }

  @ParameterizedTest
  @MethodSource("provideData")
  @DisplayName("Заполнение текстовых полей")
  void fillingTextBox(String inputName, String inputMail, String inputAddress) {
    $("#item-0").click();

    $("#userName").setValue(inputName);
    $("#userEmail").setValue(inputMail);
    $("#currentAddress").setValue(inputAddress);

    $("#submit").shouldBe(clickable).click();

    $("#name").shouldHave(text("Name:" + inputName));
    $("#email").shouldHave(text("Email:" + inputMail));


    if (!$$("p#currentAddress").isEmpty()) {
      $("p#currentAddress").shouldHave(text("Current Address :" + inputAddress));
    }
    LOGGER.info("Text boxes successfully verified");
  }

  @Test
  @DisplayName("Добавление новой строки в таблицу при помощи формы")
  void successfulAddNewRecord() {
    $("#item-3").click();
    $("#addNewRecordButton").click();

    $("#firstName").setValue("Stanislav");
    $("#lastName").setValue("Strelkov");
    $("#userEmail").setValue("testEmail@mail.ru");
    $("#age").setValue("24");
    $("#salary").setValue("90000");
    $("#department").setValue("QA");

    $("#submit").click();

    assert($$(".rt-tbody .rt-tr-group").stream()
        .anyMatch(row -> row.$$(".rt-td").get(0).getText().equals("Stanislav")));

    LOGGER.info("New record added");
  }

  @Test
  @DisplayName("Форма регистрации осталась открытой из-за незаполнения обязательных полей")
  void registrationFormNotClose() {
    $("#item-3").click();
    $("#addNewRecordButton").click();

    $("#firstName").setValue("Stas");
    $("#submit").click();

    assert($("#registration-form-modal").isDisplayed());

    LOGGER.info("Registration Form still open");
  }

  @Test
  @DisplayName("Закрытие формы регистрации нажатием кнопки Esc")
  void registrationFormCloseByEsc() {
    $("#item-3").click();
    $("#addNewRecordButton").click();

    $("#firstName").sendKeys(Keys.ESCAPE);
    $("#registration-form-modal").shouldNotBe(Condition.visible, Duration.ofSeconds(1));

    assertFalse($("#registration-form-modal").isDisplayed());

    LOGGER.info("Registration Form closed");
  }

  @Test
  @DisplayName("Изменение поля возраст у записи c Last Name Gentry")
  void successfulEditAgeInRecord() {
    $("#item-3").click();

    $(byXpath(
        "//div[contains(@class, 'rt-tbody')]//div[text()='Gentry']"
            + "/ancestor::div[contains(@class, 'rt-tr')]//span[@title='Edit']")).click();

    $("#age").clear();
    $("#age").setValue("35");
    $("#submit").click();

    $(byXpath("//div[contains(@class, 'rt-tbody')]//div[text()='Gentry']"
            + "/ancestor::div[contains(@class, 'rt-tr')]//div[3]")).shouldHave(text("35"));

    LOGGER.info("Age edited");
  }
}
