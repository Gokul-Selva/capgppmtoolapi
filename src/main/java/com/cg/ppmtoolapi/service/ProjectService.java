package com.cg.ppmtoolapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ppmtoolapi.domain.Project;
import com.cg.ppmtoolapi.exception.ProjectIDException;
import com.cg.ppmtoolapi.repository.ProjectRepository;

@Service
public class ProjectService {
@Autowired
	private ProjectRepository projectRepository;
	
public Project saveOrUpdate(Project project) {
	try {
		project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
		return projectRepository.save(project);
	}
	catch (Exception e) {
		throw new ProjectIDException("ProjectIdentifier "+project.getProjectIdentifier()+" already available");
	}
}
}
