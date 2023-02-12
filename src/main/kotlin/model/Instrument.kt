package model

import org.jetbrains.exposed.dao.id.IntIdTable


data class InstrumentEvent(val type: Type, val data: Instrument) {
    enum class Type {
        ADD,
        DELETE
    }
}

data class Instrument(val isin: ISIN, val description: String)
typealias ISIN = String



object Instruments: IntIdTable() {
    val isin = varchar("isin", 100).uniqueIndex()
    val description = varchar("description", 255)
}