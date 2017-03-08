import java.io._

object MultiConcat {

  val outputFileName = "result.txt"
  val in = new BufferedReader(new InputStreamReader(System.in))
  val filesHere = (new java.io.File(".")).listFiles

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def resultFileDelete() = {
    for {
      file <- filesHere
      if file.isFile
      if file.getName == outputFileName
    } yield file.delete()
  }

  def printListOfFiles(filesList: Array[File]) = {
    filesList.foreach(x => println("\t" + x))
  }

  def filteringTxt(): Array[File] = {
    print("filtering by *.txt ...")
    val txtFiles = for {
      file <- filesHere
      if file.isFile
      if file.getName.endsWith(".txt")
    } yield file
    println("done")
    txtFiles
  }

  def readLines(filesList: Array[File]): IndexedSeq[List[String]] = {
    println("read lines...")
    val listLines = for {
      i <- 0 to filesList.length
      file <- filesList
      if i <= 2
    } yield {
      fileLines(file)
    }
    println("done")
    listLines
  }

  def concatByLine(listLines: IndexedSeq[List[String]]): List[String] = {
    println("crossover over 1-st list and 2-nd list and multiplying...")
    val result = for {
      elem1 <- listLines(0)
      elem2 <- listLines(1)
      result = elem1 ++ elem2
    } yield result
    println("done")
    result
  }

  def writeResultToFile(result: List[String]) = {
    val toFile = new PrintWriter(new File(outputFileName))
    println(s"Saving in $outputFileName...")
    for (elem <- result) {
      toFile.write(elem + "\n")
    }
    println("done")
    toFile.close
    println(s"..file closed now. Please, see $outputFileName\nExit.")
  }

  def main(ars: Array[String]): Unit = {
    // TODO --  get file name from console
    try {
      resultFileDelete()
      printListOfFiles(filesHere)

      val txtFiles = filteringTxt()

      do {
        println("This app multiply and concat file lines from  one or two *.txt in current dir and save in result.txt\n" +
          " Be shure that in current dir is one or two target *.txt files. Yes? - print y")
      }
      while (!(txtFiles.length == 2) && !(in.readLine() == "y"))

      println(s"Finded ${txtFiles.length} folowing files to be multiply: ")
      printListOfFiles(txtFiles)
      val lines = readLines(txtFiles)
      val result = concatByLine(lines)
      writeResultToFile(result)

    } catch {
      case ex: IndexOutOfBoundsException => println("The required amount of files are not found!")
      case ex: FileNotFoundException => println("File not found")
      case ex: IOException => println("Read/Write error...")
    }

  }
}
