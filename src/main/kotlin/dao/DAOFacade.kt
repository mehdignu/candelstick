package dao

import model.Candlestick

interface DAOFacade {
     fun getCandlesticks(isin: String): List<Candlestick>
}