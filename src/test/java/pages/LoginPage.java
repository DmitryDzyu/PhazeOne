package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

@PageEntry(title = "Авторизация")
public class LoginPage extends Page {

    @ElementTitle(value = "Логин")
    private WebElement SWEUserName= PageFactory.getWebDriver().findElement(By.name("SWEUserName"));

    @ElementTitle(value = "Пароль")
    private WebElement SWEPassword= PageFactory.getWebDriver().findElement(By.name("SWEPassword"));

    public LoginPage() {
        PageFactory.initElements(PageFactory.getWebDriver(),this);
    }

}