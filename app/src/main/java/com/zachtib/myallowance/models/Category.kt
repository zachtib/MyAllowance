package com.zachtib.myallowance.models

//{
//    "id": "string",
//    "category_group_id": "string",
//    "name": "string",
//    "hidden": true,
//    "original_category_group_id": "string",
//    "note": "string",
//    "budgeted": 0,
//    "activity": 0,
//    "balance": 0,
//    "goal_type": "TB",
//    "goal_creation_month": "string",
//    "goal_target": 0,
//    "goal_target_month": "string",
//    "goal_percentage_complete": 0,
//    "deleted": true
//}

data class Category(
    val id: String,
    val categoryGroupId: String,
    val name: String,
    val hidden: Boolean,
    val originalCategoryGroupId: String,
    val note: String,
    val budgeted: Int,
    val activity: Int,
    val balance: Int,
    val goalType: Int,
    val goalCreationMonth: String,
    val goalTarget: Int,
    val goalTargetMonth: String,
    val goalPercentageComplete: Int,
    val deleted: Boolean
)
