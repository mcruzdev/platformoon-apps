package com.github.platformoon.presentation.resources;

import com.github.platformoon.application.usecases.create.CreateApplication;
import com.github.platformoon.application.usecases.create.CreateApplicationCommand;
import com.github.platformoon.application.usecases.create.CreateApplicationOutput;
import com.github.platformoon.application.usecases.get.GetApplications;
import com.github.platformoon.domain.application.Application;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;

@Path("/applications")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class ApplicationResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationResource.class);
  private final CreateApplication createApplication;
  private final GetApplications getApplications;

  @Inject
  public ApplicationResource(CreateApplication createApplication, GetApplications getApplications) {
    this.createApplication = createApplication;
    this.getApplications = getApplications;
  }

  @POST
  public Response createApplicationHandler(CreateApplicationCommand request) {
    LOGGER.info("[flow:create-application] creating new application: {}", request);
    CreateApplicationOutput execute = this.createApplication.execute(request);
    return Response.created(URI.create("/applications/" + execute.id())).build();
  }

  @GET
  public List<Application> getApplicationsHandler() {
    LOGGER.info("[flow:get-applications] getting all applications");
    return this.getApplications.execute().content();
  }
}
