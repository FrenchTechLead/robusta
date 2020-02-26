# Contributing to Robusta

:+1::tada: First off, thanks for taking the time to contribute! :tada::+1:

The following is a set of guidelines for contributing to Robusta project and its packages, which are hosted  on GitHub. These are mostly guidelines, not rules. Use your best judgment, and feel free to propose changes to this document in a pull request.

## Installation
```
# Clone the repository
> git clone https://github.com/Meshredded/robusta.git
# Enter the cloned repository
> cd robusta
# Install maven dependecies
> mvn clean install
```
> after the installation you should import the project as an existing maven project in your favorite IDE, Example in eclipse : file > Import > Maven > Existing Maven Project

## Generate robusta.jar locally
To generate robusta.jar locally ( in target/robusta.jar ), tape the following command :
```
# this command will generate a standalone executable jar file
> mvn clean compile assembly:single
```

> Don't hesitate to contribute even by translating the [documentation](docs/DOCS.md) to your language.
