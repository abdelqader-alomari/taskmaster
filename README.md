# Task Master

## Log v1.4 7/11/2021

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

## Day Five - Lab 31

In this lab the core thing is test the app using Espresso Tests which is really nice and easy test come up with 2 versions
one can be write manually (coded) which is powerful and I did in this lab
and the other is visual as espresso recorded which record each action that do in app and can add asserts after that and make configurations and write codes automatically which is easier and quick, but not as powerful as regular espresso test.

In this lab I tested most features of app using Espresso test and wrote comment in each single line to make it very clear. Most Tests depend on buttons and texts and what appeared on screen.