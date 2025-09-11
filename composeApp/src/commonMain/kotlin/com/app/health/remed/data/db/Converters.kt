import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.collections.emptyList

class Converters {
    @TypeConverter
    fun fromListLong(times: List<Long>): String {
        return times.joinToString(",")
    }

    @TypeConverter
    fun toListLong(data: String): List<Long> {
        if (data.isEmpty()) return emptyList()
        return data.split(",").map { it.toLong() }
    }
}