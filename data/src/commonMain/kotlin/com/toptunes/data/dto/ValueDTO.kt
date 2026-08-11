package com.toptunes.data.dto

import kotlinx.serialization.Serializable

@Serializable
sealed interface ValueDTO {

    @Serializable
    data class Id(
        val label: String,
        val attributes: AttributeDTO.Id
    ) : ValueDTO

    @Serializable
    data class Text(
        val label: String
    ) : ValueDTO

    @Serializable
    data class Image(
        val label: String,
        val attributes: AttributeDTO.Image
    ) : ValueDTO

    @Serializable
    data class Category(
        val attributes: AttributeDTO.Category
    ) : ValueDTO
}