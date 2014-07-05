name:="prog_scala"

scalaVersion:="2.10.4"

version:="0.1"


libraryDependencies ++= Seq(
    "org.specs2" %% "specs2" % "2.3.12" % "test" 
  )

  scalacOptions in Test ++= Seq("-Yrangepos")

  // Read here for optional dependencies:
  // http://etorreborre.github.io/specs2/guide/org.specs2.guide.Runners.html#Dependencies

 resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)
