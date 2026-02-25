package org.example.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: Long? = null,

    @SerialName("first_name")
    val nameFirst: String,

    @SerialName("middle_name")
    val nameMiddle: String?,

    @SerialName("last_name")
    val nameLast: String,

    val email: String,

    @SerialName("phone_number")
    val phoneNumber: String?,

    val address: String?
)