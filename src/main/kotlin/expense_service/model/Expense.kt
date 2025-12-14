package com.example.expense.model

import jakarta.persistence.*

@Entity
@Table(name = "expenses")
class Expense(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val vehicleId: Long,

    @Column(nullable = false)
    val vehiclePlate: String,

    @Column(nullable = false)
    val category: String,

    @Column(nullable = false)
    val type: String,

    @Column(nullable = false)
    val date: String, // dd/MM/yyyy

    @Column(nullable = false)
    val amount: Int, // CLP

    @Column
    val km: Int? = null,

    @Column(length = 500)
    val notes: String? = null
)
