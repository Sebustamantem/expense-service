package com.example.expense.service

import com.example.expense.dto.ExpenseRequest
import com.example.expense.dto.ExpenseResponse
import com.example.expense.model.Expense
import com.example.expense.repository.ExpenseRepository
import org.springframework.stereotype.Service

@Service
class ExpenseService(
    private val repository: ExpenseRepository
) {

    fun create(request: ExpenseRequest): ExpenseResponse {
        val saved = repository.save(
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
        return saved.toResponse()
    }

    fun listByVehicle(vehicleId: Long): List<ExpenseResponse> {
        return repository.findByVehicleId(vehicleId).map { it.toResponse() }
    }

    fun getById(id: Long): ExpenseResponse {
        val expense = repository.findById(id)
            .orElseThrow { RuntimeException("Expense not found") }
        return expense.toResponse()
    }

    fun update(id: Long, request: ExpenseRequest): ExpenseResponse {
        val existing = repository.findById(id)
            .orElseThrow { RuntimeException("Expense not found") }

        val saved = repository.save(
            Expense(
                id = existing.id,
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
        return saved.toResponse()
    }

    fun delete(id: Long) {
        // (opcional) validar existencia antes de borrar
        if (!repository.existsById(id)) {
            throw RuntimeException("Expense not found")
        }
        repository.deleteById(id)
    }

    // Mapper interno
    private fun Expense.toResponse(): ExpenseResponse = ExpenseResponse(
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
