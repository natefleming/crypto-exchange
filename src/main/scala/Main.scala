
import com.typesafe.scalalogging.slf4j.LazyLogging
import org.knowm.xchange.Exchange
import org.knowm.xchange.ExchangeFactory
import org.knowm.xchange.bitstamp.BitstampExchange
import org.knowm.xchange.service.marketdata.MarketDataService
import org.knowm.xchange.currency.CurrencyPair
import org.knowm.xchange.gdax.GDAXExchange
import org.knowm.xchange.kraken.KrakenExchange
import org.knowm.xchange.gemini.v1.GeminiExchange
import org.knowm.xchange.livecoin.LivecoinExchange
import org.knowm.xchange.okcoin.OkCoinExchange

object Main extends LazyLogging {

  def main(args: Array[String]) {

    var bitstamp = ExchangeFactory.INSTANCE.createExchange(classOf[BitstampExchange])
    var gdax = ExchangeFactory.INSTANCE.createExchange(classOf[GDAXExchange])
    var kracken = ExchangeFactory.INSTANCE.createExchange(classOf[KrakenExchange])
    var gemini = ExchangeFactory.INSTANCE.createExchange(classOf[GeminiExchange])
    var livecoin = ExchangeFactory.INSTANCE.createExchange(classOf[LivecoinExchange])
    var okcoin = ExchangeFactory.INSTANCE.createExchange(classOf[OkCoinExchange])

    printTicker(bitstamp)
    printTicker(gdax)
    printTicker(kracken)
    printTicker(gemini)
    printTicker(livecoin)
    printTicker(okcoin)

  }

  def printTicker(exchange: Exchange) {
    var marketDataService = exchange.getMarketDataService()
    var ticker = marketDataService.getTicker(CurrencyPair.BTC_USD)
    logger.debug(exchange.toString)
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

}
