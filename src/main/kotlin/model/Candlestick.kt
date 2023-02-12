package model

import java.time.Instant

typealias Price = Double

data class Candlestick(
    val openTimestamp: Instant,
    var closeTimestamp: Instant,
    val openPrice: Price,
    var highPrice: Price,
    var lowPrice: Price,
    var closingPrice: Price
)