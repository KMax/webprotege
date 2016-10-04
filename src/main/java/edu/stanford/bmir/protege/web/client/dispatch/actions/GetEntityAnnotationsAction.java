package edu.stanford.bmir.protege.web.client.dispatch.actions;

import edu.stanford.bmir.protege.web.shared.dispatch.Action;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;
import org.semanticweb.owlapi.model.IRI;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 21/02/2013
 */
public class GetEntityAnnotationsAction extends AbstractHasProjectIdAndSubject<IRI> implements Action<GetEntityAnnotationsResult> {

    /**
     * For serialization purposes only
     */
    private GetEntityAnnotationsAction() {

    }

    public GetEntityAnnotationsAction(IRI entityIRI, ProjectId projectId) {
        super(entityIRI, projectId);
    }
}
