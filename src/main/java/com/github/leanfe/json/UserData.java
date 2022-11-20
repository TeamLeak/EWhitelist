package com.github.leanfe.json;


import com.fasterxml.jackson.annotation.*;
import java.util.List;

@lombok.Data
public class UserData {
    @lombok.Getter(onMethod_ = {@JsonProperty("users")})
    @lombok.Setter(onMethod_ = {@JsonProperty("users")})
    private List<User> users;
}

