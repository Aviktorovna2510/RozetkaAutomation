RozetkaAutomation project

**Content of the project:**

Constants package - used constants;
driver package - contains Driver Manager and classes which can be helpful for a driver;
pages package - page object classes with WebElements and their methods;
util package - contain helpers;
tests package - test classes for running.

**Tests running**

For tests running, please do this:
1. Clone the project

2. For tests running use Maven command
(from folder of the project):

**mvn test**

This command run testng.xml
(location: src/test/resources/scenarious/testng.xml)


**Allure report browsing**

For report browsing Allure Command line needed.

MacOS: for installation with Homebrew manager use command "brew install allure"

Windows: for installation with Scoop manager use command "scoop install allure"

Detailed installation instructions for Allure https://docs.qameta.io/allure/#_get_started

After test running use "allure serve" command with path to "allure-results" directory,
which will be created after tests running.
(Example: allure serve /home/path/to/project/allure-results/).
Report will be browse in this way
![alt text](https://github.com/Aviktorovna2510/RozetkaAutomation/blob/master/allure_scr.png)
