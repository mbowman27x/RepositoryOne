package example

import scala.io.Source

object StockApplication {
    

    def stocks{
        var stockOptionSeq: Seq[String] = Seq("1: What's new?", "2: Hottest Stocks Today!", "3: Hottest Stocks of the Week!", "4: Return to Main Screen")
        println("Select an option")

        for(x <- stockOptionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => println("SPY HITS ALL TIME HIGH!!! Again...")
            case 2 => println("GME STILL THE MOST HYPED STOCK ON SUPERSTONK!!!")
            case 3 => println("GME ALSO THE HOTTEST OF THE WEEK, EVERY WEEK, Forever...")
            case 4 => println("You have returned to the Main Screen")
                        LoginScreen.options
            case _ => println("Enter a valid number")
                        stocks
        }

    }
}
