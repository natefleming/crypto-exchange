import sbt._

object Dependencies {

  val scalaLoggingVersion = "2.1.2"
  val scalaTestVersion = "2.2.5"
  val xchangeVersion = "4.3.2"
  val kafkaVersion = "1.0.0"
  val scoptVersion = "3.3.0"

  val libs = Seq(
    "org.knowm.xchange" % "xchange-core" % xchangeVersion,
    "org.knowm.xchange" % "xchange-gdax" % xchangeVersion,
    "org.knowm.xchange" % "xchange-bitstamp" % xchangeVersion,
    "org.knowm.xchange" % "xchange-btctrade" % xchangeVersion,
    "org.knowm.xchange" % "xchange-wex" % xchangeVersion,
    "org.knowm.xchange" % "xchange-binance" % xchangeVersion,
    "org.knowm.xchange" % "xchange-okcoin" % xchangeVersion,
    "org.knowm.xchange" % "xchange-poloniex" % xchangeVersion,
    "org.knowm.xchange" % "xchange-bitfinex" % xchangeVersion,
    "org.knowm.xchange" % "xchange-gemini" % xchangeVersion,
    "org.knowm.xchange" % "xchange-bitflyer" % xchangeVersion,
    "org.knowm.xchange" % "xchange-empoex" % xchangeVersion,
    "org.knowm.xchange" % "xchange-ripple" % xchangeVersion,
    "org.knowm.xchange" % "xchange-livecoin" % xchangeVersion,
    "org.knowm.xchange" % "xchange-kraken" % xchangeVersion,
    "org.knowm.xchange" % "xchange-blockchain" % xchangeVersion,
    "org.apache.kafka" % "kafka-clients" % "1.0.0",
    "org.apache.kafka" %% "kafka" % "1.0.0",
    "com.google.code.gson" % "gson" % "2.8.2",
    "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
    "com.github.scopt" %% "scopt" % scoptVersion,
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % scalaLoggingVersion,
    "org.slf4j" % "slf4j-api" % "1.7.12",
    "ch.qos.logback" % "logback-classic" % "1.1.3")

  val resolvers = Seq(
    Resolver.sonatypeRepo("public"))
}
