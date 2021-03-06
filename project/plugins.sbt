/*
 * Copyright 2013 - 2017 Outworkers Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
resolvers ++= Seq(
  "jgit-repo" at "http://download.eclipse.org/jgit/maven",
  "Twitter Repo" at "http://maven.twttr.com/",
  Resolver.sonatypeRepo("releases"),
  Resolver.bintrayRepo("websudos", "oss-releases"),
  Resolver.url("scoverage-bintray", url("https://dl.bintray.com/sksamuel/sbt-plugins/"))(Resolver.ivyStylePatterns),
  Resolver.url("bintray-csl-sbt-plugins", url("https://dl.bintray.com/twittercsl/sbt-plugins"))(Resolver.mavenStylePatterns),
  Resolver.url("twitter-csl-sbt-plugins", url("https://dl.bintray.com/twittercsl/sbt-plugins"))(Resolver.ivyStylePatterns)
)

lazy val scalaTravisEnv = sys.env.get("TRAVIS_SCALA_VERSION")
def isScala210: Boolean = scalaTravisEnv.exists("2.10.6" ==)
lazy val isCi = sys.env.get("CI").exists("true" == )

lazy val Versions = new {
  val scrooge = if (isCi) {
    if (sys.props("java.specification.version") == "1.8" && !isScala210) "4.14.0" else "4.7.0"
  } else {
    if (sys.props("java.specification.version") == "1.8") "4.14.0" else "4.7.0"
  }
}

addSbtPlugin("org.scoverage" %% "sbt-scoverage" % "1.5.0")

addSbtPlugin("org.scoverage" %% "sbt-coveralls" % "1.1.0")

addSbtPlugin("com.jsuereth" % "sbt-pgp" % "1.0.0")

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")

if (sys.env.get("MAVEN_PUBLISH").exists("true" ==)) {
  addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "1.1")
} else {
  addSbtPlugin("me.lessis" % "bintray-sbt" % "0.3.0")
}

addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.7.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.8.5")

addSbtPlugin("com.websudos" % "sbt-package-dist" % "1.2.0")

addSbtPlugin("com.twitter" % "scrooge-sbt-plugin" % Versions.scrooge)

addSbtPlugin("com.eed3si9n" % "sbt-doge" % "0.1.5")

addSbtPlugin("org.tpolecat" % "tut-plugin" % "0.5.2")

libraryDependencies += "org.slf4j" % "slf4j-nop" % "1.7.22"