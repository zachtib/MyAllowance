package com.zachtib.myallowance.models

//{
//    "category_groups": [
//    {
//        "id": "string",
//        "name": "string",
//        "hidden": true,
//        "deleted": true,
//        "categories": [
//
//        ]
//    }
//    ],
//    "server_knowledge": 0
//}

data class CategoryGroup(
    val id: String,
    val name: String,
    val hidden: Boolean,
    val deleted: Boolean,
    val categories: List<Category>
)