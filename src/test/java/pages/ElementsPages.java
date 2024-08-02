package pages;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.codeborne.selenide.SelenideElement;

public class ElementsPages {

  private SelenideElement elementsPage = $("div.home-body > div > div:nth-child(1)"),
      radioBtnPage = $("#item-2"),
      radioBtn = $("#yesRadio"),
      textPage = $("#item-0"),
      inputName = $("#userName"),
      inputMail = $("#userEmail"),
      inputAddress = $("#currentAddress");

  public ElementsPages openElementsPage() {
    elementsPage.click();
    return this;
  }

  public ElementsPages openRadioPage() {
    radioBtnPage.click();
    return this;
  }

  public ElementsPages buttonLikeSite() {
    executeJavaScript("arguments[0].click();", radioBtn);
    return this;
  }

  public ElementsPages checkRadBtnClick() {
    $("span.text-success").shouldHave(text("Yes"));
    return this;
  }

  public ElementsPages openTextPage() {
    textPage.click();
    return this;
  }

  public ElementsPages setFullName (String value) {
    inputName.setValue(value);
    return this;
  }

  public ElementsPages setEmail (String value) {
    inputMail.setValue(value);
    return this;
  }

  public ElementsPages setAddress(String value) {
    inputAddress.setValue(value);
    return this;
  }

  public ElementsPages submitTextBox() {
    $("#submit").shouldBe(clickable).click();
    return this;
  }

  public ElementsPages checkFullName(String value) {
    $("#name").shouldHave(text("Name:" + value));
    return this;
  }
  public ElementsPages checkMail(String value) {
    $("#email").shouldHave(text("Email:" + value));
    return this;
  }
  public ElementsPages checkAddress(String value) {
    $("p#currentAddress").shouldHave(text("Current Address :" + value));
    return this;
  }
}
