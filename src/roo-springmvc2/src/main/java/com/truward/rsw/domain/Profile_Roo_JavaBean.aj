// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.truward.rsw.domain;

import java.lang.Integer;
import java.lang.String;

privileged aspect Profile_Roo_JavaBean {
    
    public String Profile.getDisplayName() {
        return this.displayName;
    }
    
    public void Profile.setDisplayName(String displayName) {
        this.displayName = displayName;
    }
    
    public String Profile.getLogin() {
        return this.login;
    }
    
    public void Profile.setLogin(String login) {
        this.login = login;
    }
    
    public Integer Profile.getAge() {
        return this.age;
    }
    
    public void Profile.setAge(Integer age) {
        this.age = age;
    }
    
}