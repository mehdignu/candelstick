package dao

import model.Candlestick

interface DAOFacade {
     fun getCandlesticks(isin: String): List<Candlestick>
     fun getLast30MinutesCandleSticks(isin: String):List<Candlestick>
}