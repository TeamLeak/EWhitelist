package com.github.leanfe.json;

import com.fasterxml.jackson.annotation.*;

@lombok.Data
public class User {
    @lombok.Getter(onMethod_ = {@JsonProperty("username")})
    @lombok.Setter(onMethod_ = {@JsonProperty("username")})
    private String username;
    @lombok.Getter(onMethod_ = {@JsonProperty("exp")})
    @lombok.Setter(onMethod_ = {@JsonProperty("exp")})
    private long exp;
}