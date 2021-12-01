PROJECT_ONE
    - A Stock Market Application that ingests data from an API and queries daily stock price movement of any valid stocks in the U.S. markets

Tech Stack 
    - Visual Studio Code
    - Scala 2.11.8 + SBT
    - Hadoop HDFS, YARN, and HIVE
    - Alpha Vantage API
    - Git / Github

Features
    - A login process for Admin and Basic Users
    - Retrieves the most current statistics of a specified stock from Alpha Vantage API
    - Ability to create and name your own Hive Tables and load the relevant data
    - Several query types for analysing your custom Hive Tables such as:
        - Show last 10 Trading Days of specific stock
        - Show Max Price of a stock in last 6 months
        - Show Daily Volume Traded


Project Setup
    - Clone this repository to the desired location on your machine
    - To use and run this application, the user must have Hadoop Ecosytem and Hive installed
    - Create a file "stockData.csv" in local machine in path "/tmp/dataFolder/stockData.csv"
    - Create a directory in HDFS user path called "alphaVantageStockData"
    - Create a Hive Database called "project1_database"

Usage
    - Package the scala project into a jar file with "package" command in VSC terminal
    - Secure Copy (-scp) the jar file to your Hadoop environment folder1
    - Spark-Submit the jar file to run the program
    - LOAD some stock market data into Hive with the first two menu options of the application
    - Query and Analyse some Tables

Contributors
    - Matthew Bowman