# 3. Github Action Workflow Design

[![Generic badge](https://img.shields.io/badge/Date-2023/10/26-blue.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/Status-Accepted-Green.svg)](https://shields.io/)

## Context

In order to have an efficient release process we need to have a good CI/CD pipeline,
we also need to minimize the usage of the Github Actions workflow duration in order to reduce our impact on the
environment.

## Decision

We designed our Github Action Workflow to be as simple as possible, we have 3 main workflows:

- **Pull Request Check**: This workflow is triggered on every pull request, it runs the tests and checks if there are no
  secrets commited on the repository.
- **Build and Test and Deploy on Dev**: This workflow is triggered on every push to the repository, it builds the
  project and publishes it on jfrog and deploy it on our dev turbine environment.
- **Release and Deploy on QA**: This workflow is triggered manually, it builds the project, runs the tests and if
  everything is ok it creates a new release.

as we went with the mono repository approach, we decided to have a workflow for each project, this way we can have a
better control of the workflow.

In the *Pull Request Check** : In order not to execute the workflows excessively we added a condition on the workflow,
it will only run if the pull request is not a draft.

Same with the **Build and Test and Deploy on Dev** workflow, it will only build and deploy the project that we worked
on.

## Consequences

The Github Action Workflow is simple and efficient, and with our design we execute the workflows only when needed, thus
we reduce our costs and our impact on the environment.