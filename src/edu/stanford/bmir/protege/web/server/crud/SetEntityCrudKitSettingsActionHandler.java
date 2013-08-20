package edu.stanford.bmir.protege.web.server.crud;

import edu.stanford.bmir.protege.web.server.dispatch.AbstractHasProjectActionHandler;
import edu.stanford.bmir.protege.web.server.dispatch.ExecutionContext;
import edu.stanford.bmir.protege.web.server.dispatch.RequestContext;
import edu.stanford.bmir.protege.web.server.dispatch.RequestValidator;
import edu.stanford.bmir.protege.web.server.dispatch.validators.NullValidator;
import edu.stanford.bmir.protege.web.server.owlapi.OWLAPIProject;
import edu.stanford.bmir.protege.web.shared.crud.SetEntityCrudKitSettingsAction;
import edu.stanford.bmir.protege.web.shared.crud.SetEntityCrudKitSettingsResult;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 8/19/13
 */
public class SetEntityCrudKitSettingsActionHandler extends AbstractHasProjectActionHandler<SetEntityCrudKitSettingsAction, SetEntityCrudKitSettingsResult> {

    @Override
    public Class<SetEntityCrudKitSettingsAction> getActionClass() {
        return SetEntityCrudKitSettingsAction.class;
    }

    @Override
    protected RequestValidator<SetEntityCrudKitSettingsAction> getAdditionalRequestValidator(SetEntityCrudKitSettingsAction action, RequestContext requestContext) {
        // TODO: Should be owner?
        return NullValidator.get();
    }

    @Override
    protected SetEntityCrudKitSettingsResult execute(SetEntityCrudKitSettingsAction action, OWLAPIProject project, ExecutionContext executionContext) {
        EntityCrudKitRegistry registry = EntityCrudKitRegistry.get();
        EntityCrudKitHandler<?> handler = registry.getHandler(action.getSettings());
        // TODO: Persist
        project.setEntityCrudKitHandler(handler);
        return new SetEntityCrudKitSettingsResult();
    }

}
