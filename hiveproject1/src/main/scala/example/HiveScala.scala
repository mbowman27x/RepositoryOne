package example

import example.LoginScreen

object HiveScala {
    def main (args: Array[String]) {
        println("Hello, welcome to Stock Market Application")
        println("Login to continue")
        LoginScreen.loginUser
        
        while(true){
            userOptions
        }
    }

    def userOptions{
        var userOptionSeq: Seq[String] = Seq("1: Stocks", "2: User Options")
        println("Please, select an option")

        for(x <- userOptionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => println("Yay Stocks!")
            case 2 => LoginScreen.option
        }
    }
}