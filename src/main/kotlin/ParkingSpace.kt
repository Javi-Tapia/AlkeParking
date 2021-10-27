import java.util.*
import kotlin.math.ceil

const val MINUTES_IN_MILISECONDS = 60000
const val BASE_TIME_IN_MILISECONDS = 7200000
const val FRACTION_TIME_IN_MILISECONDS = 900000
const val FRACTION_FEE = 5

data class ParkingSpace(var plate: String?, var vehicle: Vehicle, var parking: Parking){
    private val parkedTime: Long
        get() = (Calendar.getInstance().timeInMillis - vehicle.checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

    fun checkInVehicle(vehicle: Vehicle) {
        val vehicleAdded = parking.addVehicle(vehicle)
        if (vehicleAdded) println("Welcome to AlkeParking!") else println("Sorry, the chek-in failed")
    }

    fun checkOutVehicle(plate: String, onSuccess: (Int) -> Unit, onError: () -> Unit) {
        val finalFee: Int
        if (parking.vehicles.contains(vehicle)) {
            finalFee = calculateFee(vehicle.type, parkedTime.toInt(), vehicle.discountCard.isNullOrEmpty())
            parking.vehicles.remove(vehicle)
            parking.updateRegister(finalFee)
            onSuccess(finalFee)
        } else {
            onError
        }
    }

    private fun calculateFee(type: VehicleType, parkedTime: Int, hasNotDiscountCard: Boolean): Int {
        val finalFee: Int = if (parkedTime <= BASE_TIME_IN_MILISECONDS) {
            type.fee
        } else {
            // Get extra time and divide it by blocks of predefined fractions. Then, round up it and multiply it by
            // a predefined fee.
            val extraTime = ceil(((parkedTime - BASE_TIME_IN_MILISECONDS) / FRACTION_TIME_IN_MILISECONDS).toDouble()) * FRACTION_FEE
            type.fee + extraTime.toInt()
        }
        // If the vehicle has a discount card, it applies the discount.
        if (!hasNotDiscountCard) {
            return (finalFee * 0.85).toInt()
        }
        return finalFee
    }
}