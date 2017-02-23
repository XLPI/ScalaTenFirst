name := "MultiCincatDictionarys"

version := "0.01"

lazy val multiCincatDictionarys = (project in file(".")).
  settings(
    name := "MultiCincatDictionarys"
  )

scalaVersion := "2.12.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.0.1" % Test
)
