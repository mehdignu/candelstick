package conf

import model.Instruments
import model.Quotes
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseConf {
    companion object{
        fun init(){
            Database.connect("jdbc:h2:mem:dev;DB_CLOSE_DELAY=-1;", "org.h2.Driver")
            transaction {
                addLogger(StdOutSqlLogger)
                SchemaUtils.create(Instruments)
                SchemaUtils.create(Quotes)
            }
        }
    }
}