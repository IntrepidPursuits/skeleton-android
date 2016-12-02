Skeleton
========

The starting point for Android apps at Intrepid.

The project contains the following components:

-   Commonly used third party dependencies (support library, RxJava, Jake Wharton Experience, etc)
-   Gradle configuration (build types, signing configs, retrolambda)
-   Base MVP framework (includes a few examples and how they are used in tests)
-   Various other setups that we often do when starting projects (tests, custom Application, Retrofit, Timber, Crashlytics, common strings, etc)

Cloning the project
-------------------
To start a new project that's based on this project, you simply need to
download/clone the repo and then rename all instances of "skeleton" to 
your project name. You can use the [necromancer.sh](./necromancer.sh) to automate this

The script takes in the following arguments
```
-p  Specifies the the package name of the new app. The package name 
    should be in the for of x.y.z . The last part of the package name 
    will also serve as the application name and the directory name.
-d  Specifies the directory that the project will be downloaded/cloned 
    to. Defaults to the current directory if it's not specified.
```

example usage:
```
./necromancer.sh -p io.intrepid.zombie -d ~/AndroidStudioProjects/
```

Running the Tests
-----------------

### Unit tests
Unit tests can run be using the standard commands. ex. `./gradlew testDebugUnitTest`

### UI/Instrumentation tests
This project uses [Spoon](https://github.com/square/spoon) and its [gradle plugin](https://github.com/stanfy/spoon-gradle-plugin) to run instrumentation tests. To run an instrumentation test, use `./gradlew spoon`

### Code coverage
Code coverage configuration are handled by [coverage.gradle](app/coverage.gradle). To generate a code coverage report, use `./gradlew testCoverage`. This will run both unit and instrumentation tests and merge the result of both tests into a single report.

