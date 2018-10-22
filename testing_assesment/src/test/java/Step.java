import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;

public class Step {
    WebDriver driver = null;
    ExtentReports report = new ExtentReports("C:\\Users\\Admin\\Desktop\\Reports\\assessment_report.html", true);
    ExtentTest task1;
    ExtentTest task2;
    ExtentTest task3;

    @Before
    public void setup() {
        //Start driver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Given("^that you are on the create UserScreen$")
    public void that_you_are_on_the_create_UserScreen() {
        task1 = report.startTest("Task 1: Allow users to be added to the database");

        HomePage home = PageFactory.initElements(driver, HomePage.class);
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);
        driver.get("http://localhost:8080/");

        // Login to jenkins
        home.enterDetails("root", "root");
        task1.log(LogStatus.INFO, "User logged into Jenkins");

        // User directed to the manage page
        home.getManageLink().click();
        task1.log(LogStatus.INFO, "User directed to manage page");

        // Ensure that the correct page is opened by comparing title
        task1.log(LogStatus.INFO, "Checking if user on the manage page");
        assertEquals("Manage Jenkins [Jenkins]", driver.getTitle());
    }

    @When("^the User details are entered on the Create UserScreen$")
    public void the_User_details_are_entered_on_the_Create_UserScreen() {
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);
        ManageUsers manageUsers = PageFactory.initElements(driver, ManageUsers.class);

        // Click manage users
        manage.getManageUserLink().click();
        task1.log(LogStatus.INFO, "User directed to manage users link");

        // Click create user
        manageUsers.getAddUserLink().click();
        task1.log(LogStatus.INFO, "User directed to the form to create user");

        // Fill in form
        manageUsers.createUser("testuser","1234", "1234", "Dan Higgins", "testuser@test.com");

