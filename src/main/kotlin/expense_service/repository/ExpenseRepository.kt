package com.example.expense.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.example.expense.model.Expense

interface ExpenseRepository : JpaRepository<Expense, Long> {

    fun findByVehicleId(vehicleId: Long): List<Expense>
}
