package io.currency.bot.minfin.telegram.network.retrofit.converters

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Type
import retrofit2.Converter
import retrofit2.Retrofit

class EnumRetrofitConverterFactory : Converter.Factory() {

    override fun stringConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<*, String>? =
        if (type is Class<*> && type.isEnum) {
            null
//            Converter { enum ->
//                try {
//                    enum.javaClass.getField(enum.name).getAnnotation(SerializedName::class.java)?.value
//                } catch (e: NoSuchFieldException) {
//                    enumSerializationError(type)
//                } ?: enumSerializationError(type)
//            }
        } else {
            null
        }

    @Suppress("NOTHING_TO_INLINE")
    private inline fun enumSerializationError(type: Type?): Nothing = error("cannot serialize $type enum properly, please make sure it's annotated with @SerializedName")
}
