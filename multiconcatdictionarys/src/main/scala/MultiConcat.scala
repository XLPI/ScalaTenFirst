import java.io._

object MultiConcat extends App {

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  try {
    val in = new BufferedReader(new InputStreamReader(System.in))
    val filesHere = (new java.io.File(".")).listFiles
    val resultDelete = for {
      file <- filesHere
      if file.isFile
      if file.getName == "result.txt"
    } yield file.delete()

    println(" done")
    print("filtering by *.txt ...")
    val txtFiles = for {
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".txt")
    } yield file
    println("done")

    do {
      println("This app multiply and concat file lines from  one or two *.txt in current dir and save in result.txt\n" +
        " Be shure that in current dir is one or two target *.txt files. Yes? - print y")
    }
    while (!(txtFiles.length == 2) && !(in.readLine() == "y"))

    println(s"Finded ${txtFiles.length} folowing files to be multiply: ")
    for (file <- txtFiles) println(file.getName)

    println("read lines...")
    val listLines = for {
      i <- 0 to txtFiles.length
      file <- txtFiles
      if i <= 2
    } yield {
      fileLines(file)
    }
    println("done")

    println("crossover over 1-st list and 2-nd list and multiplying...")
    val result = for {
      elem1 <- listLines(0)
      elem2 <- listLines(1)
      result = elem1 ++ elem2
    } yield result
    println("done")

    val toFile = new PrintWriter(new File("result.txt"))
    println("Saving in result.txt...")
    for (elem <- result) {
      toFile.write(elem + "\n")
    }
    println("done")

    toFile.close
    println("..file closed now. Please, see result.txt\nExit.")

  } catch {
    case ex: IndexOutOfBoundsException => println("The required amount of files are not found!")
    case ex: FileNotFoundException => println("File not found")
    case ex: IOException => println("Read/Write error...")
  }

}
