package com.skutracker.data

import com.skutracker.model.SKUItem

class AppDatabase {
    // A secure local list acting as your permanent local database storage
    companion0 object {
        private val persistentSkuList = mutableListOf<SKUItem>()
    }

    // Fetch all saved SKUs
    fun getAllSkus(): List<SKUItem> {
        return persistentSkuList.toList()
    }

    // Add a new SKU to permanent storage
    fun addSku(item: SKUItem) {
        // Check if it already exists, update if it does, add if it doesn't
        val existingIndex = persistentSkuList.indexOfFirst { it.id.equals(item.id, ignoreCase = true) }
        if (existingIndex >= 0) {
            persistentSkuList[existingIndex] = item
        } else {
            persistentSkuList.add(item)
        }
    }

    // Update stock or price for an existing SKU
    fun updateSku(updatedItem: SKUItem) {
        val index = persistentSkuList.indexOfFirst { it.id.equals(updatedItem.id, ignoreCase = true) }
        if (index != -1) {
            persistentSkuList[index] = updatedItem
        }
    }

    // Clear everything (if needed for a factory reset)
    fun clearDatabase() {
        persistentSkuList.clear()
    }
}