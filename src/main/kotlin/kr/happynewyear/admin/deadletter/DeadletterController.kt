package kr.happynewyear.admin.deadletter

import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterHandler
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/deadletters")
class DeadletterController(
    private val deadletterHandler: DeadletterHandler
) {

    @PostMapping("/requeue")
    fun requeue(@RequestParam id: String) {
        deadletterHandler.requeue(id)
    }

}
