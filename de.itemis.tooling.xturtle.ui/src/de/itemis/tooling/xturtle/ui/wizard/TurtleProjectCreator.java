package de.itemis.tooling.xturtle.ui.wizard;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.ui.XtextProjectHelper;
import org.eclipse.xtext.ui.util.ProjectFactory;
import org.eclipse.xtext.ui.wizard.AbstractProjectCreator;
import org.eclipse.xtext.util.StringInputStream;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class TurtleProjectCreator extends AbstractProjectCreator{

	private static final String FILE_NAME = "example.ttl";
	@Inject
	private Provider<ProjectFactory> projectFactoryProvider;
	
	@Override
	protected ProjectFactory createProjectFactory() {
		return projectFactoryProvider.get();
	}

	@Override
	protected String getModelFolderName() {
		throw new UnsupportedOperationException();
	}

	@Override
	protected List<String> getAllFolders() {
		return Collections.emptyList();
	}

	protected String[] getProjectNatures() {
		return new String[] { XtextProjectHelper.NATURE_ID,"de.itemis.tooling.xturtle.ui.de.itemis.tooling.xturtleNature"};
	}

	protected String[] getBuilders() {
		return new String[] { XtextProjectHelper.BUILDER_ID };
	}

	protected void enhanceProject(final IProject project, final IProgressMonitor monitor) throws CoreException {
		IFile file = project.getFile(FILE_NAME);
		file.create(new StringInputStream(""), true, monitor);
		project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
	}
	
	@Override
	protected IFile getModelFile(IProject project) throws CoreException {
		return project.getFile(FILE_NAME);
	}
	
	@Override
	protected List<IProject> getReferencedProjects() {
		return ((XturtleProjectInfo)getProjectInfo()).getReferencedProjects();
	}
}
