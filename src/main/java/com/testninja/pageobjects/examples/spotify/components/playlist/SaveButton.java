package com.testninja.pageobjects.examples.spotify.components.playlist;

import com.testninja.pageobjects.wrappers.BaseWebComponent;
import com.testninja.pageobjects.wrappers.ScriptHelper;

public class SaveButton extends BaseWebComponent {

    public boolean isSaved() {
        return checkState("Save to Your Library");
    }

    public void clickSave() {
        if (!isSaved()) {
            interactions.click(getWrappedWebElement());
        }
    }

    private boolean checkState(String state) {
        return getAttribute("aria-label").equals(state);
    }
}
