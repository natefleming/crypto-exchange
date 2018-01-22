import sbt._

object Dependencies {

  val scalaLoggingVersion = "2.1.2"
  val scalaTestVersion = "2.2.5"
  val xchangeVersion = "4.3.2"

  val libs = Seq(
    "org.knowm.xchange" % "xchange-core" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-gdax" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-bitstamp" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-btctrade" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-wex" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-binance" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-okcoin" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-poloniex" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-bitfinex" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-gemini" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-bitflyer" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-empoex" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-ripple" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-livecoin" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-kraken" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "org.knowm.xchange" % "xchange-blockchain" % xchangeVersion exclude ("javax.ws.rs", "javax.ws.rs-api"),
    "javax.ws.rs" % "javax.ws.rs-api" % "2.1",
    //"javax.ws.rs" % "javax.ws.rs-api" % "2.0.1",
    //		"javax.ws.rs" % "javax.ws.rs-api" % "2.1",
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % scalaLoggingVersion,
    "org.slf4j" % "slf4j-api" % "1.7.12",
    "ch.qos.logback" % "logback-classic" % "1.1.3")

  val resolvers = Seq(
    Resolver.sonatypeRepo("public"))
}
