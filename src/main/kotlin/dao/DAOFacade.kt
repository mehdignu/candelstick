package dao

import model.Candlestick

interface DAOFacade {
    suspend fun getCandlesticks(isin: String): List<Candlestick>
    suspend fun getLast30MinutesCandleSticks(isin: String):List<Candlestick>
}