package com.redhat.devtools.intellij.commonuitest.fixtures.test.mainidewindow.project_manipulation;

import com.redhat.devtools.intellij.commonuitest.LibraryTestBase;
import com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.FlatWelcomeFrame;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.toolwindowspane.AbstractToolWinPane;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.toolwindowspane.ProjectExplorer;
import com.redhat.devtools.intellij.commonuitest.fixtures.mainidewindow.toolwindowspane.ToolWindowPane;
import com.redhat.devtools.intellij.commonuitest.utils.project.CreateCloseUtils;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * ProjectManipulation test
 *
 * @author mszuc@redhat.com
 */
public class ProjectManipulationTest extends LibraryTestBase {
    private static final String PROJECT_NAME = "empty_project";
    AbstractToolWinPane toolWinPane;


    @Test
    public void CreateEmptyProjectTest() {
        remoteRobot.find(FlatWelcomeFrame.class, Duration.ofSeconds(5));
        CreateCloseUtils.createEmptyProject(remoteRobot, PROJECT_NAME);

        toolWinPane = remoteRobot.find(ToolWindowPane.class, Duration.ofSeconds(10));
        toolWinPane.openProjectExplorer();
        ProjectExplorer projectExplorer = toolWinPane.find(ProjectExplorer.class, Duration.ofSeconds(10));

        assertTrue(projectExplorer.isItemPresent(PROJECT_NAME), "Project: '"+ PROJECT_NAME + "' should be open, but is not!");
        CreateCloseUtils.closeProject(remoteRobot);
        remoteRobot.find(FlatWelcomeFrame.class, Duration.ofSeconds(10));
    }
}
