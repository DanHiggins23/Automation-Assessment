import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManagePage {
    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[16]/a/dl/dt")
    private WebElement manageUserLink;

    @FindBy(xpath = "//*[@id=\"people\"]/tbody/tr[3]/td[3]/a")
    private WebElement createdUser;

    @FindBy(linkText = "matttt")
    private WebElement mattUsername;

    public WebElement getManageUserLink() {
        return manageUserLink;
    }

    public WebElement getCreatedUser() {
        return createdUser;
    }

    public WebElement getMattUsername() {
        return mattUsername;
    }
}
