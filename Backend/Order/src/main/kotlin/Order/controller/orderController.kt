package Order.controller

import Order.DTO.Tracking
import Order.Message.Message
import Order.Model.order
import Order.Services.orderServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@CrossOrigin
@RestController
@RequestMapping
class orderController {

    @Autowired
    private lateinit var orderServices: orderServices

    @GetMapping("/tracking/{tid}")
    fun tracking(@PathVariable tid:String): ResponseEntity<Any>
    {
        try
        {
            //------------------------------------------ TODAY---------------------------------------
            val `in` = Date()
            val ldt: LocalDateTime = LocalDateTime.ofInstant(`in`.toInstant(), ZoneId.systemDefault())
            val Today = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant())

            //------------------------------------------START DATA-----------------------------------
            val info = orderServices.tracking(tid)
            val startdate = LocalDate.parse(info.shipdate)
            val defaultZoneId = ZoneId.systemDefault()
            val localDate = startdate
            val Startdate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant())

            //----------------------------------------Day Calculation----------------------------------
            val date1 = Startdate
            val date2 = Today

            val date1InMs = date1.time
            val date2InMs = date2.time

            var timeDiff: Long = 0
            timeDiff = if (date1InMs > date2InMs) {
                date1InMs - date2InMs
            } else {
                date2InMs - date1InMs
            }
            val daysDiff = (timeDiff / (1000 * 60 * 60 * 24)*2).toInt()


            println("Start Date : $Startdate")
            println("     Today : $Today")
            println("No of days diff is : $daysDiff")

            val tracking = Tracking()
            tracking.name = info.name
            tracking.progress = daysDiff
            tracking.city = info.city
            tracking.shipcity =info.shipcity
            tracking.state1 =info.state
            return ResponseEntity.status(200).body(tracking)
        }
        catch (e: Exception)
        {
            return ResponseEntity.status(401).body(Message("Incorrect TrackingID"))
        }
    }

    @PostMapping("/add")
    fun addorder(@RequestBody order: order): order
    {
        return orderServices.addorder(order)
    }

}