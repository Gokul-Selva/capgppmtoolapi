package com.cg.ppmtoolapi.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ppmtoolapi.domain.Project;
import com.cg.ppmtoolapi.service.MapValidationErrorService;
import com.cg.ppmtoolapi.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
		@PostMapping
		public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

			ResponseEntity<?> errorMap =  mapValidationErrorService.mapValidationError(result);
			if(errorMap!=null) return errorMap;
			Project newProject=projectService.saveOrUpdate(project);
			return new ResponseEntity<Project>(newProject, HttpStatus.CREATED);
		}
	}

