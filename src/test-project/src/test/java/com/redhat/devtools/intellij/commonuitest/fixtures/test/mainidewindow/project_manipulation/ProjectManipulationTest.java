package com.redhat.devtools.intellij.commonuitest.fixtures.test.mainidewindow.project_manipulation;

import com.redhat.devtools.intellij.commonuitest.LibraryTestBase;
import com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.FlatWelcomeFrame;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.toolwindowspane.AbstractToolWinPane;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.toolwindowspane.ProjectExplorer;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.toolwindowspane.ToolWindowPane;
import com.redhat.devtools.intellij.commonuitest.utils.project.CreateCloseUtils;
import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * ProjectManipulation test
 *
 * @author mszuc@redhat.com
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectManipulationTest extends LibraryTestBase {
    private static final String PROJECT_NAME = "empty_project";
    private static final int WAIT_TIME = 10;
    AbstractToolWinPane toolWinPane;


    @AfterAll
    public static void CloseProject(){
        CreateCloseUtils.closeProject(remoteRobot);
        FlatWelcomeFrame flatWelcomeFrame = remoteRobot.find(FlatWelcomeFrame.class);
        flatWelcomeFrame.clearWorkspace();

        verifyProjectIsClosed();
    }
    @Test
    @Order(1)
    public void CreateEmptyProjectTest() {
        verifyProjectIsClosed();
        CreateCloseUtils.createEmptyProject(remoteRobot, PROJECT_NAME);

        verifyProjectIsOpened();
        CreateCloseUtils.closeProject(remoteRobot);
    }

    @Test
    @Order(2)
    public void ReopenLastProjectTest() {
        verifyProjectIsClosed();

        CreateCloseUtils.reopenLastProject(remoteRobot);
        verifyProjectIsOpened();
    }

    private void verifyProjectIsOpened() {
        toolWinPane = remoteRobot.find(ToolWindowPane.class, Duration.ofSeconds(10));
        toolWinPane.openProjectExplorer();
        ProjectExplorer projectExplorer = toolWinPane.find(ProjectExplorer.class, Duration.ofSeconds(10));
        assertTrue(projectExplorer.isItemPresent(PROJECT_NAME), "Project: '" + PROJECT_NAME + "' should be open, but is not!");
    }

    private static void verifyProjectIsClosed() {
        try {
            remoteRobot.find(FlatWelcomeFrame.class, Duration.ofSeconds(ProjectManipulationTest.WAIT_TIME));
        } catch (Exception e) {
            fail("Project should be closed, but FlatWelcomeFrame could not be found within " + ProjectManipulationTest.WAIT_TIME + " seconds");
        }
    }

}
