package com.github.platformoon.application.usecases.ci.create;

import com.github.platformoon.application.usecases.ci.data.CreatePipelineOnPullRequestCommand;
import com.github.platformoon.domain.ci.Pipeline;
import com.github.platformoon.domain.ci.PipelineExecutor;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class CreatePipelineOnPullRequest {

    private final PipelineExecutor pipelineExecutor;

    public CreatePipelineOnPullRequest(PipelineExecutor pipelineExecutor) {
        this.pipelineExecutor = pipelineExecutor;
    }

    public void execute(CreatePipelineOnPullRequestCommand command) {
        if (Objects.equals(command.action(), "opened")) {
            this.pipelineExecutor.executePipeline(
                    Pipeline.create(
                            command.sha(),
                            command.cloneUrl(),
                            command.ref(),
                            command.event().concat(":").concat(command.action()),
                            command.name(),
                            command.fullName()
                    )
            );
        }
    }

}
