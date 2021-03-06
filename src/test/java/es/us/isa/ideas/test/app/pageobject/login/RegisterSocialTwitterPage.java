package es.us.isa.ideas.test.app.pageobject.login;

import es.us.isa.ideas.test.app.pageobject.editor.EditorPage;
import es.us.isa.ideas.test.app.pageobject.PageObject;
import es.us.isa.ideas.test.app.pageobject.TestCase;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import static org.junit.Assert.assertTrue;

/**
 * Applied Software Engineering Research Group (ISA Group) University of
 * Sevilla, Spain
 *
 * @author Felipe Vieira da Cunha Serafim <fvieiradacunha@us.es>
 * @version 1.0
 */
public class RegisterSocialTwitterPage extends RegisterPage {

    @FindBy(id = "username_or_email")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "allow")
    WebElement loginButton;

    @FindBy(id = "completeYourProfile")
    WebElement completeYourProfileButton;

    static final Logger LOG = Logger.getLogger(RegisterSocialTwitterPage.class.getName());

    public static RegisterSocialTwitterPage navigateTo() {
        logout();
        PageFactory.initElements(getWebDriver(), LoginPage.class).clickOnTwitter();
        return PageFactory.initElements(getWebDriver(), RegisterSocialTwitterPage.class);
    }

    public RegisterSocialTwitterPage clickOnLogin() {
        loginButton.click();
        return PageFactory.initElements(getWebDriver(), RegisterSocialTwitterPage.class);
    }

    public RegisterSocialGooglePage clickOnCompleteYourProfile() {
        clickOnClickableElement(completeYourProfileButton);
        return PageFactory.initElements(getWebDriver(), RegisterSocialGooglePage.class);
    }

    public RegisterSocialTwitterPage typeUsername(String username) {
        usernameField.sendKeys(username);
        return PageFactory.initElements(getWebDriver(), RegisterSocialTwitterPage.class);
    }

    public RegisterSocialTwitterPage typePassword(String password) {
        passwordField.sendKeys(password);
        return PageFactory.initElements(getWebDriver(), RegisterSocialTwitterPage.class);
    }

    public static void testTwitterSocialRegister(String twUser, String twPass) {
        new TwitterRegisterTestCase().testTwitterSocialRegister(twUser, twPass);
    }

    public static void testTwitterSocialLogin(String twUser, String twPass) {
        new TwitterRegisterTestCase().testTwitterSocialLogin(twUser, twPass);
    }

    private static class TwitterRegisterTestCase extends TestCase {

        public void testTwitterSocialRegister(String twUser, String twPass) {
            RegisterSocialTwitterPage page = RegisterSocialTwitterPage.navigateTo()
                .typeUsername(twUser)
                .typePassword(twPass)
                .clickOnLogin();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RegisterSocialTwitterPage.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            page.clickOnCompleteYourProfile();
            PageObject.waitForElementVisible(page.nameField, 10);
            
            WebElement lastElement = PageObject.getWebDriver().findElement(By.id("settingsContainer"));
            TEST_RESULT = false;
            if (lastElement != null) {
                TEST_RESULT = page.getCurrentUrl().contains("settings/user") && !page.nameField.getText().toLowerCase().contains("null");
            }
            
            LOG.log(Level.INFO, "test_result: {0}", TEST_RESULT);
            assertTrue(TEST_RESULT);

        }
        
        public void testTwitterSocialLogin(String twUser, String twPass) {
            RegisterSocialTwitterPage page = RegisterSocialTwitterPage.navigateTo()
                .typeUsername(twUser)
                .typePassword(twPass)
                .clickOnLogin();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RegisterSocialTwitterPage.class.getName()).log(Level.SEVERE, null, ex);
            }

            TEST_RESULT = page.getCurrentUrl().contains("app/editor");

            if (TEST_RESULT) {
                new EditorPage().consoleEchoCommand("User logged with Twitter account \"" + twUser + "\".");
            }

            LOG.log(Level.INFO, "test_result: {0}", TEST_RESULT);
            assertTrue(TEST_RESULT);

        }
    }
}
