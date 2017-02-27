import java.io._

object MultiConcat {

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def main(args: Array[String]) {

      try {
        val in = new BufferedReader(new InputStreamReader(System.in))
        do {
          println("This app multiply and concat file lines from  two *.txt in current dir and save in result.txt\n" +
            " Be shure that in current dir only two target *.txt files. Yes? - print y")
        } while (!(in.readLine() == "y"))
          print("reding list of files...")
        println(new File(".").getName)
        val filesHere = (new java.io.File(".")).listFiles
        val resultDelete = for {
          file <- filesHere
          if file.isFile
          if file.getName == "result.txt"
        } yield file.delete()
        for (file <- filesHere) println(file.getName) // debug print all files in "."
        println("done")
        print("filtering by *.txt ...")
        val txtFiles = for {
          file <- filesHere
          if file.isFile
          if file.getName.endsWith(".txt")
        } yield file
        println("done")
        println(s"Finded ${txtFiles.length} folowing files to be multiply: ")
        for (file <- txtFiles) println(file.getName)

        println("read lines...")
        val listLines = for {
          file <- txtFiles
        } yield {
          fileLines(file)
        }
        val firstList = listLines(0)
        val secondList = listLines(1)
        val result = for {
          elem1 <- firstList
          elem2 <- secondList
          result = elem1 ++ elem2
        } yield result

        val toFile = new PrintWriter(new File("result.txt"))
        println("Saving in result.txt...")
        for (elem <- result) {
          toFile.write(elem + "\n")
        }
        println("done")

        toFile.close
        println("..file closed now. You can exit now.")

      } catch {
        case ex: FileNotFoundException => println("File not found")
        case ex: IOException => println("Read/Write error...")
      }


    }

  }
