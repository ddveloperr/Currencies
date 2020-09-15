package com.example.currencies.domain.api.gson

import com.example.currencies.domain.model.Currency
import com.example.currencies.domain.model.CurrencyRate
import com.example.currencies.domain.model.CurrenciesResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.math.BigDecimal

class CurrenciesResponseGsonConverter : JsonDeserializer<CurrenciesResponse> {
    companion object {
        private const val JSON_MEMBER_RATES = "rates"
        private const val JSON_MEMBER_BASE_CURRENCY = "baseCurrency"
    }

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CurrenciesResponse {
        val baseCurrency = json!!.asJsonObject.get(JSON_MEMBER_BASE_CURRENCY).asString
        val currencyRates = json.asJsonObject.get(JSON_MEMBER_RATES).asJsonObject.entrySet().map {
            Currency.valueOf(it.key) to it.value.asBigDecimal
        }.toMap()
        return CurrenciesResponse(baseCurrency, currencyRates)
    }
}