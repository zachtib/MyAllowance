package com.zachtib.myallowance.models

// Example:

//{
//    "data": {
//        "budgets": [
//            {
//                "id": "string",
//                "name": "string",
//                "last_modified_on": "2019-03-03T17:55:15.080Z",
//                "date_format": DateFormat
//                "currency_format": CurrencyFormat
//            }
//        ]
//    }
//}

data class Budget(
    val id: String,
    val name: String,
    val lastModifiedOn: String,
    val dateFormat: DateFormat,
    val currencyFormat: CurrencyFormat
)