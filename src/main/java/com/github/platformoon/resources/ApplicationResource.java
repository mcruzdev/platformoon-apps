package com.github.platformoon.resources;

import com.github.platformoon.application.usecases.CreateApplication;
import com.github.platformoon.application.usecases.CreateApplicationCommand;
import com.github.platformoon.application.usecases.CreateApplicationOutput;
import com.github.platformoon.presentation.CreateApplicationRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;

@Path("/applications")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class ApplicationResource {

  private final CreateApplication createApplication;

  @Inject
  public ApplicationResource(CreateApplication createApplication) {
    this.createApplication = createApplication;
  }

  @POST
  public Response createApplicationHandler(CreateApplicationCommand request) {
    CreateApplicationOutput execute = this.createApplication.execute(request);
    return Response.created(URI.create("/applications/" + execute.id())).build();
  }
}
