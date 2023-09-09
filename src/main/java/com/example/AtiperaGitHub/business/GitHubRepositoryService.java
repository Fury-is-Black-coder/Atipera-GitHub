package com.example.AtiperaGitHub.business;

import com.example.AtiperaGitHub.cdm.Branch;
import com.example.AtiperaGitHub.cdm.Repository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GitHubRepositoryService {

    @Value("${github.api.url}")
    private String GITHUB_API_BASE_URL;
    private final RestTemplate restTemplate;

    @Autowired
    public GitHubRepositoryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Repository> getRepositories(String username) {

            String apiUrl = GITHUB_API_BASE_URL + "/users/" + username + "/repos";
            JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

            List<Repository> repositories = new ArrayList<>();
            for (JsonNode repoNode : response) {
                Repository repository = new Repository();
                repository.setName(repoNode.get("name").asText());
                repository.setOwnerLogin(repoNode.get("owner").get("login").asText());
                repository.setFork(repoNode.get("fork").asBoolean());

                List<Branch> branches = getBranchesForRepository(username, repository.getName());
                repository.setBranches(branches);

                repositories.add(repository);
            }

            List<Repository> nonForkRepositories = repositories.stream()
                        .filter(repo -> !repo.isFork())
                        .collect(Collectors.toList());

            return nonForkRepositories;
    }

    private List<Branch> getBranchesForRepository(String username, String repoName) {
        String apiUrl = "https://api.github.com/repos/" + username + "/" + repoName + "/branches";
        JsonNode response = restTemplate.getForObject(apiUrl, JsonNode.class);

        List<Branch> branches = new ArrayList<>();
        for (JsonNode branchNode : response) {
            Branch branch = new Branch();
            branch.setName(branchNode.get("name").asText());
            branch.setLastCommitSha(branchNode.get("commit").get("sha").asText());
            branches.add(branch);
        }

        return branches;
    }

}


