package example

import org.mongodb.scala._
import org.mongodb.scala.model._
import scala.io.Source
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.model.Projections._
import org.mongodb.scala.model.Aggregates._
import org.mongodb.scala.model.Updates._
import example.Helpers._

import scala.collection.mutable.SortedSet

object RunPokedex {
    def main(args:Array[String]){
        println("Starting MongoDB - Scala")
        println("Welcome to the Pokedex Interface")

        while(true){
            selection
        }    
    }

    // Direct Connection to MongoDB(default server local) - val client: MongoClient = MongoClient()
    // Create variables for database and collection
    val client: MongoClient = MongoClient()
    val database: MongoDatabase = client.getDatabase("projectZeroDB")
    val collection: MongoCollection[Document] = database.getCollection("pokedexCollection")

    var id = 809
    var pokemonName = "Machu"
    var HP = 0
    var Attack = 0
    var Defense = 0
    var Speed = 0

    def createDocObserve{
        val document: Document = Document(
            "id" -> id,
            "name" -> Document("english" -> pokemonName),
            "base" -> Document("HP" -> HP,
                               "Attack" -> Attack,
                               "Defense" -> Defense,
                               "Speed" -> Speed )
        )

        val observable: Observable[Completed] = collection.insertOne(document)
        collection.insertOne(document).results()
        println("Creating Pokemon: " + pokemonName)
        collection.find(equal("name.english", pokemonName)).printResults()
    }

