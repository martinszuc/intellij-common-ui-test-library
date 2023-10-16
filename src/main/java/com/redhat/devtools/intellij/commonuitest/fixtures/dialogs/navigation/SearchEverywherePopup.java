/*******************************************************************************
 * Copyright (c) 2021 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.navigation;

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.data.RemoteComponent;
import com.intellij.remoterobot.fixtures.CommonContainerFixture;
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import com.intellij.remoterobot.fixtures.JListFixture;
import com.intellij.remoterobot.fixtures.JTextFieldFixture;
import com.intellij.remoterobot.fixtures.dataExtractor.RemoteText;
import com.intellij.remoterobot.utils.Keyboard;
import com.intellij.remoterobot.utils.WaitForConditionTimeoutException;
import com.redhat.devtools.intellij.commonuitest.exceptions.UITestException;
import com.redhat.devtools.intellij.commonuitest.utils.constants.XPathDefinitions;
import com.redhat.devtools.intellij.commonuitest.utils.texttranformation.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.intellij.remoterobot.search.locators.Locators.byXpath;
import static com.intellij.remoterobot.utils.RepeatUtilsKt.waitFor;

/**
 * Search Everywhere popup fixture
 *
 * @author zcervink@redhat.com
 */
@DefaultXpath(by = "SearchEverywhereUI type", xpath = XPathDefinitions.SEARCH_EVERYWHERE_POPUP)
@FixtureName(name = "Search Everywhere Popup")
public class SearchEverywherePopup extends CommonContainerFixture {
    private final RemoteRobot remoteRobot;
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchEverywherePopup.class);


    public SearchEverywherePopup(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
        this.remoteRobot = remoteRobot;
    }

    /**
     * Activate given tab
     *
     * @param tabName name of the tab in the Search Everywhere popup
     */
    public void activateTab(String tabName) {
        try {
            button(byXpath(XPathDefinitions.label(tabName)), Duration.ofSeconds(2)).click();
        } catch (WaitForConditionTimeoutException e) {
            throw new UITestException("The '" + tabName + "' tab cannot be found.");
        }
    }

    /**
     * Invoke a command using the search field
     *
     * @param cmdToEnter command that will be invoked using the search field
     */
    public void invokeCmd(String cmdToEnter) {
        insertToSearchField(cmdToEnter);
        waitFor(Duration.ofSeconds(30), Duration.ofSeconds(1), "The search in the Search Everywhere popup did not finish in 30 seconds.", () -> didSearchFinish(cmdToEnter));
        new Keyboard(remoteRobot).hotKey(KeyEvent.VK_ENTER);
    }

    public void checkAndUpdateSetting(String settingName, String desiredState) {
        insertToSearchField(settingName);
        waitFor(Duration.ofSeconds(30), Duration.ofSeconds(1), "The search in the Search Everywhere popup did not finish in 30 seconds.", () -> didSearchFinish(settingName));

        JListFixture resultsList = this.find(JListFixture.class, byXpath(XPathDefinitions.JBLIST));
        String oppositeState = desiredState.equals("ON") ? "OFF" : "ON";

        try {
            resultsList.findText(desiredState);
            new Keyboard(remoteRobot).hotKey(KeyEvent.VK_ESCAPE);
            LOGGER.info("SearchEverywhere: Setting is already set to desired state.");
        } catch (Exception e) {
            try {
                resultsList.findText(oppositeState).click();
                new Keyboard(remoteRobot).hotKey(KeyEvent.VK_ESCAPE);
                LOGGER.info("SearchEverywhere: Setting set to desired state!.");
            } catch (Exception ex) {
                throw new UITestException("Neither '" + desiredState + "' nor '" + oppositeState + "' component can be found.");
            }
        }
    }




    private void insertToSearchField(String cmdToEnter) {
        JTextFieldFixture searchField = textField(JTextFieldFixture.Companion.byType(), Duration.ofSeconds(10));
        searchField.click();
        searchField.setText(cmdToEnter);
    }


    private boolean didSearchFinish(String cmdToInvoke) {
        String searchResultsString = TextUtils.listOfRemoteTextToString(getSearchResults());
        return searchResultsString.toLowerCase().contains(cmdToInvoke.toLowerCase());
    }

    private List<RemoteText> getSearchResults() {
        List<JListFixture> searchResults = jLists(JListFixture.Companion.byType());
        if (searchResults.isEmpty()) {
            return new ArrayList<>();
        }
        JListFixture searchResultsList = searchResults.get(0);
        return searchResultsList.findAllText();
    }
}