package com.skutracker.viewmodel

import com.skutracker.model.SKUItem
import com.skutracker.data.AppDatabase
import com.skutracker.logic.CashReturnCalculator
import com.skutracker.logic.InventoryTransactionEngine

class InventoryViewModel {
    private val database = AppDatabase()
    private val cashCalculator = CashReturnCalculator()
    private val transactionEngine = InventoryTransactionEngine()

    // Fetch all current inventory items
    fun getSkuList(): List<SKUItem> {
        return database.getAllSkus()
    }

    // Add a brand new SKU to the tracker
    fun addNewSku(
        id: String,
        name: String,
        costPricePerUnit: Double,
        sellingPricePerUnit: Double,
        unitsPerBox: Int,
        initialBoxes: Int,
        monthlyUnitsSold: Int
    ) {
        val totalPieces = transactionEngine.convertBoxesToPieces(initialBoxes, unitsPerBox)
        val capital = totalPieces * costPricePerUnit

        val newItem = SKUItem(
            id = id,
            name = name,
            costPricePerUnit = costPricePerUnit,
            sellingPricePerUnit = sellingPricePerUnit,
            unitsPerBox = unitsPerBox,
            totalPiecesStock = totalPieces,
            monthlyUnitsSold = monthlyUnitsSold,
            capitalInvested = capital
        )
        database.addSku(newItem)
    }

    // Get the core metric: Cash Return per 1,000 RS per month
    fun getCashReturnPer1000(item: SKUItem): Double {
        return cashCalculator.calculateCashReturnPer1000(item)
    }

    // Check if an item is a slow mover
    fun checkIsSlowMover(item: SKUItem): Boolean {
        return cashCalculator.isSlowMoving(item)
    }

    // Process a stock delivery (boxes converted to pieces automatically)
    fun restockSku(skuId: String, boxesReceived: Int) {
        val items = database.getAllSkus()
        val targetItem = items.find { it.id == skuId } ?: return
        
        val updatedItem = transactionEngine.processStockIn(targetItem, boxesReceived)
        // Update database logic here as needed
    }
}