Assignment link [here](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa2) in romanian

Name: [Iustina-Andreea Caramida 322CA](https://github.com/iuniod)

# OOP TV

---
## Table of Contents
- src/
    - command/
        - commands/
            - database/
                - nofify/
                    - ObserverNotification.java - clas in charge of notifying the observers
                    - Subject.java - interface for the subject
                - Add.java - Add command class from database command type
                - Delete. java - Delete command class from database command type
                - Subscribe.java - Subscribe command
            - features/ - contains the commands for the features
            - navigate/
                - NextPage.java - class for change page command
                - BackPage.java - class for back command
        - AbstractFactory.java - the abstract factory for the commands
        - CommandFactory.java - the factory for the commands types
        - Command.java - the interface for the commands
        - ChangePageFactory.java - the factory for the change page command
        - OnPageFactory.java - the factory for the on page commands
        - DatabaseFactory.java - the factory for the database commands
    - database/
        - Constants.java - class with the constants
        - Database.java - class that contains the database
        - PageWorkFlow.java - class that contains the page workflow and features workflow
        - Recommendation.java - class recommend a movie for a user
    - input/
        - InputFormat.java - class that reads the input
        - action/
            - Action.java - class that reads the action
            - filters/
                - Contain.java - class that reads contain filter
                - Filter.java - class that reads the filter
                - Sort.java - class that reads sort filter
        - movie/
            - Movie.java - class that reads the movie
        - user/
            - Credentials.java - class that reads the credentials of the user
            - Notification.java - class in charge of notifications
            - User.java - class that reads the user
    - output/
        - error/
            - Error.java - class that writes the error
        - success/
            - filter/
                - Criteria.java - interface for the criteria
                - AllCriteria.java - class that finds all the movies that match the all criteria
                - CriteriaActors.java - class that finds all the movies that match the actors criteria
                - CriteriaGenre.java - class that finds all the movies that match the genre criteria
                - CriteriaIsNotBanned.java - class that finds all the movies that match the is not banned criteria
                - FilterList.java - class that finds all the movies that match the filter criteria
            - Context.java - class that executes the strategy
            - DetailsList.java - class that finds the movie list for the "see details" command
            - MovieList.java - class that finds the movie list for the "movies" command
            - NullList.java - class that make the movie list null
            - SearchList.java - class that finds the movie list for the "search" command
            - Strategy.java - interface for the strategy
            - Success.java - class that writes the success
        - OutputFactory.java - Factory for output
        - Output.java - interface for output
        - OutputFormat.java - class for output format
        
    - Main.java - main class
    - Test.java - for testing each test

---
## Description
Implementing a simple backend for a platform specific for watching movies and series like Netflix or HBO MAX.
The platform will have a database of movies and series, and users will be able to purchase, watch, like, rate them, filter and search them and buy premium account or tokens for purchasing movies.
The platform will also have a database of users, and each user will have a history of watched, liked and rated them.

---
## Workflow
### Reading data from json files
I created a `InputFormat.java` for reading data from json files. This class contains action, movie and user classes to read data easier and faster. Moreover, I used the same classes for commands and output, because I did not want to copy the same data in two different classes. For fields that are not read from input, I initialized them with default values, and for the fields that I do not want to write I used `@JsonIgnore`.

---
### Creating the output - Factory Design Pattern + Strategy Design Pattern + Criteria Design Pattern
For each command, we create a json file with the output. The output is created using Factory Design Pattern. According to the possibility to execute the command, we have two possibilities for the output:
- `Error.java` - if the command is not valid
- `Success.java` - if the command is valid

I choose to use Factory Design Pattern because it is a good way to write the output according to the result of the command. Moreover, it is easy to add new output types without modifying existing code.

I have also used `OutputFormat.java` to format the output in a json file.

I choose to use `Output.java` as an interface because it is a good way to implement the Factory Design Pattern, and it has a default method for writing the output in a json file that is the same for all the output types. I did not use `abstract` because a class can has only a single superclass, but multiple interfaces.

For writing the success output, I used Strategy Design Pattern. I choose to use Strategy Design Pattern because it is a good way to write the output according to the command. Moreover, it is easy to add new strategies without modifying existing code.
I used Criteria Design Pattern for filter option. I choose to use Criteria Design Pattern because it is a good way to filter the movies according to the criteria. Moreover, it is easy to add new criteria and reunite them.

---
### Creating and using the commands - Abstract Factory Design Pattern
In `Main.java` we initialize the database of movies and users (more information about the database in the next section). We read the input data from json files. Iterating through each command, we use Abstract Factory
to create the command according to the type of command (`on page` and `change page`) and its name. For each command, it is verified if the command is valid, and according to
the result, the command is executed with an error or successfully. The output is written in a json file.

I choose to use Abstract Factory because it is a good way to create objects without exposing the creation logic to the client and refer to newly created objects using a common interface.
Moreover, it is easy to devide the commands according to their type, and finally, it is easy to add new commands without modifying existing code.

#### Commands and their implementation
The action commands are:
- `change page` + `back` - actions that change the page; each type of these action has its own class
- `on page` - each feature has its own class because the implementation is different for each feature
- `database` - each type of database action has its own class

Each Feature extends `OutputFormat.java` for writing easier the output and implements `Command.java` for executing the command with Abstract Factory Design Pattern.

---
### Notifications - Observer Design Pattern
For the notifications, I used Observer Design Pattern a little different. I do not have an Observer interface because all my observers are users that are subscribed to genres.
But I used its principles: I have a method that attach a new user to a specific genre, and a method that notifies all the users that are subscribed to a specific genre.
For notifying the users, I have in `User.java` class a method that add a notification to the users list of notifications.
---
### Database - Singleton Design Pattern
I choose to use Singleton Design Pattern for the database because it is a good way to have only one instance of the database and to have a global access point to it.
Moreover, it is easy to add new data to the database without modifying the existing code.
In database, I have the list of movies, users, I have the current user, the current page, the current movie list and the current movie.
Database has also a page work flow, which is used to move between pages and to know the current features that are available for the current page.
Both moving between pages and features are implemented using Singleton Design Pattern in `PageWorkFlow.java`. I choose to use Singleton Design Pattern because it is a good way to have only one instance of the page work flow.
Moreover, it is easy to add new pages and features without modifying existing code. The next page and current features are stored in maps, so it is easy to add new pages and features and to verify the possibility to move to a page or to execute a feature.
I have also in database an instance of `ObserverNotification`, which is used to notify the user when a movie is added or deleted. It is a part of a Singleton Class, because I want to have only one instance of the Notifications class ---
in this class, I have a hashmap with the genres and the list of users that are subscribed to the genre. When a movie is added or deleted, I notify the users that are subscribed to the genres of the movie. So, I do not want to reinitialize the hashmap every time I add or delete a movie.


	