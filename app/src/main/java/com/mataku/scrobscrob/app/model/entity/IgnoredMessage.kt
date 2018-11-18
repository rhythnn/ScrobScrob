package com.mataku.scrobscrob.app.model.entity

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class IgnoredMessage(
    val code: String,

    @Json(name = "#text")
    val text: String
)