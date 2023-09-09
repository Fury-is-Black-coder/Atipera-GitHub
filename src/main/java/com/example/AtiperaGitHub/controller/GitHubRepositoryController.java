package com.example.AtiperaGitHub.controller;

import com.example.AtiperaGitHub.business.GitHubRepositoryService;
import com.example.AtiperaGitHub.cdm.ErrorResponse;
import com.example.AtiperaGitHub.cdm.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
public class GitHubRepositoryController {

    private final GitHubRepositoryService repositoryService;

    @Autowired
    public GitHubRepositoryController(GitHubRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/repositories/{username}")
    public ResponseEntity<Object> getGitHubRepositories(
            @PathVariable String username,
            @RequestHeader(value = "Accept", defaultValue = "application/json") String acceptHeader
    ) {

        try {
            List<Repository> repositories = repositoryService.getRepositories(username);
            return ResponseEntity.ok(repositories);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("The resource " + username + " was not found.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ErrorResponse(404, "User <" + username + "> was not found"));
            }
            else if (e.getStatusCode() == HttpStatus.NOT_ACCEPTABLE) {
                System.out.println("The request is not acceptable.");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .body(new ErrorResponse(406, "Not Acceptable"));
            }
            else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ErrorResponse(500, "Internal server error or limit of requests in GitHub API without token"));
            }
        }

    }
}

