name := "MultiConcatDictionarys"

version := "0.01"

lazy val multiConcatDictionarys = (project in file(".")).
  settings(
    name := "MultiConcatDictionarys"
  )

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)
