import org.knowm.xchange.dto.marketdata.Ticker
import com.google.gson.Gson

object TickerImplicits {
  implicit class implicits(ticker: Ticker) {
    def toJson(): String = {
      return new Gson().toJson(ticker)
    }
  }

}