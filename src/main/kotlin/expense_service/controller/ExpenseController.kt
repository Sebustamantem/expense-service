package com.example.expense.controller

import com.example.expense.dto.ExpenseRequest
import com.example.expense.dto.ExpenseResponse
import com.example.expense.service.ExpenseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expenses")
class ExpenseController(
    private val service: ExpenseService
) {

    // CREATE
    @PostMapping
    fun create(@RequestBody request: ExpenseRequest): ResponseEntity<ExpenseResponse> {
        val created = service.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(created)
    }

    // LIST BY VEHICLE
    @GetMapping("/vehicle/{vehicleId}")
    fun listByVehicle(@PathVariable vehicleId: Long): ResponseEntity<List<ExpenseResponse>> {
        return ResponseEntity.ok(service.listByVehicle(vehicleId))
    }

    // GET BY ID (opcional, pero útil)
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ExpenseResponse> {
        return ResponseEntity.ok(service.getById(id))
    }

    // UPDATE
    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody request: ExpenseRequest
    ): ResponseEntity<ExpenseResponse> {
        return ResponseEntity.ok(service.update(id, request))
    }

    // DELETE
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}
