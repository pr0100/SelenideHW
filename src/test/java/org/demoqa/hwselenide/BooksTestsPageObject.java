package org.demoqa.hwselenide;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.BooksPage;

public class BooksTestsPageObject extends TestBase {

  BooksPage booksPage = new BooksPage();
  protected static final Logger LOGGER = LogManager.getLogger();


  @Test
  @DisplayName("Отображение количества книг на странице")
  void checkNumberBooksPage() {
    booksPage.openBooksPage()
            .checkNumberBooks(10);
    LOGGER.info("Number of books on page corresponds expected");
  }

  @Test
  @DisplayName("Изменения количества книг на странице")
  void changeNumberBooksPage() {
    booksPage.openBooksPage()
        .selectSizeRows("5")
        .checkNumberBooks(5);
    LOGGER.info("Number of books on page changed");
  }

  @Test
  @DisplayName("Переход на 2 страницу")
  void switchCurrentPageToNext() {
    booksPage.openBooksPage()
        .selectSizeRows("5")
        .moveNextPage()
        .checkCurrentPage("2");
    LOGGER.info("Switch current page");
  }

  @Test
  @DisplayName("Переход на 2 страницу и обратно на 1")
  void changePageToNextAndPrev() {
    booksPage.openBooksPage()
        .selectSizeRows("5")
        .moveNextPage()
        .movePrevPage()
        .checkCurrentPage("1");
    LOGGER.info("Switch current page to next and back");
  }
}
