import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import androidx.room.Upsert
import com.app.health.remed.data.DoseWithMedicine
import com.app.health.remed.data.entity.DoseEntity
import com.app.health.remed.utils.DoseStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate


@Dao
interface DoseDao {

    @Upsert
    suspend fun upsertDose(dose: DoseEntity)

    @Upsert
    suspend fun upsertDoses(doses: List<DoseEntity>)

    @Query("SELECT * FROM doses WHERE date = :date LIMIT 1")
    suspend fun getAnyDoseForDate(date: Long): DoseEntity?

    @Query("SELECT * FROM doses WHERE medicineId = :medicineId ORDER BY date ASC, time ASC")
    fun getDosesForMedicine(medicineId: Long) : Flow<List<DoseEntity>>

    @Transaction
    @Query("SELECT * FROM medicines")
    fun getDosesWithMedicineForToday() : Flow<List<DoseWithMedicine>>
}