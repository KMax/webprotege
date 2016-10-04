package edu.stanford.bmir.protege.web.client.ui.library.popupmenu;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import edu.stanford.bmir.protege.web.client.ui.UIAction;
import edu.stanford.bmir.protege.web.resources.WebProtegeClientBundle;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 18/03/16
 */
public class MenuItem extends Composite implements HasEnabled, HasText {

    public static final String DISABLED = "disabled";

    interface MenuItemUiBinder extends UiBinder<HTMLPanel, MenuItem> {

    }

    private static MenuItemUiBinder ourUiBinder = GWT.create(MenuItemUiBinder.class);

    @UiField
    Label labelField;

    private final UIAction uiAction;

    private final PopupMenu popupMenu;

    public MenuItem(final UIAction action, final PopupMenu popupMenu) {
        this.uiAction = action;
        this.popupMenu = popupMenu;
        this.uiAction.setStateChangedHandler(new UIAction.StateChangedHandler() {
            @Override
            public void handleStateChanged(UIAction action) {
                setEnabled(action.isEnabled());
            }
        });
        this.uiAction.setLabelChangedHandler(new UIAction.LabelChangedHandler() {
            @Override
            public void handleLabelChanged(UIAction action) {
                setText(action.getLabel());
            }
        });
        initWidget(ourUiBinder.createAndBindUi(this));
        setText(action.getLabel());
        setEnabled(action.isEnabled());
    }

    @UiHandler("labelField")
    protected void handleClicked(ClickEvent clickEvent) {
        if (isEnabled()) {
            popupMenu.hide();
            uiAction.execute(clickEvent);
        }
    }

    @Override
    public boolean isEnabled() {
        return  !getStyleName().contains(WebProtegeClientBundle.BUNDLE.menu().popupMenuItemDisabled());
    }

    @Override
    public void setEnabled(boolean enabled) {
        if(enabled) {
            removeStyleName(WebProtegeClientBundle.BUNDLE.menu().popupMenuItemDisabled());
        }
        else {
            addStyleName(WebProtegeClientBundle.BUNDLE.menu().popupMenuItemDisabled());
        }
    }

    @Override
    public String getText() {
        return labelField.getText();
    }

    @Override
    public void setText(String text) {
        labelField.setText(text);
    }
}