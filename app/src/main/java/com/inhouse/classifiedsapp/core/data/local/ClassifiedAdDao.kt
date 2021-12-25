package com.inhouse.classifiedsapp.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.inhouse.classifiedsapp.core.model.ClassifiedAd
import kotlinx.coroutines.flow.Flow

@Dao
interface ClassifiedAdDao {
    /**
     * Inserts [classifiedAds] into the [ClassifiedAd.TABLE_NAME] table.
     * @param classifiedAds List of ClassifiedAd
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllClassifiedAds(classifiedAds: List<ClassifiedAd>)

    /**
     * Fetches all the classifiedAds from the [Match.TABLE_NAME] table.
     * @return [Flow]
     */
    @Query("SELECT * FROM ${ClassifiedAd.TABLE_NAME}")
    fun getAllClassifiedAds(): Flow<List<ClassifiedAd>>

    @Query("SELECT * FROM ${ClassifiedAd.TABLE_NAME} WHERE uid = :uid")
    fun getClassifiedAdById(uid: String): LiveData<ClassifiedAd>
}