package model

import java.time.LocalDateTime

typealias Price = Double

data class Candlestick(
    val openTimestamp: LocalDateTime,
    val openPrice: Price,
    var highPrice: Price,
    var lowPrice: Price,
    var closingPrice: Price,
    var closeTimestamp: LocalDateTime
)