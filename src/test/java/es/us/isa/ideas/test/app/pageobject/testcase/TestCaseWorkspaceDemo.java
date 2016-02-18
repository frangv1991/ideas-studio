package es.us.isa.ideas.test.app.pageobject.testcase;

import es.us.isa.ideas.test.app.pageobject.editor.WorkspaceSection;
import es.us.isa.ideas.test.app.pageobject.login.LoginPage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Applied Software Engineering Research Group (ISA Group) University of
 * Sevilla, Spain
 *
 * @author Felipe Vieira da Cunha Serafim <fvieiradacunha@us.es>
 * @version 1.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCaseWorkspaceDemo extends TestCase {
    
    static String demoWSName = "DemoWorkspace";

    @Test
    public void step01_loginGuest() {
        WorkspaceSection.logout();
        LoginPage.testLogin("guest", "guest");
    }

    @Test
    public void step02_createGuestWorkspace() {
        WorkspaceSection.testCreateWorkspace(demoWSName, "", "");
    }

    @Test
    public void step03_publishDemoWorkspace() {
        WorkspaceSection.testPublishDemoWorkspace();
    }

    @Test
    public void step04_viewDemo() {
        WorkspaceSection.logout();
        WorkspaceSection.testDemoView(demoWSName);
    }
    
    @Test
    public void step05_loginGuest() {
        LoginPage.testLogin("guest", "guest");
    }
    
    @Test
    public void step06_deleteGuestDemoFromDashboard() {
        WorkspaceSection.testDeleteCloneDemoFromDashboard(demoWSName);
    }
}