    def selection{
        println("Select an option")
        var setOne: SortedSet[String] = SortedSet("1: Query the Pokedex Database", "2: Create a Pokemon", "3: Update an existing Pokemon", "4: Delete a Pokemon", "5: Import a Pokemon") 
        
        for(x <- setOne){
            println(x)
        }

        var num = 0
        var num2 = 0
        var name = ""
        var newValue = 0
        var newValue2 = ""
        var x = 0
        var y = 0
        
        num = scala.io.StdIn.readInt

        num match{
            case 1 => println("Select a Query Type")
                        println("1: First Ten by ID")
                        println("2: Last Ten by ID")
                        println(s"3: Provide a specific ID (1 to $id, imported Pokemon IDs may lay outside of provided range")
                        println("4: Provide a specific number range of IDs to search")
                        println("5: Search by Name")
                        num = scala.io.StdIn.readInt

                        num match{
                            case 1 => collection.find().sort(ascending("id")).limit(10).printResults()
                            case 2 => collection.find().sort(descending("id")).limit(10).printResults()
                            case 3 => println("Enter an ID number")
                                        num = scala.io.StdIn.readInt
                                        collection.find(equal("id", num)).printResults()
                            case 4 => println("Enter a range _ to _ ")
                                        println("Enter the first number")
                                        x = scala.io.StdIn.readInt
                                        println("Enter the second number")
                                        y = scala.io.StdIn.readInt
                                        for(z <- x to y){
                                            collection.find(equal("id", z)).printResults()
                                        }
                            case 5 => println("Enter a name (first letter capitalized)")
                                        name = scala.io.StdIn.readLine
                                        println("Here is the Pokemon you were looking for")
                                        collection.find(equal("name.english", name)).printResults()
                            case _ => println("Enter a valid number, returning to Query Selection...")
                                        selection
                                        
                        }
            case 2 => println("You are in the Pokemon Creation Interface")
                        RunPokedex.id += 1
                        println("Type in a name for your Pokemon")
                        pokemonName = scala.io.StdIn.readLine
                        println("Enter a number for HP: ")
                        HP = scala.io.StdIn.readInt
                        println("Enter a number for Attack Stat: ")
                        Attack = scala.io.StdIn.readInt
                        println("Enter a number for Defense Stat: ")
                        Defense = scala.io.StdIn.readInt
                        println("Enter a number for Speed Stat: ")
                        Speed = scala.io.StdIn.readInt

                        createDocObserve      

            case 3 => println("Select Pokemon by ID or Name")
                        println("1: ID")
                        println("2: Name")
                        num = scala.io.StdIn.readInt

                            num match{
                                case 1 => println("Enter an ID")
                                            num = scala.io.StdIn.readInt
                                            collection.find(equal("id", num)).printResults()
                                            println("Select a field to UPDATE")
                                            println("1: id")
                                            println("2: Name")
                                            println("3: HP")
                                            println("4: Attack")
                                            println("5: Defense")
                                            println("6: Speed")

                                            num2 = scala.io.StdIn.readInt

                                                num2 match{
                                                    case 1 => println("Enter a value to replace the current value of id")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("id", num), set("id", newValue)).printResults()
                                                                collection.find(equal("id", num)).printResults()
                                                    case 2 => println("Enter a value to replace the current value of Name")
                                                                newValue2 = scala.io.StdIn.readLine
                                                                collection.updateOne(equal("id", num), set("name.english", newValue)).printResults()
                                                                collection.find(equal("id", num)).printResults()
                                                    case 3 => println("Enter a value to replace the current value of HP")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("id", num), set("base.HP", newValue)).printResults()
                                                                collection.find(equal("id", num)).printResults()
                                                    case 4 => println("Enter a value to replace the current value of Attack")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("id", num), set("base.Attack", newValue)).printResults()
                                                                collection.find(equal("id", num)).printResults()
                                                    case 5 => println("Enter a value to replace the current value of Defense")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("id", num), set("base.Defense", newValue)).printResults()
                                                                collection.find(equal("id", num)).printResults()
                                                    case 6 => println("Enter a value to replace the current value of Speed")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("id", num), set("base.Speed", newValue)).printResults()
                                                                collection.find(equal("id", num)).printResults()
                                                }

                                case 2 => println("Enter a Name")
                                            name = scala.io.StdIn.readLine
                                            collection.find(equal("name.english", name)).printResults()
                                            println("Select a field to UPDATE")
                                            println("1: id")
                                            println("2: Name")
                                            println("3: HP")
                                            println("4: Attack")
                                            println("5: Defense")
                                            println("6: Speed")

                                            num2 = scala.io.StdIn.readInt

                                                num2 match{
                                                    case 1 => println("Enter a value to replace the current value of id")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("name.english", name), set("id", newValue)).printResults()
                                                    case 2 => println("Enter a value to replace the current value of Name")
                                                                newValue2 = scala.io.StdIn.readLine
                                                                collection.updateOne(equal("name.english", name), set("name.english", newValue)).printResults()
                                                    case 3 => println("Enter a value to replace the current value of HP")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("name.english", name), set("base.HP", newValue)).printResults()
                                                    case 4 => println("Enter a value to replace the current value of Attack")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("name.english", name), set("base.Attack", newValue)).printResults()
                                                    case 5 => println("Enter a value to replace the current value of Defense")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("name.english", name), set("base.Defense", newValue)).printResults()
                                                    case 6 => println("Enter a value to replace the current value of Speed")
                                                                newValue = scala.io.StdIn.readInt
                                                                collection.updateOne(equal("name.english", name), set("base.Speed", newValue)).printResults()
                                                }
                            }
            case 4 => println("Select Pokemon by ID or Name")
                        println("1: ID")
                        println("2: Name")
                        num = scala.io.StdIn.readInt

                        num match{
                            case 1 => println("Enter an ID")
                                            num = scala.io.StdIn.readInt
                                            collection.find(equal("id", num)).printResults()
                                            collection.deleteOne(equal("id", num)).printHeadResult(s"id $num was deleted")
                            case 2 => println("Enter a Name")
                                            name = scala.io.StdIn.readLine
                                            collection.find(equal("name.english", name)).printResults()
                                            collection.deleteOne(equal("name.english", name)).printHeadResult(s"$name was deleted")
                        }

            case 5 => println("Enter a path for the JSON file you would like to UPLOAD")
                        val fileName = scala.io.StdIn.readLine()
                        println("Reading JSON file...")
                        
                        // Import Scala.io.Source
                        // Read JSON file in Scala
                        val jsonString = Source.fromFile(fileName).getLines.toList
                        println("File Contains: ")
                        jsonString.foreach(println)
                        
                        // Convert JSON strings to mongoDB Documents
                        // BSON is mongoDB Document type
                        // This will yield a new list of mongoDB Document type objects
                        println("Converting JSON file to mongoDB Document...")
                        val bsonDocuments = jsonString.map(doc => Document(doc))

                        // Save Documents to local MongoDB
                        println("Inserting Document into Database Collection...")
                        collection.insertMany(bsonDocuments).printResults()

            case _ => println("Enter a valid number")
                        selection
        }
    }
}