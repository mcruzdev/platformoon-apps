package com.github.platformoon.presentation.resources;

import com.github.platformoon.application.usecases.ci.create.CreatePipelineOnPullRequest;
import com.github.platformoon.application.usecases.ci.data.CreatePipelineOnPullRequestCommand;
import com.github.platformoon.presentation.requests.github.GithubEventRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

@Path("/gh/webhooks")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class GithubWebhookResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(GithubWebhookResource.class);
    private final CreatePipelineOnPullRequest createPipelineOnPullRequest;

    @Inject
    GithubWebhookResource(CreatePipelineOnPullRequest createPipelineOnPullRequest) {
        this.createPipelineOnPullRequest = createPipelineOnPullRequest;
    }

    @POST
    public Response listen(GithubEventRequest request, @HeaderParam("X-Github-Event") String event) {

        LOGGER.info("X-Github-Event value: {}", event);
        LOGGER.info("Request {}", request);
        if (!Objects.equals(event, "pull_request")) {
            return Response.accepted().build();
        }

        this.createPipelineOnPullRequest.execute(
                new CreatePipelineOnPullRequestCommand(
                        event,
                        request.getPull_request().getHead().getRef(),
                        request.getPull_request().getHead().getSha(),
                        request.getAction(),
                        request.getRepository().getClone_url(),
                        request.getPull_request().getHead().getRepo().getFull_name(),
                        request.getPull_request().getHead().getRepo().getName()
                )
        );

        return Response.ok().build();
    }
}
