package com.example.AtiperaGitHub.cdm;

public class Branch {
    private String name;
    private String lastCommitSha;


    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastCommitSha() {
        return lastCommitSha;
    }
    public void setLastCommitSha(String lastCommitSha) {
        this.lastCommitSha = lastCommitSha;
    }
}
