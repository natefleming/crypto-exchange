lazy val commonSettings = Seq(
  organization := "org.lazydog",
  version := "0.1",
  scalaVersion := "2.11.7"
)

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(name := "lazydog-trading").
  settings(libraryDependencies ++= Dependencies.libs)
//  settings(resolvers ++= Dependencies.resolvers)

mergeStrategy in assembly <<= (mergeStrategy in assembly) { 
	(old) => {
    	case PathList("META-INF", xs @ _*) => MergeStrategy.discard
    	case x => MergeStrategy.first
	}
}
