name := "swagger-spray-sample"

scalaVersion := "2.11.8"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

libraryDependencies ++= Seq(
  "io.spray" %% "spray-servlet" % "1.3.4",
  "io.spray" %% "spray-can" % "1.3.4",
  "com.github.swagger-spray" %% "swagger-spray" % "0.7.4-SNAPSHOT",
  "org.slf4j" % "slf4j-simple" % "1.7.14"
)
