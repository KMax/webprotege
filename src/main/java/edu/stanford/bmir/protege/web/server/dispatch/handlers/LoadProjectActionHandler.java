package edu.stanford.bmir.protege.web.server.dispatch.handlers;

import com.google.common.base.Stopwatch;
import edu.stanford.bmir.protege.web.server.dispatch.ActionHandler;
import edu.stanford.bmir.protege.web.server.dispatch.ExecutionContext;
import edu.stanford.bmir.protege.web.server.dispatch.RequestContext;
import edu.stanford.bmir.protege.web.server.dispatch.RequestValidator;
import edu.stanford.bmir.protege.web.server.dispatch.validators.ReadPermissionValidator;
import edu.stanford.bmir.protege.web.server.dispatch.validators.ValidatorFactory;
import edu.stanford.bmir.protege.web.server.logging.WebProtegeLogger;
import edu.stanford.bmir.protege.web.server.owlapi.OWLAPIProjectManager;
import edu.stanford.bmir.protege.web.server.permissions.ProjectPermissionsManager;
import edu.stanford.bmir.protege.web.server.project.ProjectDetailsManager;
import edu.stanford.bmir.protege.web.shared.permissions.PermissionsSet;
import edu.stanford.bmir.protege.web.shared.project.LoadProjectAction;
import edu.stanford.bmir.protege.web.shared.project.LoadProjectResult;
import edu.stanford.bmir.protege.web.shared.project.ProjectDetails;
import edu.stanford.bmir.protege.web.shared.project.ProjectId;

import javax.inject.Inject;
import java.util.concurrent.TimeUnit;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 05/04/2013
 */
public class LoadProjectActionHandler implements ActionHandler<LoadProjectAction, LoadProjectResult> {

    private final ProjectDetailsManager projectDetailsManager;

    private final ProjectPermissionsManager projectPermissionsManager;

    private final OWLAPIProjectManager projectManager;

    private final ValidatorFactory<ReadPermissionValidator> validatorFactory;

    private WebProtegeLogger webProtegeLogger;

    @Inject
    public LoadProjectActionHandler(ProjectDetailsManager projectDetailsManager, ProjectPermissionsManager projectPermissionsManager, OWLAPIProjectManager projectManager, ValidatorFactory<ReadPermissionValidator> validatorFactory) {
        this.projectDetailsManager = projectDetailsManager;
        this.projectPermissionsManager = projectPermissionsManager;
        this.projectManager = projectManager;
        this.validatorFactory = validatorFactory;
    }

    @Override
    public Class<LoadProjectAction> getActionClass() {
        return LoadProjectAction.class;
    }

    @Override
    public RequestValidator getRequestValidator(LoadProjectAction action, RequestContext requestContext) {
        return validatorFactory.getValidator(action.getProjectId(), requestContext.getUserId());
    }

    @Override
    public LoadProjectResult execute(final LoadProjectAction action, ExecutionContext executionContext) {
        // Load project in parallel (as we don't return it, but want it ready for further calls).
        Stopwatch stopwatch = Stopwatch.createStarted();
        webProtegeLogger.info("Loading project: " + action.getProjectId());
        projectManager.getProject(action.getProjectId());
        stopwatch.stop();
        webProtegeLogger.info(".... loaded project in %s ms", stopwatch.elapsed(TimeUnit.MILLISECONDS));
        final ProjectId projectId = action.getProjectId();//project.getProjectId();

        ProjectDetails projectDetails = projectDetailsManager.getProjectDetails(projectId);

        PermissionsSet permissions = projectPermissionsManager.getPermissionsSet(projectId, executionContext.getUserId());
        return new LoadProjectResult(action.getProjectId(), executionContext.getUserId(), permissions, projectDetails);
    }
}
