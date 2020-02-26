package com.example.trello.domain;

import org.springframework.security.core.GrantedAuthority;

  public enum Role implements GrantedAuthority{
      USER, ADMIN;

    public String getAuthority(){
            return name();
        }
  }