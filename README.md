# Skeleton

[![Build Status](https://ci.intrepid.io/buildStatus/icon?job=Android/android-projects/skeleton-android)](https://ci.intrepid.io/job/Android/job/android-projects/job/skeleton-android/)
[![Coverage](http://ci.intrepid.io:9913/jenkins/cobertura/Android/job/android-projects/job/skeleton-android/)](https://ci.intrepid.io/job/Android/job/android-projects/job/skeleton-android/cobertura/)

The starting point for Android apps at Intrepid. An equivalent Kotlin implementation can be found [here](https://github.com/IntrepidPursuits/skotlinton-android).

1. [Overview](#overview)
1. [Cloning the Project](#cloning-the-project) 
1. [Building](#building)
    1. [Configurations](#configurations)
        1. [Release](#release)
    1. [Jenkins](#jenkins)
1. [Testing](#testing)
    1. [Unit Tests](#unit-tests)
    1. [UI/Instrumentation Tests](#uiinstrumentation-tests)
    1. [Code Coverage](#code-coverage)
1. [Architecture](#architecture)
    1. [Model-View-Presenter](#model-view-presenter)
    1. [Base Classes](#base-classes)
    1. [Third Party Libraries](#third-party-libraries)
1. [History](#history)

## Overview
The project contains the following components:

-   Commonly used third party dependencies (support library, RxJava, Jake Wharton Experience, etc)
-   Gradle configuration (build types, signing configs, retrolambda)
-   Base MVP framework (includes a few examples and how they are used in tests)
-   Various other setups that we often do when starting projects (tests, custom Application, Retrofit, Timber, Crashlytics, common strings, etc)

## Cloning the project
To start a new project that's based on this project, simply download/clone the repo and then rename all instances of "skeleton" to your project name. You can use the [necromancer.sh](./necromancer.sh) to automate this

The script takes in the following arguments
```
-p  Specifies the the package name of the new app. The package name 
    should be in the form of x.y.z . The last part of the package name 
    will also serve as the application name and the directory name.
-d  Specifies the directory that the project will be downloaded/cloned 
    to. Defaults to the current directory if it's not specified.
```

example usage:
```
./necromancer.sh -p io.intrepid.zombie -d ~/AndroidStudioProjects/
```

## Building
This project does not require any additional setup or special configurations to build or run.

### Configurations
- There are 3 different build types - Debug, QA, and Release.
- Both Debug and QA builds are debuggable and logs to console. The main difference between them is that QA builds also report to Crashlytics.
- Release builds are not debuggable and don't show console logs, but they report to Crashlytics.

Each build has different application and name suffix, so they can be installed side by side on a phone.

#### Release
To configure the release builds, generate the keystore file and add the following lines to the `local.properties` file:
```
signing_file=../release.keystore
signing_alias=#####
signing_key_password=#####
signing_password=#####
```
(replace ##### with the actual credentials)

### Jenkins
There are 2 different Jenkins builds for this project:
- skeleton-android - This is run every time something is merged into the develop branch. It runs the unit tests, generates a QA build, and uploads it to the OTA link.
- skeleton-android-pr - This is run every time a PR is created. It runs the unit tests and reports the result back to the GitHub PR page.

## Testing
### Unit Tests
Unit tests exist under the "test" directory. They can be run using the standard commands. ex. `./gradlew testDebugUnitTest`

### UI/Instrumentation Tests
UI (Espresso) tests exist under the "androidTest" directory. The project also uses [Spoon](https://github.com/square/spoon) and its [gradle plugin](https://github.com/stanfy/spoon-gradle-plugin) to run instrumentation tests and generate a easy to read report. To run an instrumentation test, use `./gradlew spoon`.

### Code Coverage
Code coverage configuration are handled by [coverage.gradle](app/coverage.gradle). To generate a code coverage report, use `./gradlew testCoverage`. This will run both unit and instrumentation tests and merge the result of both tests into a single report.

## Architecture
### Model-View-Presenter
The app uses the popular MVP architecture to allow for separation of logic and ease of testing. In this paradigm, all business logic should live inside presenters (but they can delegate some tasks to other classes that are injected as dependencies). Activities and fragment will act as "views", they should not have any logic other than passing the user events to the presenter and displaying the data. There are also Contract classes that specify the communication interface between the views and presenters.  

### Base Classes
- `BaseActivity`: Base class for all activities. Includes lifecycle logging and view inflation.
- `BaseMvpActivity`: Base class for activities that will have some business logic instead of just hosting a fragment. Includes setup for interactions with presenter.
- `BaseFragmentActivity`: Base class for activities whose sole purpose to to host a fragment. If the activity contains any additional logic, use `BaseMvpActivity` instead.
- `BaseFragment`: Basically the same as `BaseMvpActivity`, but for fragments.
- `BasePresenter`: Base class for all presenters. Includes lifecycle setup and common dependencies. Goes along with `BaseMvpActivity` and `BaseFragment`.
- `BaseContract`: Includes interfaces that all views and presenters should implement.
- `PresenterConfiguration`: Wrapper class for common dependencies that all presenters are expected to have.

### Third Party Libraries
- Retrolambda (adds support for lambda expressions in Java 6)
- RxJava/RxAndroid (helps with asynchronous event handling)
- ButterKnife (easier view binding)
- Retrofit/OkHttp (simplifies network requests)
- Timber (better logging tool)
- Picasso (simplifies image loading)
- Calligraphy (easier font configuration)
- LeakCanary (helps catch memory leaks)
- Crashlytics (reports crashes)
- Mockito (mocks out classes for tests)
- Espresso (UI testing framework)
- Spoon (displays the test results in an easy to read format)

## History
