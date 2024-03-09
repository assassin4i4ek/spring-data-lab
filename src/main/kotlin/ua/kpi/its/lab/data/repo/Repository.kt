package ua.kpi.its.lab.data.repo

import org.springframework.data.jpa.repository.JpaRepository
import ua.kpi.its.lab.data.entity.Battery
import ua.kpi.its.lab.data.entity.Vehicle

interface VehicleRepository : JpaRepository<Vehicle, Long> {

}

interface BatteryRepository : JpaRepository<Battery, Long> {

}