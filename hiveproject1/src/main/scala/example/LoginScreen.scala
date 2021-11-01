package example

import scala.io.Source

object LoginScreen {
    var administrator = "Admin"
    var adminPass = "boss"
    var user1 = "Matthew"
    var user1Pass = "enter"
    var failedAttempts = 0
    var currentUser = ""

    def options{
        var optionSeq: List[String] = List("1: Alpha Vantage API Application", "2: User Options", "3: Exit Application")
        println("Please select an option")
        
        for(x <- optionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => println("Welcome to the STREET!") 
                        while(true){
                            StockApplication.stocks
                        }
            case 2 => userOptions
            case 3 => println("You have exited the Application")
                        println("Enter any key to wake the machine back up")
                        var reboot = scala.io.StdIn.readLine
            case _ => println("Enter a valid number")
                        options
        }
    }

    def userOptions{
        var userOptionSeq: Seq[String] = Seq("1: Switch User", "2: Update User", "3: Display Users", "4: Display Current User", "5: Exit User Options")
        println("Please select a User Option")
        
        for(x <- userOptionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => loginUser
            case 2 => updateUser
            case 3 => displayUsers
            case 4 => displayCurrentUser
            case 5 => exitUserOptions
                        options
            case _ => println("Enter a valid number")
                        userOptions
        }
    }

    def loginUser{
        println("Enter your username")
        var username = scala.io.StdIn.readLine

        if(username == administrator){
            println("Enter your password")
            var passwd = scala.io.StdIn.readLine
                if(passwd == adminPass){
                    println("Welcome " + administrator)
                    currentUser = administrator
                }else if(failedAttempts == 2){
                    println("3 Failed Inputs, please contact the nearest Data Engineer")
                    failedAttempts = 0
                    loginUser
                }else{
                    println("Incorrect Password")
                    failedAttempts += 1
                    loginUser
                }
        }else if(username == user1){
            println("Enter your password")
            var passwd = scala.io.StdIn.readLine
                if(passwd == user1Pass){
                    println("Welcome " + user1)
                    currentUser = user1
                }else if(failedAttempts == 2){
                    println("3 Failed Inputs, please contact the nearest Data Engineer")
                    failedAttempts = 0
                    loginUser
                }else{
                    println("Incorrect Password")
                    failedAttempts += 1
                    loginUser
                }
        }else if(failedAttempts == 2){
            println("3 Failed Inputs, please contact the nearest Data Engineer")
            failedAttempts = 0
            loginUser
        }else{
            println("Enter a valid username")
            failedAttempts += 1
            loginUser
        }
    }

    def updateUser{
        var updateOptionSeq: Seq[String] = Seq("1: Update Username", "2: Update Password")
        println("Select an option")

        for(x <- updateOptionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => println("Enter your username")
                        var username = scala.io.StdIn.readLine

                        if(username == administrator){
                            println("Enter your password")
                            var passwd = scala.io.StdIn.readLine
                                if(passwd == adminPass){
                                    println("So, " + administrator + ", you would like to update your username?")
                                    println("Please enter a new username")
                                    administrator = scala.io.StdIn.readLine
                                    println("Your username has been updated to: " + administrator)
                                    println("Returning to options")
                                    userOptions
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to User Options")
                                    failedAttempts = 0
                                    userOptions
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    updateUser
                                }
                        }else if(username == user1){
                            println("Enter your password")
                            var passwd = scala.io.StdIn.readLine
                                if(passwd == user1Pass){
                                    println("So, " + user1 + ", you would like to update your username?")
                                    println("Please enter a new username")
                                    user1 = scala.io.StdIn.readLine
                                    println("Your username has been updated to: " + user1)
                                    println("Returning to options")
                                    userOptions
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to User Options")
                                    failedAttempts = 0
                                    userOptions
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    updateUser
                                }
                        }else if(failedAttempts == 2){
                            println("3 Failed Inputs, returning to User Options")
                            failedAttempts = 0
                            userOptions
                        }else{
                            println("Enter a valid username")
                            failedAttempts += 1
                            updateUser
                        }

            case 2 => println("Enter your username")
                        var username = scala.io.StdIn.readLine

                        if(username == administrator){
                            println("Enter your password")
                            var passwd = scala.io.StdIn.readLine
                                if(passwd == adminPass){
                                    println("So, " + administrator + ", you would like to update your password?")
                                    println("Please enter a new password")
                                    adminPass = scala.io.StdIn.readLine
                                    println("Your password has been updated to: " + adminPass)
                                    println("Returning to User Options")
                                    userOptions
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to User Options")
                                    failedAttempts = 0
                                    userOptions
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    updateUser
                                }
                        }else if(username == user1){
                            println("Enter your password")
                            var passwd = scala.io.StdIn.readLine
                                if(passwd == user1Pass){
                                    println("So, " + user1 + ", you would like to update your password?")
                                    println("Please enter a new password")
                                    user1Pass = scala.io.StdIn.readLine
                                    println("Your password has been updated to: " + user1Pass)
                                    println("Returning to User Options")
                                    userOptions
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to User Options")
                                    failedAttempts = 0
                                    userOptions
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    updateUser
                                }
                        }else if(failedAttempts == 2){
                            println("3 Failed Inputs, returning to User Options")
                            failedAttempts = 0
                            userOptions
                        }else{
                            println("Enter a valid username")
                            failedAttempts += 1
                            updateUser
                        }

            case _ => println("Enter a valid number")
                        userOptions
        }
    }

    def displayUsers{
        println("Users of this Application:")
        println(administrator)
        println(user1)
        userOptions
    }

    def displayCurrentUser{
        println(currentUser + " is currently logged in")
    }

    def exitUserOptions{
        println("You have exited User Options")
    }
}