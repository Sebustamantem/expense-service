package com.example.expense.controller

import com.example.expense.dto.ExpenseRequest
import com.example.expense.dto.ExpenseResponse
import com.example.expense.service.ExpenseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/expenses")
class ExpenseController(
    private val service: ExpenseService
) {

    @PostMapping
    fun create(@RequestBody request: ExpenseRequest): ResponseEntity<ExpenseResponse> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(request))

    @GetMapping("/vehicle/{vehicleId}")
    fun listByVehicle(@PathVariable vehicleId: Long): ResponseEntity<List<ExpenseResponse>> =
        ResponseEntity.ok(service.listByVehicle(vehicleId))

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ExpenseResponse> =
        ResponseEntity.ok(service.getById(id))

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: ExpenseRequest
    ): ResponseEntity<ExpenseResponse> =
        ResponseEntity.ok(service.update(id, request))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
