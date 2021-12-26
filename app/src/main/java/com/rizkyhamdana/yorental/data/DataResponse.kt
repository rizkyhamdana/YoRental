package com.rizkyhamdana.yorental.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataResponse(
    var nama: String = " ",
    var biaya: Int = 0,
    var desc: String = " ",
    var jenis: String = "",
    var gambar: String = ""
): Parcelable
