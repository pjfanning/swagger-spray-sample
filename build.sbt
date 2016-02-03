name := "swagger-spray-sample"

scalaVersion := "2.11.7"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

resolvers += "Maven" at "https://repo1.maven.org/maven2/"

resolvers += Resolver.mavenLocal

libraryDependencies ++= Seq(
  "io.spray" %% "spray-servlet" % "1.3.3",
  "io.spray" %% "spray-can" % "1.3.3",
  "com.github.swagger-spray" %% "swagger-spray" % "0.6.2",
  "org.slf4j" % "slf4j-simple" % "1.7.14"
)
