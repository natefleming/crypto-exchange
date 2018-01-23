
import java.util.Properties
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.knowm.xchange.Exchange
import org.knowm.xchange.ExchangeFactory
import org.knowm.xchange.bitstamp.BitstampExchange
import org.knowm.xchange.currency.CurrencyPair
import org.knowm.xchange.dto.marketdata.Ticker
import org.knowm.xchange.gdax.GDAXExchange
import org.knowm.xchange.gemini.v1.GeminiExchange
import org.knowm.xchange.kraken.KrakenExchange
import org.knowm.xchange.livecoin.LivecoinExchange
import org.knowm.xchange.okcoin.OkCoinExchange

import com.google.gson.Gson
import com.typesafe.scalalogging.slf4j.LazyLogging
import TickerImplicits._

object Main extends LazyLogging {

  case class CommandLine(brokers: String = "", topic: String = "")

  def main(args: Array[String]) {
    val parser = new scopt.OptionParser[CommandLine]("Main") {
      opt[String]("brokers").required().action {
        (x, c) => c.copy(brokers = x)
      } text ("A csv list of one or more Kafka brokers")
      opt[String]("topic").required().action {
        (x, c) => c.copy(topic = x)
      } text ("The kafka topic")
    }

    parser.parse(args, CommandLine()) match {
      case Some(commandLine) => main(commandLine)
      case None => System.exit(1)
    }
  }

  def main(commandLine: CommandLine) = {

    var exchangeClasses = Array(
      classOf[BitstampExchange],
      classOf[GDAXExchange],
      classOf[GDAXExchange],
      classOf[KrakenExchange],
      classOf[GeminiExchange],
      classOf[LivecoinExchange],
      classOf[OkCoinExchange])

    var index = new AtomicLong(0)
    var producer = getProducer(commandLine)
    var exchanges = exchangeClasses.map { ExchangeFactory.INSTANCE.createExchange(_) }
    var pool: ExecutorService = Executors.newFixedThreadPool(exchanges.size)
    var isRunning = true

    sys.addShutdownHook(() => {
      println("SHUTTING DOWN...")
      isRunning = false
      pool.shutdown
      producer.flush
      producer.close
    })

    exchanges.foreach { exchange =>
      pool.execute(new Runnable {
        def run {
          while (isRunning) {
            var ticker: Ticker = getTicker(exchange)
            printTicker(ticker)
          //  publish(producer, commandLine.topic, index.getAndIncrement.toString, ticker)
            TimeUnit.SECONDS.sleep(5)
          }
        }
      })
    }

  }

  def getTicker(exchange: Exchange): Ticker = {
    var marketDataService = exchange.getMarketDataService()
    var ticker = marketDataService.getTicker(CurrencyPair.BTC_USD)
    return ticker
  }

  def publish(producer: Producer[String, String], topic: String, index: String, ticker: Ticker) = {
    val record = new ProducerRecord[String, String](topic, index, ticker.toJson);
    val time = System.currentTimeMillis();
    val metadata = producer.send(record).get();
    val elapsedTime = System.currentTimeMillis() - time;
    printf(
      "sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
      record.key(),
      record.value(),
      metadata.partition(),
      metadata.offset(),
      elapsedTime)
  }

  def printTicker(ticker: Ticker) = {
    logger.debug("ask={}", ticker.getAsk)
    logger.debug("bid={}", ticker.getBid)
    logger.debug("currencyPair={}", ticker.getCurrencyPair)
    logger.debug("high={}", ticker.getHigh)
    logger.debug("last={}", ticker.getLast)
    logger.debug("low={}", ticker.getLow)
    logger.debug("open={}", ticker.getOpen)
    logger.debug("quoteVolume={}", ticker.getQuoteVolume)
    logger.debug("timestamp={}", ticker.getTimestamp)
    logger.debug("volume={}", ticker.getVolume)
    logger.debug("vwap={}", ticker.getVwap)

  }

  def getProducer(commandLine: CommandLine): Producer[String, String] = {
    val props = new Properties();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, commandLine.brokers);
    props.put(ProducerConfig.CLIENT_ID_CONFIG, "ExchangeTicker");
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer]);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer]);
    props.put(ProducerConfig.ACKS_CONFIG, "1");
    val producer = new KafkaProducer[String, String](props);
    return producer
  }

}
