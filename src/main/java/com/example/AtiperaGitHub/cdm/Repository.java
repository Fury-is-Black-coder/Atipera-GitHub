package com.example.AtiperaGitHub.cdm;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Repository {
    private String name;
    private String ownerLogin;
    private List<Branch> branches;
    private boolean isFork;


    //Getters and Setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOwnerLogin() {
        return ownerLogin;
    }
    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }
    public List<Branch> getBranches() {
        return branches;
    }
    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }
    @JsonIgnore
    public boolean isFork() {
        return isFork;
    }
    public void setFork(boolean fork) {
        isFork = fork;
    }
}
