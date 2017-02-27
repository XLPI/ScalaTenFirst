
import java.io.{File, PrintWriter}

def fileLines(file: java.io.File) =
  scala.io.Source.fromFile(file).getLines().toList



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

println("read lines")
val listLines = for {
  file <- txtFiles
} yield {
  println(s"Debug. File: ${file.getName} will be listLines")
  fileLines(file)
}
val firstList = listLines(0)
val secondList = listLines(1)

//        println("debug print all lines")
//      for(list1 <- listListLines) {
//                  for(list2 <- list1) {
//                    for (line <- list2) println(line+list2.flatMap(_+_))
//                    }
//                  }
val result = for {
  elem1 <- firstList
  elem2 <- secondList
  result = elem1++elem2
} yield result

// SAVING result to file




val toFile = new PrintWriter(new File("result.txt" ))

for (elem <- result) {
  toFile.write(elem+"\n")
}

toFile.close
