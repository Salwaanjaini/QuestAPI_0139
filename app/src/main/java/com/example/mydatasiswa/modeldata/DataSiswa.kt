package com.example.mydatasiswa.modeldata

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.intOrNull

object StringOrIntSerializer : KSerializer<Int> {
    override val descriptor = PrimitiveSerialDescriptor("StringOrInt", PrimitiveKind.INT)

    override fun serialize(encoder: Encoder, value: Int) {
        encoder.encodeInt(value)
    }

    override fun deserialize(decoder: Decoder): Int {
        return if (decoder is JsonDecoder) {
            val element = decoder.decodeJsonElement()
            element.jsonPrimitive.intOrNull ?: element.jsonPrimitive.content.toIntOrNull() ?: 0
        } else {
            decoder.decodeInt()
        }
    }
}

@Serializable
data class DataSiswa(
    @Serializable(with = StringOrIntSerializer::class)
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)
