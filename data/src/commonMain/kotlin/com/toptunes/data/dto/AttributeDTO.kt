package com.toptunes.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface AttributeDTO {

    @Serializable
    data class Image(
        val height: String
    ) : AttributeDTO

    @Serializable
    data class Id(
        @SerialName("im:id")
        val id: String
    ) : AttributeDTO

    @Serializable
    data class Category(
        val label: String
    ) : AttributeDTO
}