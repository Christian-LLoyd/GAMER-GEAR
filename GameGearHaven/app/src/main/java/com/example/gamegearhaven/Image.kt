package com.example.gamegearhaven


import android.os.Parcelable

import kotlinx.parcelize.Parcelize


@Parcelize
class Image (
    val imageSrc: Int,
    val imageTitle: String,
    val imageDesc: String,
    val total : String,
) : Parcelable