package com.skutracker.model

data class SKUItem(
    val id: String,
    val name: String,
    val costPricePerUnit: Double,
    val sellingPricePerUnit: Double,
    val unitsPerBox: Int,           // e.g., 24 pieces in a carton/box
    val totalPiecesStock: Int,      // Total inventory tracked in individual pieces
    val monthlyUnitsSold: Int,      // Velocity tracker for monthly movement
    val capitalInvested: Double     // Total capital tied up in this SKU
)