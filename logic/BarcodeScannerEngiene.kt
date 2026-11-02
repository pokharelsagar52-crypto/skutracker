package com.skutracker.logic

import com.skutracker.model.SKUItem

class BarcodeScannerEngine {

    /**
     * Looks up an SKU from the active inventory list using a scanned barcode/ID string.
     */
    fun lookupSkuByBarcode(scannedBarcode: String, inventory: List<SKUItem>): SKUItem? {
        return inventory.find { it.id.equals(scannedBarcode, ignoreCase = true) }
    }

    /**
     * Generates standard dropdown action options for a scanned item.
     */
    fun getAvailableActions(item: SKUItem?): List<String> {
        if (item == null) {
            return listOf("ERROR: SKU Not Found", "Register New Barcode")
        }
        return listOf(
            "1. Stock In (Add Boxes/Pieces)",
            "2. Stock Out (Record Sale)",
            "3. View Cash Return per 1,000 RS",
            "4. Edit Pricing/Multipliers"
        )
    }
}