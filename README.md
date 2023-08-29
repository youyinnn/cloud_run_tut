## <COEN6313: Programming On Cloud> TUT: Google Cloud Run

This tutorial will go through two demo projects using Google Cloud Run.

Before you dive into the coding, you should study the following:

- Overall:

  - [What is Cloud Run](https://cloud.google.com/run/docs/overview/what-is-cloud-run): You should understand the concept of "Cloud Run Services" and "Clould Run Jobs."

  - [Is my app a good fit for Cloud Run?](https://cloud.google.com/run/docs/fit-for-run): You should know what kind of work suits Google Cloud Run.

- Cloud Run Services:

  - [Quickstart: Deploy to Cloud Run from a Git Repository](https://cloud.google.com/run/docs/quickstarts/deploy-continuously#cloudrun_deploy_continuous_code-python): You should be able to create a CI/CD Cloud Run service with a simple Python flask application from a Git Repository.

  - [Deploy a Python Service to Cloud Run from Source Code](https://cloud.google.com/run/docs/quickstarts/build-and-deploy/deploy-python-service): You should be able to create a CI/CD Cloud Run service with a simple Python flask application from your local source code using Google Cloud CLI.

## Preliminary Setup

1. Create a Project Space for your work at https://cloud.google.com/?hl=en.

2. Install the Google Cloud CLI: https://cloud.google.com/sdk/docs/install, run init, and select the project you just created.

## Cloud Run Services

There are three approaches to deploying your project to Cloud Run: (1) from a published docker image, (2) from a GitHub repository, and (3) from your local source code.

In this tutorial, we will walk through the last two approaches.

### Deploy from a Git Repository



