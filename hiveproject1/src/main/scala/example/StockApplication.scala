package example

import scala.io.Source
import java.io.PrintWriter
import java.io.File

import scala.util.Try
import java.io.IOException
import java.sql.SQLException
import java.sql.Connection
import java.sql.ResultSet
import java.sql.Statement
import java.sql.DriverManager

object StockApplication {

    def stocks(): Unit = {

        var stockOptionSeq: Seq[String] = Seq("1: LOAD Stock Data from Alpha Vantage API to HDP", "2: LOAD API Data into Hive", "3: Query API Data", "4: Return to Main Screen")
        println("Select an option")

        for(x <- stockOptionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => tickerSelection
            case 2 => hiveLoad
            case 3 => hiveQuery
            case 4 => println("You have returned to the Main Screen")
                        LoginScreen.options
            case _ => println("Enter a valid number")
                        stocks
        }
    }

    // stock ticker variable
    var symbol = ""

    def tickerSelection {
        println("Enter Stock Ticker")
        symbol = scala.io.StdIn.readLine
        getApiData
    }

    // gets data from Alpha Vantage API url
    // variables can be changed in the url to retrieve specific data

    def getApiData {
        val url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&datatype=csv&apikey=4TZZOJKGYN1WMDJ9"
        val result = scala.io.Source.fromURL(url).mkString
        println("Retrieving Data from API Pipeline...")

        moveDataToHDP(result)
        println(result)
    }

    // passes in result as parameter and writes the json data as a new file into specified HDP directory
    
    def moveDataToHDP(csv: String): Unit = {
        val filePathHDP = "/tmp/dataFolder/stockData.csv"
        val writer = new PrintWriter(new File(filePathHDP))
        writer.write(csv)
        writer.close()

        println("File created in HDP Path: " + filePathHDP)
    }

    def hiveLoad {
        var connection: java.sql.Connection = null

        try{
        var driverName = "org.apache.hive.jdbc.HiveDriver"
        val connectionString = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/project1_database"

        Class.forName(driverName)
        connection = DriverManager.getConnection(connectionString, "", "")
        val statement = connection.createStatement()

        var hiveCommand = ""
        // statement.execute(hiveCommand)      <- this is for executing hive commands
        
        println("Enter 1 to create Table")

        val hiveSelection = scala.io.StdIn.readInt

        hiveSelection match{
            case 1 =>   println("Type in a name for the new table")
                        val newTable = scala.io.StdIn.readLine
                        hiveCommand = "CREATE TABLE " + newTable + "(time String, open String, high String, low String, close String, volume String) row format delimited fields terminated by ','"
                        statement.execute(hiveCommand)
                        println(newTable + " Table created")

                        println("LOAD DATA LOCAL INPATH '/tmp/dataFolder/stockData.csv' INTO TABLE " + newTable)
                        hiveCommand = "LOAD DATA LOCAL INPATH '/tmp/dataFolder/stockData.csv' INTO TABLE " + newTable
                        statement.execute(hiveCommand)
                        println("Data loaded into Table successfully")

            case _ =>   println("Invalid: Returning to Selections")
        }

        }catch {
            case ex => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
            }
        }finally {
            try {
                if (connection != null)
                connection.close();
            } catch {
                case ex => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
                }
            }
        }
    }

    def hiveQuery {
        var connection: java.sql.Connection = null

        try{
        var driverName = "org.apache.hive.jdbc.HiveDriver"
        val connectionString = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/project1_database"

        Class.forName(driverName)
        connection = DriverManager.getConnection(connectionString, "", "")
        val statement = connection.createStatement()

        var hiveCommand = ""
        // statement.execute(hiveCommand)      <- this is for executing hive commands
        
        var x = ""
        // x variable for printing queries in scala

        println("Enter the ticker symbol of an existing Table")
        var tickerQuery = scala.io.StdIn.readLine
        println("Select a Query")
        println("1: Look at most recent performance of " + tickerQuery)
        println("2: ")
        println("3: ")
        println("4: ")
        println("5: ")
        println("6: ")

        val querySelection = scala.io.StdIn.readInt

        querySelection match{
            case 1 =>   println("SELECT * FROM " + tickerQuery + " LIMIT 10")
                        hiveCommand = "SELECT * FROM " + tickerQuery + " LIMIT 10"
                        var x = statement.executeQuery(hiveCommand)
                        while (x.next()){
                            println(x.getString(1) + "  " + x.getString(3) + "  " + x.getString(4))
                        }
            case 2 => println("2: ") 
            case 3 => println("3: ")            
            case 4 => println("4: ")
            case _ => println("Invalid: Returning to Selections")
        }

        }catch {
            case ex => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
            }
        }finally {
            try {
                if (connection != null)
                connection.close();
            } catch {
                case ex => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
                }
            }
        }
    }
}
