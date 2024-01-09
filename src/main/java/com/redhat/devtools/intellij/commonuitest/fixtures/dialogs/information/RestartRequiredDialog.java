package com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.information;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.data.RemoteComponent;
import com.intellij.remoterobot.fixtures.CommonContainerFixture;
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import com.intellij.remoterobot.fixtures.JButtonFixture;
import com.redhat.devtools.intellij.commonuitest.utils.constants.XPathDefinitions;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

import static com.intellij.remoterobot.search.locators.Locators.byXpath;

/**
 * Restart Required dialog fixture
 *
 * @author mszuc@redhat.com
 */
@DefaultXpath(by = "MyDialog type", xpath = "//div[@title='Restart Required']")
@FixtureName(name = "Restart Required Dialog")
public class RestartRequiredDialog extends CommonContainerFixture {
    public RestartRequiredDialog(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }

    /**
     * Press the 'Shutdown' button
     */
    public void shutdown() {
        JButtonFixture button = button(byXpath(XPathDefinitions.SHUTDOWN_BUTTON), Duration.ofSeconds(2));
        button.clickWhenEnabled();
    }

    /**
     * Press the 'Not Now' button
     */
    public void notNow() {
        JButtonFixture button = button(byXpath(XPathDefinitions.NOT_NOW_BUTTON), Duration.ofSeconds(2));
        button.click();
    }
}
