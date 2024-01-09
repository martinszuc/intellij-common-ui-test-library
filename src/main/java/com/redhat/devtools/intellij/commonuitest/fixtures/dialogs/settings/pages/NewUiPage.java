package com.redhat.devtools.intellij.commonuitest.fixtures.dialogs.settings.pages;

/*******************************************************************************
 * Copyright (c) 2022 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v2.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 * Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

import com.intellij.remoterobot.RemoteRobot;
import com.intellij.remoterobot.data.RemoteComponent;
import com.intellij.remoterobot.fixtures.CommonContainerFixture;
import com.intellij.remoterobot.fixtures.DefaultXpath;
import com.intellij.remoterobot.fixtures.FixtureName;
import com.intellij.remoterobot.fixtures.JCheckboxFixture;
import com.redhat.devtools.intellij.commonuitest.utils.constants.XPathDefinitions;
import org.jetbrains.annotations.NotNull;

/**
 * New UI page fixture
 *
 * @author mszuc@redhat.com
 */
@DefaultXpath(by = "MyDialog type", xpath = XPathDefinitions.DIALOG_ROOT_PANE)
@FixtureName(name = "New UI Page")
public class NewUiPage extends CommonContainerFixture {
    public NewUiPage(@NotNull RemoteRobot remoteRobot, @NotNull RemoteComponent remoteComponent) {
        super(remoteRobot, remoteComponent);
    }

    /**
     * Toggle 'Show main menu in a separate toolbar' checkbox
     *
     * @param value boolean value to toggle the checkbox to
     */
    public void toggleMainMenuInSeparateToolbar(boolean value) {
        showMainMenuInSeparateToolbarCheckBox().setValue(value);
    }

    /**
     * Get the 'Show main menu in a separate toolbar' checkbox fixture
     *
     * @return checkbox fixture
     */
    public JCheckboxFixture showMainMenuInSeparateToolbarCheckBox() {
        return checkBox("Show main menu in a separate toolbar", true);
    }

    /**
     * Toggle 'Enable new UI' checkbox
     *
     * @param value boolean value to toggle the checkbox to
     */
    public void toggleEnableNewUI(boolean value) {
        enableNewUICheckBox().setValue(value);
    }

    /**
     * Get the value of 'Enable new UI' checkbox
     *
     * @return boolean value of the checkbox
     */
    public boolean isEnableNewUIChecked() {
        return enableNewUICheckBox().isSelected();
    }

    /**
     * Get the 'Enable new UI' checkbox fixture
     *
     * @return checkbox fixture
     */
    public JCheckboxFixture enableNewUICheckBox() {
        return checkBox("Enable new UI", true);
    }
}
