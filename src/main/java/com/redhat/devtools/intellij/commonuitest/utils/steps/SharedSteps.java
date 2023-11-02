package com.redhat.devtools.intellij.commonuitest.utils.steps;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.fixtures.ComponentFixture;
import com.intellij.remoterobot.search.locators.Locator;

import java.time.Duration;

import static com.intellij.remoterobot.utils.RepeatUtilsKt.waitFor;

public class SharedSteps {
    public static void waitForComponentByXpath(RemoteRobot robot, int duration, Locator xpath) {
        waitFor(Duration.ofSeconds(duration), () -> robot.findAll(ComponentFixture.class, xpath)
                .stream()
                .anyMatch(ComponentFixture::isShowing));
    }
}
