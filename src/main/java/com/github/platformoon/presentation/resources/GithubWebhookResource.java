package com.github.platformoon.presentation.resources;

import com.github.platformoon.application.usecases.application.create.CreateApplicationVersion;
import com.github.platformoon.application.usecases.application.create.CreateApplicationVersionCommand;
import com.github.platformoon.presentation.requests.github.GithubEventRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/gh/webhooks")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class GithubWebhookResource {

    private final CreateApplicationVersion createApplicationVersion;

    @Inject
    GithubWebhookResource(CreateApplicationVersion createApplicationVersion) {
        this.createApplicationVersion = createApplicationVersion;
    }

    @POST
    public Response listen(GithubEventRequest request, @HeaderParam("X-Github-Event") String event) {

        var command = new CreateApplicationVersionCommand(
                event,
                request.repository().name(),
                request.head_commit().id(),
                request.head_commit().url(),
                request.head_commit().author().name(),
                request.head_commit().author().username(),
                request.ref()
        );

        this.createApplicationVersion.execute(command);

        return Response.ok().build();
    }
}
