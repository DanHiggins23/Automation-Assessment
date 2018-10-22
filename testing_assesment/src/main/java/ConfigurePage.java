import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfigurePage {
    @FindBy(xpath = "//*[@id=\"main-panel\"]/form/table/tbody/tr[1]/td[3]/input")
    private WebElement nameTxt;

    @FindBy(id = "yui-gen1-button")
    private WebElement applyBtn;

    @FindBy(id = "yui-gen3-button")
    private WebElement saveBtn;

    public void updateName(String nameUpdateTxt) {
        nameTxt.clear();
        nameTxt.sendKeys(nameUpdateTxt);
    }

    public void submitName() {
        saveBtn.click();
    }
}
