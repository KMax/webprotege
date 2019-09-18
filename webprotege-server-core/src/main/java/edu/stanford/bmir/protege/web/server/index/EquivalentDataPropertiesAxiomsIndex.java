package edu.stanford.bmir.protege.web.server.index;

import edu.stanford.bmir.protege.web.server.index.impl.Index;
import edu.stanford.bmir.protege.web.shared.inject.ProjectSingleton;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLOntologyID;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2019-08-22
 */
@ProjectSingleton
public interface EquivalentDataPropertiesAxiomsIndex extends Index {

    @Nonnull
    Stream<OWLEquivalentDataPropertiesAxiom> getEquivalentDataPropertiesAxioms(@Nonnull OWLDataProperty property,
                                                                               @Nonnull OWLOntologyID ontologyId);
}