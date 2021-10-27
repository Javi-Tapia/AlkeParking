//    Ejercicio 1
//    Consigna: Responder: ¿Por qué se define vehicles como un Set ?
//    Answer: Because in a Set, it doesn't matter the order of elements, it's easier to retrieve an element, and
//    it hasn't repeated elements.

fun main() {
    val parking = Parking(mutableSetOf())

    // Could we use a scope fucntion to don't call VehiclesInstances a lot of times?
    val vehicles = mutableListOf(VehiclesInstances.car,VehiclesInstances.motorcycle,VehiclesInstances.minibus,
        VehiclesInstances.bus,VehiclesInstances.car2,VehiclesInstances.motorcycle2,VehiclesInstances.minibus2,
        VehiclesInstances.bus2,VehiclesInstances.car3,VehiclesInstances.motorcycle3,VehiclesInstances.minibus3,
        VehiclesInstances.bus3,VehiclesInstances.car4,VehiclesInstances.motorcycle4,VehiclesInstances.minibus4,
        VehiclesInstances.bus4,VehiclesInstances.car5,VehiclesInstances.motorcycle5,VehiclesInstances.minibus5,
        VehiclesInstances.bus5)

    for (vehicle in vehicles) {
        ParkingSpace(vehicle.plate, vehicle, parking).checkInVehicle(vehicle)
    }

    // Ugly code...
    ParkingSpace(VehiclesInstances.car.plate, VehiclesInstances.car, parking)
        .checkOutVehicle(VehiclesInstances.car.plate, ::onSuccess, ::onError)

    parking.getCheckoutsAndEarnings()

    parking.listVehicles()
}

fun onSuccess(fee: Int) = println("Your fee is $fee. Come back soon.")

fun onError() = println("Sorry, the check-out failed")

