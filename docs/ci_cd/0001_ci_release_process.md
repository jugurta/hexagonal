# CI/CD : Release and hotfix process

[![Generic badge](https://img.shields.io/badge/Date-2023/10/24-blue.svg)](https://shields.io/)
[![Generic badge](https://img.shields.io/badge/Status-Accepted-Green.svg)](https://shields.io/)

## Version and workflow
We use the workflow [Github Flow](https://docs.github.com/en/get-started/quickstart/github-flow) and versions will be created as following ;
- Release with breaking changes, we must increase the major component of version (ex: __2__.0.0)
- Release without breaking changes, we must increase the minor component of version (ex: 2.__1__.0)
- For hotfix, we must increase the micro component of version (ex: 2.0.__1__)

## Process
### Starting a new sprint and creating the next release

1. We develop on feature-branch created from the **main** branch.
2. When a feature is finished (PR validated) it is merged on **main**
3. At the end of the sprint, when we are ready to put a series of several features on QA we created
we will trigger a workflow from main with the version number of the release and create a tag with the same version number.
5. the version of the project's pom.xml and package.json files is incremented on **main** with the next version number (ex: 2.0.0 -> 2.1.0-SNAPSHOT)
5. When the QA is validated, we deploy on production.