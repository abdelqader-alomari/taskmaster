# Task Master

## Log v1.6 10/11/2021

Task master is an android app with is help people to manage their daily tasks

It's built with Java for android applications, so any android user can use the app.

## Day one - Lab 26

### Features:

3 Activities:

- Main Page: ![Main Page](screenshots/MainPage.jpg)
- Add Task Page: ![Add Task](screenshots/AddTasksPage.jpg)
  ![Add Task](screenshots/AddTasksPage2.jpg) to show counter
- All Tasks Page: ![All Tasks](screenshots/AllTasksPage.jpg)

## Day Two - Lab 27

Added the ability to send data among different activities in the application using SharedPreferences and Intents.

### Features:

- Main Page: ![Main Page](screenshots/HomePage.jpg)
- Settings Page: ![Settings Page](screenshots/Settings1.jpg)
  ![Add userName](screenshots/Settings2.jpg)
- Task Detail Page: in this lab there is Task1, Task2, and Task3
  ![Task 1](screenshots/Task1.jpg)
  ![Task 2](screenshots/Task2.jpg)
  ![Task 3](screenshots/Task3.jpg)

<!-- From previous lab -->

- Add Task Page: ![Add Task](screenshots/AddTasksPage.jpg)
- ![Add Task](screenshots/AddTasksPage2.jpg) to show counter
- All Tasks Page: ![All Tasks](screenshots/AllTasksPage.jpg)

## Day Three - Lab 28

Added Fragment and layout and connect it to the main by recycle view and make a configuration for adapter.
So when user click on the task it will show the details.screenshots

- MainPage: ![HomePage](screenshots/homepage2.jpg)
- Android task: ![Android](screenshots/taskdetail2.jpg)
- Task2: ![Amazon](screenshots/taskdetail.jpg)

## Day Four - Lab 29

- Set up Room application, and modify Task class to be an Entity.
- Modify Add Task form to save the data entered in as a Task in database.
- Refactor homepage’s RecyclerView to display all Task entities in database.

- MainPage after add 1 task:
  ![HomePage](screenshots/HomePage3.jpg)
- Add Task Page:
  ![AddPage](screenshots/addTaskPage.jpg)
  Added Task:
  ![AddedTask](screenshots/addTask.jpg)
- Main Page with 2 tasks added:
  ![HomePage](screenshots/HomePage4.jpg)

## Day Six - Lab 32

In this lab it must pre Created AWS Account and installed Amplify CLI to set up the application with Amplify

- Using the `amplify add api` command, create a Task resource that replicates our existing Task schema.
- Update all references to the Task data to instead use AWS Amplify to access your data in DynamoDB instead of in Room.
- Modify Add Task form to save the data entered in as a Task to DynamoDB.
- Refactor homepage’s RecyclerView to display all Task entities in DynamoDB.

- Same images for previous lab:

- MainPage after add 1 task:
  ![HomePage](screenshots/HomePage3.jpg)
- Add Task Page:
  ![AddPage](screenshots/addTaskPage.jpg)
  Added Task:
  ![AddedTask](screenshots/addTask.jpg)
- Main Page with 2 tasks added:
  ![HomePage](screenshots/HomePage4.jpg)

## Day Eight - Lab 34

- In this lab I create a first apk file from this application throw android studio

apk file is the file used to publish on google play and consumed from android users

**The file Located @** app/build/outputs/apk/debug