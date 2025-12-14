package com.example.expense.service

import org.springframework.stereotype.Service
import com.example.expense.repository.ExpenseRepository
import com.example.expense.dto.*
import com.example.expense.model.Expense

@Service
class ExpenseService(
    private val repository: ExpenseRepository
) {

    fun create(request: ExpenseRequest): ExpenseResponse {
        val expense = repository.save(
            Expense(
                vehicleId = request.vehicleId,
                vehiclePlate = request.vehiclePlate,
                category = request.category,
                type = request.type,
                date = request.date,
                amount = request.amount,
                km = request.km,
                notes = request.notes
            )
        )
        return expense.toResponse()
    }

    fun listByVehicle(vehicleId: Long): List<ExpenseResponse> =
        repository.findByVehicleId(vehicleId).map { it.toResponse() }

    fun update(id: Long, request: ExpenseRequest): ExpenseResponse {
        val expense = repository.findById(id)
            .orElseThrow { RuntimeException("Expense not found") }

        val updated = repository.save(
            Expense(
                id = expense.id,
                vehicleId = request.vehicleId,
                vehiclePlate = request.vehiclePlate,
                category = request.category,
                type = request.type,
                date = request.date,
                amount = request.amount,
                km = request.km,
                notes = request.notes
            )
        )
        return updated.toResponse()
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }

    private fun Expense.toResponse() = ExpenseResponse(
        id = id,
        vehicleId = vehicleId,
        vehiclePlate = vehiclePlate,
        category = category,
        type = type,
        date = date,
        amount = amount,
        km = km,
        notes = notes
    )
}
