package com.zachtib.myallowance.models

// Example:

//"currency_format": {
//    "iso_code": "string",
//    "example_format": "string",
//    "decimal_digits": 0,
//    "decimal_separator": "string",
//    "symbol_first": true,
//    "group_separator": "string",
//    "currency_symbol": "string",
//    "display_symbol": true
//}

data class CurrencyFormat(
    val isoCode: String,
    val exampleFormat: String,
    val decimalDigits: Int,
    val decimalSeparator: String,
    val symbolFirst: Boolean,
    val groupSeparator: String,
    val currencySymbol: String,
    val displaySymbol: Boolean
)