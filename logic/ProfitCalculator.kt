package com.skutracker.logic

import com.skutracker.model.SKUItem

class ProfitCalculator {
    // Calculates gross profit for a single unit
    fun calculateUnitProfit(item: SKUItem): Double {
        return item.sellingPrice - item.costPrice
    }

    // Calculates total potential profit for all stock on hand
    fun calculateTotalPotentialProfit(item: SKUItem): Double {
        return calculateUnitProfit(item) * item.stockCount
    }
}