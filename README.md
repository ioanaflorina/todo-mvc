#**Automation Framework for TODO-MVC project**

* [General info](#general-info)
* [Concepts included](#concepts-included)
* [Tools](#tools)
* [Requirements](#requirements)
* [Environment](#environment)
* [Browser](#browser)
* [Running tests](#running-tests)

## General info

Java quickstart project for automation covering UI testing for the TODO-MVC application

## Concepts included

* Page Object pattern
* Common web page interaction methods
* Commonly used test utility classes

## Tools

* Maven - Dependency management
* TestNG _ Testing framework
* Spring - Development framework for enterprise Java
* Selenium WebDriver - Browser automation framework

## Requirements

In order to use this project you need to have the following installed locally:

* Maven 3
* Chrome (UI tests use Chrome by default, can be changed in config)
* Java 1.19

## Environment

Global configurations related to environment are living in properties files, in the workspace, */resources/config* directory and can be
adjusted. For the moment there is one file corresponding to the dev environment. In order for the tests to be executed
on the desired environment, the execution command must contain the following:
* dev : *-Denv=dev*

## Browser

In order for the tests to be executed on the desired browser, the execution command must contain the following:
* chrome : *-Dbrowser=chrome*

## Running tests

To run tests, navigate to the workspace directory and run:

```$ mvn -P regression-todo-mvc test -Denv=dev -Dbrowser=chrome ```

The previous command specifies the environment for the tests to be executed (in this case dev) and the browser (chrome)

