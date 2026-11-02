package com.skutracker.logic

import com.skutracker.model.SKUItem

class InventoryTransactionEngine {

    // Converts incoming boxes/cartons into individual units (pieces)
    fun convertBoxesToPieces(boxes: Int, unitsPerBox: Int): Int {
        return boxes * unitsPerBox
    }

    /**
     * Handles stock-in via barcode scan or manual entry.
     * Takes existing item, adds newly received boxes converted to pieces, and updates capital.
     */
    fun processStockIn(item: SKUItem, boxesReceived: Int): SKUItem {
        val additionalPieces = convertBoxesToPieces(boxesReceived, item.unitsPerBox)
        val newTotalPieces = item.totalPiecesStock + additionalPieces
        val additionalCapital = boxesReceived * item.unitsPerBox * item.costPricePerUnit
        
        return item.copy(
            totalPiecesStock = newTotalPieces,
            capitalInvested = item.capitalInvested + additionalCapital
        )
    }

    /**
     * Handles stock-out (sales/outbound) via barcode scan.
     */
    fun processStockOut(item: SKUItem, piecesSold: Int): SKUItem {
        val updatedStock = (item.totalPiecesStock - piecesSold).coerceAtLeast(0)
        val updatedMonthlySales = item.monthlyUnitsSold + piecesSold

        return item.copy(
            totalPiecesStock = updatedStock,
            monthlyUnitsSold = updatedMonthlySales
        )
    }
}