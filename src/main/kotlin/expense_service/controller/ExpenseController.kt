package com.example.expense.controller

import com.example.expense.dto.ExpenseRequest
import com.example.expense.dto.ExpenseResponse
import com.example.expense.service.ExpenseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/expenses")
class ExpenseController(
    private val service: ExpenseService
) {

    // GET /expenses/vehicle/123
    @GetMapping("/vehicle/{vehicleId}")
    fun listByVehicle(@PathVariable vehicleId: Long): List<ExpenseResponse> =
        service.listByVehicle(vehicleId)

    // GET /expenses/1
    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ExpenseResponse =
        service.getById(id)

    // POST /expenses
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody req: ExpenseRequest): ExpenseResponse =
        service.create(req)

    // PUT /expenses/1
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody req: ExpenseRequest): ExpenseResponse =
        service.update(id, req)

    // DELETE /expenses/1
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}
