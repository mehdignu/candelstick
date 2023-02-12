package model

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select


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

    private fun toInstrument(row: ResultRow): Instrument =
        Instrument(
            isin = row[Instruments.isin],
            description = row[Instruments.description]
        )
}