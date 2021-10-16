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

object RunPokedex {
    def main(args:Array[String]){
        println("Starting MongoDB - Scala")
        println("Welcome to the Pokedex Interface")

        while(true){
            selection
            println("Current ID value is: " + id)
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

        document.foreach(println)

        val observable: Observable[Completed] = collection.insertOne(document)
        println("Creating Pokemon: " + pokemonName)
        collection.insertOne(document).results()
    }

    def selection{
        println("Select an option")
        var setOne: Set[String] = Set("1: Query the Pokedex Database", "2: Create a Pokemon", "3: Import Your Own Pokemon! (Read JSON file and Upload to Database)")

        for(x <- setOne){
            println(x)
        }

        var num = 0
        var x = 0
        var y = 0
        var name = ""
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
                                        name = scala.io.StdIn.readLine()
                                        println("Here is the Pokemon you were looking for")
                                        collection.find(equal("name.english", name)).printResults()
                            case _ => println("Enter a valid number, returning to Query Selection...")
                                        selection
                                        
                        }
            case 2 => println("You are in the Pokemon Creation Interface")
                        RunPokedex.id += 1
                        println("Type in a name for your Pokemon")
                        pokemonName = scala.io.StdIn.readLine()
                        println("Enter a number for HP: ")
                        HP = scala.io.StdIn.readInt()
                        println("Enter a number for Attack Stat: ")
                        Attack = scala.io.StdIn.readInt()
                        println("Enter a number for Defense Stat: ")
                        Defense = scala.io.StdIn.readInt()
                        println("Enter a number for Speed Stat: ")
                        Speed = scala.io.StdIn.readInt()    

                        createDocObserve        

            case 3 => println("Enter a path for the JSON file you would like to UPLOAD")
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