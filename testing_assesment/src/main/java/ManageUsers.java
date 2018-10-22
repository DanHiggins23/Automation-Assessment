import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ManageUsers {
    @FindBy(linkText = "Create User")
    private WebElement addUserLink;

    @FindBy(id = "username")
    private WebElement createUsername;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[2]/td[2]/input")
    private WebElement createPassword;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[3]/td[2]/input")
    private WebElement confirmPassword;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[4]/td[2]/input")
    private WebElement createName;

    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[5]/td[2]/input")
    private WebElement createEmail;

    @FindBy(id = "yui-gen2-button")
    private WebElement submitUser;

    public WebElement getAddUserLink() {
        return addUserLink;
    }

    public void createUser(String usernameTxt, String passwordTxt, String confirmPasswordTxt, String nameTxt, String emailTxt) {
        createUsername.sendKeys(usernameTxt);
        createPassword.sendKeys(passwordTxt);
        confirmPassword.sendKeys(confirmPasswordTxt);
        createName.sendKeys(nameTxt);
        createEmail.sendKeys(emailTxt);
    }

    public void createUserSubmit() {
        submitUser.click();
    }
}
