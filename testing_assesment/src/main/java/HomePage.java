import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {
    @FindBy(id = "j_username")
    private WebElement username;

    // Password input doesn't have ID, hence xpath
    @FindBy(xpath = "/html/body/div/div/form/div[2]/input")
    private WebElement password;

    @FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
    private WebElement manageLink;

    public void enterDetails(String usernameTxt, String passwordTxt) {
        username.sendKeys(usernameTxt);
        password.sendKeys(passwordTxt);
        password.submit();
    }

    public WebElement getManageLink() {
        return manageLink;
    }
}
