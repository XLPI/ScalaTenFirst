import java.io._

object NumberGenerator {

  def main(ars: Array[String]): Unit = {


    val outputFileName = "NumbersList"
    val in = new BufferedReader(new InputStreamReader(System.in))

    def generationNumbers(size: Int): Range = {
      1 to size
    }

    def WriteToFile(range: Range) = {
      val toFile = new PrintWriter(new File(s"${outputFileName}_${range.size.toString}.txt"))
      println(s"Genereting ${range.size} numbers in file ${outputFileName}_${range.size.toString}.txt ...")
      for (elem <- range) {
        toFile.write(elem + "\n")
      }
      println("done")

      toFile.close
      println(s"..file closed now. Please, see $outputFileName\nExit.")
    }

    try {

      println("Generation in file list of numbers from 1 to \"your selection\"... \n")
      val countNumbers = in.readLine().toInt

      val myRange = generationNumbers(countNumbers)
      WriteToFile(myRange)

    } catch {
      case ex: IndexOutOfBoundsException => println("The required amount of files are not found!")
      case ex: FileNotFoundException => println("File not found")
      case ex: IOException => println("Read/Write error...")
    }

  }
}
