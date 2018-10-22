import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfile {
    @FindBy(xpath = "//*[@id=\"main-panel\"]/div[2]")
    private WebElement profileTitle;

    @FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
    private WebElement configureBtn;

    public WebElement getProfileTitle() {
        return profileTitle;
    }

    public WebElement getConfigureBtn() {
        return configureBtn;
    }
}
