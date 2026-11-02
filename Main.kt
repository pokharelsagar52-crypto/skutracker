import com.skutracker.viewmodel.InventoryViewModel

fun main() {
    val viewModel = InventoryViewModel()
    
    // 1. Add an item
    viewModel.addNewSku(
        id = "SKU-WATER-01",
        name = "Mineral Water 1L Case",
        costPricePerUnit = 15.0,
        sellingPricePerUnit = 25.0,
        unitsPerBox = 12,
        initialBoxes = 20,
        monthlyUnitsSold = 60
    )

    println("=== DATABASE TEST ===")
    println("Total SKUs in Database: ${viewModel.getSkuList().size}")
    
    val item = viewModel.getSkuList().first()
    val cashReturn = viewModel.getCashReturnPer1000(item)
    
    println("Item Saved: ${item.name}")
    println("Cash Return: RS %.2f per 1,000 RS/mo".format(cashReturn))
}