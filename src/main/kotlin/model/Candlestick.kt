package model

import java.time.Instant

typealias Price = Double

interface CandlestickManager {
    fun getCandlesticks(isin: String): List<Candlestick>
}

data class Candlestick(
    val openTimestamp: Instant,
    var closeTimestamp: Instant,
    val openPrice: Price,
    var highPrice: Price,
    var lowPrice: Price,
    var closingPrice: Price
)