package example

import example.LoginScreen
import example.StockApplication

object HiveScala {
    def main (args: Array[String]) {
        println("Hello, welcome to the Alpha Vantage API Application!")
        println("Login to continue")
        LoginScreen.loginUser
        
        while(true){
            LoginScreen.options
        }
    }
}