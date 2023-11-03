package com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.navigation;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.data.RemoteComponent;
import com.intellij.remoterobot.fixtures.CommonContainerFixture;
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import org.jetbrains.annotations.NotNull;

@DefaultXpath(by = "NavBarItemComponent type", xpath = "//div[@class='NavBarItemComponent']")
@FixtureName(name = "Nav Bar Item Component")
public class NavBarItemComponentFixture extends CommonContainerFixture {
    public NavBarItemComponentFixture(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }
}