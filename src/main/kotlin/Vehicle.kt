import java.util.*

data class Vehicle(val plate: String, val type: VehicleType, var checkInTime: Calendar, val discountCard: String? = null){

    // Function states that two Vehicles are equal if their plates are equal.
    override fun equals(other: Any? ): Boolean{
        if(other is Vehicle){
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    // Function states that the hashCode (used internally in search functions in sets and arrays),
    // is the hashCode of the plate.
    override fun hashCode(): Int = this.plate.hashCode()
}