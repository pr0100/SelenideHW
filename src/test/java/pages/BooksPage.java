package pages;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import java.time.Duration;

public class BooksPage {
  private SelenideElement booksPage = $("div.home-body > div > div:nth-child(6)"),
      pageSizeRows = $("div.-center span select"),
      nextBtn = $(".-next button"),
      curPage = $("input[type=number]"),
      prevBtn = $(".-previous button");


  public BooksPage openBooksPage() {
    booksPage.click();
    return this;
  }

  public BooksPage selectSizeRows(String value) {
    pageSizeRows.selectOptionByValue(value);
    return this;
  }

  private int numberRows() {
    return $(".rt-tbody").$$(".rt-tr-group").size();
  }

  public BooksPage checkNumberBooks(int value) {
    pageSizeRows.shouldBe(Condition.visible, Duration.ofSeconds(5));
    assertEquals(value, numberRows());
    return this;
  }

  public BooksPage moveNextPage() {
    nextBtn.click();
    return this;
  }

  public BooksPage movePrevPage() {
    prevBtn.click();
    return this;
  }

  public BooksPage checkCurrentPage(String valuePage) {
    assertEquals(valuePage, curPage.getAttribute("value"));
    return this;
  }

}
