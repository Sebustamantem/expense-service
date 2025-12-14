package com.example.expense.dto

data class ExpenseRequest(
    val vehicleId: Long,
    val vehiclePlate: String,
    val category: String,
    val type: String,
    val date: String,
    val amount: Int,
    val km: Int?,
    val notes: String?
)
