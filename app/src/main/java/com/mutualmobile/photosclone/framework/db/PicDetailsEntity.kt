package com.mutualmobile.photosclone.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mutualmobile.photosclone.domain.PicDetails

// Entity Class for the table in the Database
@Entity(tableName = "pic_tb")
data class PicDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val path: String,
    val uploaded: Boolean
)

fun PicDetails.toEntity(): PicDetailsEntity {
    return PicDetailsEntity(
        this.id,
        this.path,
        this.uploaded
    )
}

fun PicDetailsEntity.toDetails(): PicDetails {
    return PicDetails(
        this.id,
        this.path,
        this.uploaded
    )
}
