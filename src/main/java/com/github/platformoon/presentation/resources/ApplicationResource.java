package com.github.platformoon.presentation.resources;

import com.github.platformoon.application.usecases.application.create.CreateApplication;
import com.github.platformoon.application.usecases.application.create.CreateApplicationCommand;
import com.github.platformoon.application.usecases.application.create.CreateApplicationOutput;
import com.github.platformoon.application.usecases.get.GetApplicationByID;
import com.github.platformoon.application.usecases.get.GetApplications;
import com.github.platformoon.application.usecases.get.GetVersionsByApplicationID;
import com.github.platformoon.domain.application.Application;
import com.github.platformoon.domain.application.GitRepository;
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
    private final GetApplicationByID getApplicationByID;
    private final GitRepository gitRepository;
    private final GetVersionsByApplicationID getVersionsByApplicationID;

    @Inject
    public ApplicationResource(
            CreateApplication createApplication,
            GetApplications getApplications,
            GetApplicationByID getApplicationByID,
            GitRepository gitRepository,
            GetVersionsByApplicationID getVersionsByApplicationID) {
        this.createApplication = createApplication;
        this.getApplications = getApplications;
        this.getApplicationByID = getApplicationByID;
        this.gitRepository = gitRepository;
        this.getVersionsByApplicationID = getVersionsByApplicationID;
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

    @GET
    @Path("/{id}")
    public Response getApplicationHandler(@PathParam(value = "id") final String id) {
        LOGGER.info("[flow:get-application-by-id] getting application by id: {}", id);
        return Response.ok(this.getApplicationByID.execute(id)).build();
    }

    @GET
    @Path("/{id}/versions")
    public Response getApplicationVersionsHandler(@PathParam(value = "id") final String id) {
        var output = this.getVersionsByApplicationID.execute(id);
        return Response.ok(output).build();
    }
}
