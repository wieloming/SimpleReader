name := "SimpleApp"

version := "1.0"

scalaVersion := "2.11.1"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.apache.poi" % "poi" % "3.15-beta2"

libraryDependencies += "org.apache.poi" % "poi-ooxml" % "3.15-beta2"

libraryDependencies +=  "com.typesafe.play" %% "play-json" % "2.3.0"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"