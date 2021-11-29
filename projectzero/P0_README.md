PROJECT_ZERO
    A customizable Pokedex Application that allows the user to showcase basic CRUD operations in a Scala program.
 
Tech Stack
    Visual Studio Code
    Scala 2.13 + SBT
    MongoDB
    Git / Github
 
Features
    A Pokedex that can display 800+ different pokemon and cool statistics
    The ability to create your own custom pokemon and add it to the pokedex
    The ability to update or delete existing pokemon in the pokedex
    A LOAD option for importing new pokemon datasets into the pokedex
 
    Future Improvements:
        Create more queries that implement aggregations of the data
        Searching pokemon by type and best stats
 
Project Setup
    Clone this repository to the desired location on your machine
    To use and run this application, the user must have Mongo installed and should be connected to a Mongo Database Server.
    Then the user must have or create the following database and collection:
    Database = ‘projectZeroDB’
    Collection = ‘pokedexCollection’
 
Usage
    Open the project in Visual Studio Code or the preferred IDE
    Run the program and you will be presented with a menu of options
    If you are running the program for the first time, you will want to select the Import option first to LOAD the pokemon dataset into your pokedex. The application requires that you manually type or Copy/Paste the path of the json file into the command line. The default path within this project is :
    projectzero\src\main\resources\pokedex.json
    Now you can enjoy the application and perform some CRUD operations
 
Contributors
    Matthew Bowman
