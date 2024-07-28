package org.demoqa.hwselenide;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.Configuration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BooksTests {

  protected static final Logger LOGGER = LogManager.getLogger();

  private int numberRows() {
     return $(".rt-tbody").$$(".rt-tr-group").size();
  }

  @BeforeEach
  void setUp() {
    Configuration.pageLoadStrategy = "eager";
    open("https://demoqa.com/books");
    getWebDriver().manage().window().maximize();
  }

  @Test
  @DisplayName("Отображение количества книг на странице")
  void checkNumberBooksPage() {
    assertEquals(10, numberRows());
    LOGGER.info("Number of books on page corresponds expected");
  }

  @Test
  @DisplayName("Изменения количества книг на странице")
  void changeNumberBooksPage() {
    $("div.-center span select").selectOptionByValue("5");
    assertEquals(5, numberRows());
    LOGGER.info("Number of books on page changed");
  }

  @Test
  @DisplayName("Переход на 2 страницу")
  void switchCurrentPageToNext() {
    $("div.-center span select").selectOptionByValue("5");
    $(".-next button").click();
    assertEquals("2", $("input[type=number]").getAttribute("value"));
    LOGGER.info("Switch current page");
  }

  @Test
  @DisplayName("Переход на 2 страницу и обратно на 1")
  void changePageToNextAndPrev() {
    $("div.-center span select").selectOptionByValue("5");
    $(".-next button").click();
    $(".-previous button").click();
    assertEquals("1", $("input[type=number]").getAttribute("value"));
    LOGGER.info("Switch current page to next and back");
  }
}
