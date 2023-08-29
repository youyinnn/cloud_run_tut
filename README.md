## <COEN6313: Programming On Cloud> TUT: Google Cloud Run

This tutorial will go through two demo projects using Google Cloud Run.

Before you dive into the coding, you should study the following materials. No need to actually operate on the Cloud Run.

- Overall:

  - [What is Cloud Run](https://cloud.google.com/run/docs/overview/what-is-cloud-run): You should understand the concept of "Cloud Run Services" and "Clould Run Jobs."

  - [Is my app a good fit for Cloud Run?](https://cloud.google.com/run/docs/fit-for-run): You should know what kind of work suits Google Cloud Run.

- Cloud Run Services:

  - [Quickstart: Deploy to Cloud Run from a Git Repository](https://cloud.google.com/run/docs/quickstarts/deploy-continuously#cloudrun_deploy_continuous_code-python)

  - [Deploy a Python Service to Cloud Run from Source Code](https://cloud.google.com/run/docs/quickstarts/build-and-deploy/deploy-python-service)

## Preliminary Setup

1. Create a Project Space for your work at https://cloud.google.com/?hl=en.

2. Install the Google Cloud CLI: https://cloud.google.com/sdk/docs/install, run init, and select the project you just created.

3. (Optional) Install docker in your local to debug with your Dockerfile.

## Cloud Run Services

There are three approaches to deploying your project to Cloud Run: (1) from a published docker image, (2) from a GitHub repository, and (3) from your local source code.

In this tutorial, we will walk through the last two approaches.

### Deploy from a Git Repository

We can deploy our project on GitHub to Cloud Run and leverage the CI/CD of it.

1. Clone this repo;

2. Go to Cloud Run and create a service.

   ![image-20230829103130826](img/image-20230829103130826.png)

3. Select "... from a source repository"; Setup Cloud Build; Authorize to your GitHub account; Select the repo you just cloned;

   ![image-20230829103650087](img/image-20230829103650087.png)

   Select the main branch; Select build type "Dockerfile" and locate the file.

   ![image-20230829104126827](img/image-20230829104126827.png)

   Allow unauthenticated invocations and create it;
   ![image-20230829104232619](img/image-20230829104232619.png)

4. Your code now is created and deploying on Cloud Run;

   ![84EF0266-791E-472D-8BFE-41EEEF86634B_1_201_a](img/84EF0266-791E-472D-8BFE-41EEEF86634B_1_201_a.jpeg)

5. Visit the URL;

   ![image-20230829105124446](img/image-20230829105124446.png)

6. Make some changes in your code and commit it to the GitHub repo;
   ![image-20230829105322586](img/image-20230829105322586.png)

7. Visit the Build History. You should see a new build is processing;

   ![image-20230829105418670](img/image-20230829105418670.png)

8. The change should be updated to the web service;

   ![image-20230829105523106](img/image-20230829105523106.png)



### Deploy from Local Source Code using Google Cloud CLI

Sometimes, you need to deploy your local work to the cloud for debugging. One simple way is to deploy your code using Google Cloud CLI.

In this repo, we have a Java application demo in `skier_app_java`.

Once you have installed the CLI tools, you now can deploy this project with:

``` bash
cd ./skier_app_java
```

and run:

``` bash
gcloud run deploy 
```

Follow the prompt: (1) stay default for source code location; (2) stay default for service name; (3) select region; (4) allow unauthenticated invocations.

![image-20230829145842650](img/image-20230829145842650.png)

This will trigger the Cloud Build first to build your image:

![image-20230829150235776](img/image-20230829150235776.png)

Then, it will create a Cloud Run Service:

![image-20230829150629404](img/image-20230829150629404.png)

You can now visit the [<your_cloudrun_service_url>/coen6731/public/]() to play with the Java Web application.

![image-20230829150817603](img/image-20230829150817603.png)

To deploy your changes on local, you can simply just run the `gcloud run deploy ` again.
