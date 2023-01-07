Assignment link [here](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/proiect/etapa1) in romanian

Name: [Iustina-Andreea Caramida 322CA](https://github.com/iuniod)

# OOP TV

---
## Table of Contents
- src/
    - command/
        - commands/ - contains all the on page commands and the change page command
        - AbstractFactory.java - the abstract factory for the commands
        - CommandFactory.java - the factory for the commands types
        - Command.java - the interface for the commands
        - ChangePageFactory.java - the factory for the change page command
        - OnPageFactory.java - the factory for the on page commands
    - database/
        - Database.java - class that contains the database
        - PageWorkFlow.java - class that contains the page workflow and features workflow
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
            - User.java - class that reads the user
    - output/
        - OutputFactory.java - Factory for output
        - Output.java - interface for output
        - Error.java - class for error messages
        - Success.java - class for success messages
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
### Creating the output - Factory Design Pattern
For each command, we create a json file with the output. The output is created using Factory Design Pattern. According to the posibility to execute the command, we have two posibilities for the output:
- `Error.java` - if the command is not valid
- `Success.java` - if the command is valid

I choose to use Factory Design Pattern because it is a good way to write the output according to the result of the command. Moreover, it is easy to add new output types without modifying existing code.

I have also used `OutputFormat.java` to format the output in a json file.

I choose to use `Output.java` as an interface because it is a good way to implement the Factory Design Pattern, and it has a default method for writing the output in a json file that is the same for all the output types. I did not use `abstract` because a class can has only a single superclass, but multiple interfaces.

---
### Creating and using the commands - Abstract Factory Design Pattern
In `Main.java` we initialize the database of movies and users (more information about the database in the next section). We read the input data from json files. Iterating through each command, we use Abstract Factory
to create the command according to the type of command (`on page` and `change page`) and its name. For each command, it is verified if the command is valid, and according to
the result, the command is executed with an error or successfully. The output is written in a json file.

I choose to use Abstract Factory because it is a good way to create objects without exposing the creation logic to the client and refer to newly created objects using a common interface.
Moreover, it is easy to devide the commands according to their type, and finally, it is easy to add new commands without modifying existing code.

#### Commands and their implementation
The action commands are:
- `change page` - just a class because moving to a page is not different from moving to another page
- `on page` - each feature has its own class because the implementation is different for each feature

Each Feature extends `OutputFormat.java` for writing easier the output and implements `Command.java` for executing the command with Abstract Factory Design Pattern.

---
### Database - Singleton Design Pattern
I choose to use Singleton Design Pattern for the database because it is a good way to have only one instance of the database and to have a global access point to it.
Moreover, it is easy to add new data to the database without modifying existing code.
In database, I have the list of movies, users, I have the current user, the current page, the current movie list and the current movie.
Database has also a page work flow, which is used to move between pages and to know the current features that are available for the current page.
Both moving between pages and features are implemented using Singleton Design Pattern in `PageWorkFlow.java`. I choose to use Singleton Design Pattern because it is a good way to have only one instance of the page work flow.
Moreover, it is easy to add new pages and features without modifying existing code. The next page and current features are stored in maps, so it is easy to add new pages and features and to verify the posibility to move to a page or to execute a feature.

---
### Future improvements
- I would like to implement separate classes for the particular successful outputs such as for `movies` or `see details` and its features.
- Use more oneliners for the code.
- Use more design patterns such as `Builder Design Pattern` for creating the output.

- success output for:
    - movies page
    - details page + features
    - filter
    - search
      common things:
    - error message
    - user
      different things:
    - currentMoviesList:
        - movies: all not banned movies
        - details+: current movie
        - search: movies that starts with ...
        - filter: sorted movies
          !!!Using farcade for Success output types

- observer pattern for notifications

- criteria pattern for filters (x)

- strategy pattern for success output (x)

- abstract factory pattern for commands (x)

- factory pattern for output (x)

- singleton pattern for database (x)
	