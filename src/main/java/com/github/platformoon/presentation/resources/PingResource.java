package com.github.platformoon.presentation.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/ping")
public class PingResource {

    @GET
    public String pong() {
        return "pong";
    }
}
