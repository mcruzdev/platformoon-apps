package com.github.platformoon.presentation.resources;

import com.github.platformoon.application.usecases.deployments.create.CreateDeployment;
import com.github.platformoon.application.usecases.deployments.create.CreateDeploymentInput;
import com.github.platformoon.presentation.requests.CreateDeploymentRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/deployments")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class DeploymentsResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(DeploymentsResource.class);
  private final CreateDeployment createDeployment;

  @Inject
  public DeploymentsResource(final CreateDeployment createDeployment) {
    this.createDeployment = createDeployment;
  }

  @POST
  public Response createNewDeploymentHandler(CreateDeploymentRequest request) {
    LOGGER.info("[flow:create-deployment] creating new deployment: {}", request);
    this.createDeployment.execute(
        new CreateDeploymentInput(
            request.applicationName(), request.environment(), request.applicationVersion()));
    return Response.accepted().build();
  }
}
