name := "siddhi-demo"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "WSO2 internal Repository" at "http://maven.wso2.org/nexus/content/repositories/releases/"
resolvers += "WSO2" at "http://dist.wso2.org/maven2/"

libraryDependencies ++= {
  val siddhiVersion = "3.0.2"
  val quartzVersion = "2.1.1.wso2v1"
  val playJsonVersion = "2.5.9"
  Seq(
    "org.wso2.siddhi" % "siddhi-core" % siddhiVersion,
    "org.wso2.siddhi" % "siddhi-query-api" % siddhiVersion,
    "org.wso2.siddhi" % "siddhi-query-compiler" % siddhiVersion,
    "org.quartz-scheduler.wso2" % "quartz" % quartzVersion,
    "com.typesafe.play" % "play-json_2.11" % playJsonVersion
  )
}

packAutoSettings
packResourceDir += (baseDirectory.value / "conf" -> "conf")