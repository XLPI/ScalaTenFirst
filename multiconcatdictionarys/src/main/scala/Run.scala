import java.io.{File, FileNotFoundException, IOException}

object Run {

  def fileLines(file: java.io.File) =
    scala.io.Source.fromFile(file).getLines().toList

  def main(args: Array[String]) {

    try {
        print("reding list of files...")
        println(new File(".").getName)
      val filesHere = (new java.io.File(".")).listFiles
        for(file <- filesHere) println(file.getName) // debug print all files in "."
        println("done")
        print("filtering by *.txt ...")
      val txtFiles = for {
        file <- filesHere
        if file.isFile
        if file.getName.endsWith(".txt")
      } yield file
        println("done")
      println(s"Finded ${txtFiles.length} folowing files: ")
      for(file <- txtFiles) println(file.getName)

      println("read lines")
      val listListLines = for {
        file <- txtFiles
      } yield {
        List(fileLines(file))
      }
        val firstList = listListLines(0)
        val secondList = listListLines(1)

//        println("debug print all lines")
//      for(list1 <- listListLines) {
//                  for(list2 <- list1) {
//                    for (line <- list2) println(line+list2.flatMap(_+_))
//                    }
//                  }
      val result1 = for(elem1 <- firstList) {
  for(elemLine1 <- elem1 ) {

        }
      }

        val result2 = for(elem1 <- firstList) {
          for(elemLine1 <- elem1 ) {

          }
        }

        println("result list: ")
      val res3 = for(line1 <- result1)
        for(line2 <- result2) yield (line1+line2)



//        for(list1 <- listListLines) {
//          for(list2 <- list1) fileLines(list2) + fileLines(list1.flatMap())
//        }


    } catch {
      case ex: FileNotFoundException => println("File not found")
      case ex: IOException => println("Read/Write error...")
    }


  }

}
