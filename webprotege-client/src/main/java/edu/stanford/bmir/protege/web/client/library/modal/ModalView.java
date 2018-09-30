package edu.stanford.bmir.protege.web.client.library.modal;

import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import edu.stanford.bmir.protege.web.client.library.dlg.AcceptKeyHandler;
import edu.stanford.bmir.protege.web.client.library.dlg.DialogButton;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 27 Sep 2018
 */
public interface ModalView extends IsWidget, HasModalButtons {

    void setAcceptKeyHandler(@Nonnull Runnable runnable);

    void setEscapeKeyHandler(@Nonnull Runnable runnable);

    @Nonnull
    AcceptsOneWidget getModalContainer();

    void setModalTitle(@Nonnull String title);

    void setCloser(@Nonnull ModalCloser closer);

    void hide();

    void setPrimaryButtonFocusedOnAttach(boolean focused);
}