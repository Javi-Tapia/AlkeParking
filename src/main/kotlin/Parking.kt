data class Parking( val vehicles: MutableSet<Vehicle>){

    private val maxQuota: Int = 20
    private lateinit var vehiclesAndEarnings: Pair<Int, Int>
    private var checkOuts = 0
    private var totalEarnings = 0

    fun addVehicle(vehicle: Vehicle): Boolean {
        return if (vehicles.size < maxQuota) {
            vehicles.add(vehicle)
        } else {
            false
        }
    }

    // Checkouts will produce one by one
    fun updateRegister(earnings: Int) {
        checkOuts += 1
        totalEarnings += earnings
        vehiclesAndEarnings = Pair(checkOuts, totalEarnings)
    }

    fun getCheckoutsAndEarnings() {
        println("${vehiclesAndEarnings.first} vehicles have checked out and have earnings of \$${vehiclesAndEarnings.second}")
    }

    fun listVehicles() {
        vehicles.forEach { println(it.plate) }
    }
}