        // Store text from username in variable for comparison
        task1.log(LogStatus.INFO, "User details entered in form");
    }

    @When("^the details are submitted on the Create UserScreen$")
    public void the_details_are_submitted_on_the_Create_UserScreen() {
        // Submit form
        ManageUsers manageUsers = PageFactory.initElements(driver, ManageUsers.class);
        manageUsers.createUserSubmit();
        task1.log(LogStatus.INFO, "User submitted to form");
    }

    @Then("^the Username should be visible on the UsersScreen$")
    public void the_Username_should_be_visible_on_the_UsersScreen() {
        // Check that user has been created
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);

        // Compare data stored in username variable to actual
        task1.log(LogStatus.INFO, "User has been created");
        assertEquals("Dan Higgins", manage.getCreatedUser().getText());
    }

    @When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, and \"([^\"]*)\" Fullname are entered on the Create UserScreen$")
    public void the_User_details_username_password_confirm_Password_and_Fullname_are_entered_on_the_Create_UserScreen(String arg1, String arg2, String arg3, String arg4)  {
        // Direct back to the create user page
        ManageUsers manageUsers = PageFactory.initElements(driver, ManageUsers.class);
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);

        manage.getManageUserLink().click();
        manageUsers.getAddUserLink().click();

        // Create user using paramaters
        manageUsers.createUser(arg1, arg2, arg3, arg4, "testemail@test.com");

        //Submit users
        task1.log(LogStatus.INFO, "Users from paramaters added");
    }

    @Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
    public void the_username_should_be_visible_on_the_UsersScreen(String arg1)  {
        // Check that user has been created
        if (arg1.equals(driver.findElement(By.linkText(arg1)).getText())) {
            task1.log(LogStatus.INFO, "User created succesfully");
            task1.log(LogStatus.PASS, "Test passed successfully");
        } else {
            task1.log(LogStatus.INFO, "User failed to create");
            task1.log(LogStatus.FAIL, "Test failed");
        }

        report.endTest(task1);
        assertEquals(arg1, driver.findElement(By.linkText(arg1)).getText());
    }

    @Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
    public void the_username_is_visible_on_the_UsersScreen(String arg1)  {
        task2 = report.startTest("Task 2: View a user on the database");

        //Direct user to the manage users page
        HomePage home = PageFactory.initElements(driver, HomePage.class);
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);
        driver.get("http://localhost:8080/");

        // Login to jenkins
        home.enterDetails("root", "root");
        task2.log(LogStatus.INFO, "User logged into Jenkins");

        // User directed to the manage page
        home.getManageLink().click();
        manage.getManageUserLink().click();
        task2.log(LogStatus.INFO, "User directed to manage users page");

        // Check username is visible on the user page
        assertEquals(arg1, driver.findElement(By.linkText(arg1)).getText());
    }

    @When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
    public void the_username_is_clicked_on_the_UserScreen(String arg1) {
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);
        //Click the user account link
        //POM method not used here due to passing argument in this method
        if (arg1.equals("matthewhunt")) {
            manage.getMattUsername().click();
            task2.log(LogStatus.INFO, "Username clicked");
        } else {
            driver.findElement(By.linkText(arg1)).click();
            task2.log(LogStatus.INFO, "Username clicked");
        }
    }

    @Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
    public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1) {
        //Check that user name is displayed on user profile
        UserProfile userPro = PageFactory.initElements(driver, UserProfile.class);
        task2.log(LogStatus.INFO, "Username displayed correctly");

        if (("Jenkins User ID: " + arg1).equals(userPro.getProfileTitle().getText())) {
            task2.log(LogStatus.PASS, "Test passed successfully");
        } else {
            task2.log(LogStatus.FAIL, "Test failed");
        }

        report.endTest(task2);
        assertEquals("Jenkins User ID: " + arg1, userPro.getProfileTitle().getText());
    }

    @Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
    public void the_Username_s_profile_page_has_been_loaded(String arg1) {
        task3 = report.startTest("Task 3: Update users full name");

        //Direct user to the manage users page
        HomePage home = PageFactory.initElements(driver, HomePage.class);
        ManagePage manage = PageFactory.initElements(driver, ManagePage.class);
        driver.get("http://localhost:8080/");

        // Login to jenkins
        home.enterDetails("root", "root");
        task3.log(LogStatus.INFO, "User logged into Jenkins");

        // User directed to the profile page
        home.getManageLink().click();
        manage.getManageUserLink().click();

        //Click the user account link
        driver.findElement(By.linkText(arg1)).click();
        task3.log(LogStatus.INFO, "Username clicked");
    }

    @Given("^the configure button has been clicked on the profile page$")
    public void the_configure_button_has_been_clicked_on_the_profile_page() {
        // Click configure button
        UserProfile userPro = PageFactory.initElements(driver, UserProfile.class);
        userPro.getConfigureBtn().click();
    }

    @When("^I change the old FullName on the Configure Page to a new FullName \"([^\"]*)\"$")
    public void i_change_the_old_FullName_on_the_Configure_Page_to_a_new_FullName(String arg1) {
        // Enter new full name
        ConfigurePage configure = PageFactory.initElements(driver, ConfigurePage.class);
        configure.updateName(arg1);
    }

    @When("^I save the changes to the Configure Page$")
    public void i_save_the_changes_to_the_Configure_Page() throws Throwable {
        // Click save button to make changes
        ConfigurePage configure = PageFactory.initElements(driver, ConfigurePage.class);
        configure.submitName();
    }

    @Then("^the Configure Page should show the NewFullName \"([^\"]*)\"$")
    public void the_Configure_Page_should_show_the_NewFullName(String arg1)  {
        //Check that updated name is displayed on user profile
        if (arg1.equals(driver.findElement(By.linkText(arg1)).getText())) {
            task3.log(LogStatus.PASS, "Test passed successfully");
        } else {
            task3.log(LogStatus.FAIL, "Test failed");
        }

        report.endTest(task3);
        assertEquals(arg1, driver.findElement(By.linkText(arg1)).getText());
        report.flush();
    }
}
