package com.skutracker.ui

import com.skutracker.model.SKUItem
import com.skutracker.viewmodel.InventoryViewModel

class InventoryScreen {
    private val viewModel = InventoryViewModel()

    fun renderDashboard() {
        println("==================================================")
        println("         S&N COLD STORE: CASH FLOW DASHBOARD      ")
        println("==================================================")
        
        val items = viewModel.getSkuList()
        
        if (items.isEmpty()) {
            println("[!] NO ITEMS FOUND. Tap 'Add New Item' to start.")
        } else {
            for (item in items) {
                val cashReturn = viewModel.getCashReturnPer1000(item)
                val isSlow = viewModel.checkIsSlowMover(item)
                
                val speedLabel = if (isSlow) "🔴 SLOW SELLER (Needs Attention)" else "🟢 FAST SELLER (Great Cash Flow)"
                
                println("\n📦 ITEM: ${item.name}")
                println("   • Pack Size: ${item.unitsPerBox} pieces per box")
                println("   • Current Stock: ${item.totalPiecesStock / item.unitsPerBox} Boxes (${item.totalPiecesStock} Total Pieces)")
                println("   • Status: $speedLabel")
                println("   • Money Returned: RS %.2f per 1,000 RS invested / month".format(cashReturn))
                println("--------------------------------------------------")
            }
        }
        println("\n[ QUICK ACTIONS ]")
        println(" [1] Scan Barcode  |  [2] Add Stock  |  [3] Record Sale")
        println("==================================================")
    }
}