name := "connect4scala"

scalaVersion := "2.10.5"

javacOptions ++= Seq("-source", "1.7", "-target", "1.7")

// protocol buffer support
// seq(sbtprotobuf.ProtobufPlugin.protobufSettings: _*)

// additional libraries
libraryDependencies ++= Seq(
)

resolvers ++= Seq(
)
