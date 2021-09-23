package com.example.pictureupload.framework.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pic_tb")
data class PicDetailsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val path: String,
    val uploaded: Boolean
)