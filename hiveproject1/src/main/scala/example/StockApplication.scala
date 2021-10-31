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

    var symbol = ""

    def stocks(): Unit = {

        var stockOptionSeq: Seq[String] = Seq("1: LOAD Stock Data from Alpha Vantage API to HDP", "2: LOAD API Data into Hive", "3: Query API Data via Hive Commands", "4: Return to Main Screen")
        println("Select an option")

        for(x <- stockOptionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => tickerSelection
            case 2 => hiveConnection
            case 3 => println("Select a Query")
                        
            case 4 => println("You have returned to the Main Screen")
                        LoginScreen.options
            case _ => println("Enter a valid number")
                        stocks
        }
    }

    def tickerSelection {
        println("Enter Stock Ticker")
        symbol = scala.io.StdIn.readLine
        getApiData
    }

    // gets data from Alpha Vantage API url
    // variables can be changed in the url to retrieve specific data

    def getApiData {
        val url = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=4TZZOJKGYN1WMDJ9"
        val result = scala.io.Source.fromURL(url).mkString
        println(result)

        moveDataToHDP(result)
    }

    // passes in result as parameter and writes the json data as a new file into specified HDP directory
    
    def moveDataToHDP(jsonData: String): Unit = {
        val filePathHDP = "/tmp/dataFolder/stockData.json"
        val writer = new PrintWriter(new File(filePathHDP))
        writer.write(jsonData)
        writer.close()

        println("File moved to HDP " + filePathHDP)
    }

    def hiveConnection {
        var con: java.sql.Connection = null

        try{
        var driverName = "org.apache.hive.jdbc.HiveDriver"
        val conStr = "jdbc:hive2://sandbox-hdp.hortonworks.com:10000/project1_database"

        Class.forName(driverName)
        con = DriverManager.getConnection(conStr, "", "")
        val stmt = con.createStatement()

        var hiveCommand = ""
        // stmt.execute(hiveCommand)
        
        println("How would you like to interact with Hive?")
        println("1: Create Table")
        println("2: Load Data into Table")

        val hiveSelection = scala.io.StdIn.readInt

        hiveSelection match{
            case 1 => println("Type in a name for the new table")
                        val newTable = scala.io.StdIn.readLine
                        hiveCommand = "CREATE TABLE IF NOT EXISTS " + newTable + "(json String)"
                        stmt.execute(hiveCommand)
                        println(newTable + " Table created")

            case 2 => println("Specify a Table destination")
                        println("Enter Table Name")
                        val table = scala.io.StdIn.readLine
                        hiveCommand = "LOAD DATA LOCAL INPATH '/tmp/dataFolder/stockData.json' INTO TABLE " + table
                        stmt.execute(hiveCommand)
                        println("Data loaded into table successfully")
        }

        }catch {
            case ex => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
            }
        }finally {
            try {
                if (con != null)
                con.close();
            } catch {
                case ex => {
                ex.printStackTrace();
                throw new Exception(s"${ex.getMessage}")
                }
            }
        }
    }
}
