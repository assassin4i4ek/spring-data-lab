package ua.kpi.its.lab.data

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ua.kpi.its.lab.data.config.Config
import ua.kpi.its.lab.data.entity.Battery
import ua.kpi.its.lab.data.entity.Vehicle
import ua.kpi.its.lab.data.svc.VehicleService
import java.math.BigDecimal
import java.util.Calendar

fun randomBattery(calendar: Calendar): Battery {
    val models = arrayOf("battery_model_1", "battery_model_1")
    val manufacturers = arrayOf("battery_manufacturer_1", "battery_manufacturer_2")
    val types = arrayOf("battery_type_1", "battery_type_2")
    val capacities = arrayOf(123, 321)
    val manufactureDate = calendar.apply { set(2020, 0, 1, 0,0, 0) }.time
    val chargeTimes = arrayOf(10.0, 20.0)
    val fastCharges = arrayOf(true, false)

    return Battery(
        models.random(), manufacturers.random(), types.random(), capacities.random(),
        manufactureDate, chargeTimes.random(), fastCharges.random()
    )
}

fun randomVehicle(calendar: Calendar, battery: Battery? = null): Vehicle {
    val brands = arrayOf("car_brand_1", "car_brand_2")
    val models = arrayOf("car_model_1", "car_model_1")
    val manufacturers = arrayOf("car_manufacturer_1", "car_manufacturer_2")
    val manufactureDate = calendar.apply { set(2010, 0, 1, 0, 0, 0) }.time
    val maxSpeeds = arrayOf(150.0, 200.0, 220.0)
    val prices = arrayOf(BigDecimal("7500.00"), BigDecimal("15000.00"), BigDecimal("25000.00"))
    val abs = arrayOf(true, false)

    return Vehicle(
        brands.random(), models.random(), manufacturers.random(), manufactureDate, maxSpeeds.random(),
        prices.random(), abs.random(), battery ?: randomBattery(calendar)
    )
}


fun main() {
    val context = AnnotationConfigApplicationContext(Config::class.java)
    val service = context.getBean(VehicleService::class.java)
    val calendar = context.getBean(Calendar::class.java)

    (1..5).forEach { _ ->
        val vehicle = randomVehicle(calendar)
        service.create(vehicle)
    }

// get instance with index = 3
    val vehicle3 = service.readByIndex(3)
    println("Retrieved $vehicle3")
    // remove instance with index = 3
    val vehicle4 = service.deleteByIndex(4)
    println("Removed $vehicle4")
}