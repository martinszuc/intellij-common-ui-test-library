package com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.navigation;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.data.RemoteComponent;
import com.intellij.remoterobot.fixtures.CommonContainerFixture;
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.intellij.remoterobot.search.locators.Locators.byXpath;

@DefaultXpath(by = "NewNavBarPanel type", xpath = "//div[@class='NewNavBarPanel']")
@FixtureName(name = "New Nav Bar Panel")
public class NewNavBarPanelFixture extends CommonContainerFixture {
    public NewNavBarPanelFixture(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }

    public List<NavBarItemComponentFixture> getNavBarItemComponents() {
        return findAll(NavBarItemComponentFixture.class, byXpath("//div[@class='NavBarItemComponent']"));
    }
}