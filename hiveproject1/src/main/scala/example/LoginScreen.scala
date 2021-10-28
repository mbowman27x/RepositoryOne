package example

import scala.io.Source

object LoginScreen {
    var administrator = "Admin"
    var adminPass = "boss"
    var user1 = "Matthew"
    var user1Pass = "enter"
    var failedAttempts = 0
    var currentUser = ""

    def option{
        var optionSeq: Seq[String] = Seq("1: Switch User", "2: Update User", "3: Display Users", "4: Exit User Options")
        println("Please select an option")
        
        for(x <- optionSeq){
            println(x)
        }

        var selection = scala.io.StdIn.readInt

        selection match{
            case 1 => loginUser
            case 2 => updateUser
            case 3 => displayUsers
            case 4 => exitOptions
            case _ => println("Enter a valid number")
                        option
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
                }
        }else if(username == user1){
            println("Enter your password")
            var passwd = scala.io.StdIn.readLine
                if(passwd == user1Pass){
                    println("Welcome " + user1)
                }
        }else if(failedAttempts == 2){
            println("3 Failed Inputs, returning to options")
            failedAttempts = 0
            option
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
                                    option
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to options")
                                    failedAttempts = 0
                                    option
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    loginUser
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
                                    option
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to options")
                                    failedAttempts = 0
                                    option
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    loginUser
                                }
                        }else if(failedAttempts == 2){
                            println("3 Failed Inputs, returning to options")
                            failedAttempts = 0
                            option
                        }else{
                            println("Enter a valid username")
                            failedAttempts += 1
                            loginUser
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
                                    println("Returning to options")
                                    option
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to options")
                                    failedAttempts = 0
                                    option
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    loginUser
                                }
                        }else if(username == user1){
                            println("Enter your password")
                            var passwd = scala.io.StdIn.readLine
                                if(passwd == user1Pass){
                                    println("So, " + user1 + ", you would like to update your password?")
                                    println("Please enter a new password")
                                    user1Pass = scala.io.StdIn.readLine
                                    println("Your password has been updated to: " + user1Pass)
                                    println("Returning to options")
                                    option
                                }else if(failedAttempts == 2){
                                    println("3 Failed Inputs, returning to options")
                                    failedAttempts = 0
                                    option
                                }else{
                                    println("Enter a valid username")
                                    failedAttempts += 1
                                    loginUser
                                }
                        }else if(failedAttempts == 2){
                            println("3 Failed Inputs, returning to options")
                            failedAttempts = 0
                            option
                        }else{
                            println("Enter a valid username")
                            failedAttempts += 1
                            loginUser
                        }

            case _ => println("Enter a valid number")
                        option
        }
    }

    def displayUsers{
        println("Here are the Users of this Application:")
        println(administrator)
        println(user1)
        option
    }

    def exitOptions{
        println("You have exited User Options")
    }
}