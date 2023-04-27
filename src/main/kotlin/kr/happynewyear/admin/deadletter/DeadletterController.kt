package kr.happynewyear.admin.deadletter

import kr.happynewyear.library.messaging.consumer.deadletter.DeadletterService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin/deadletters")
class DeadletterController(
    private val deadletterService: DeadletterService
) {

    @PostMapping("/requeue")
    fun requeue(@RequestParam id: String) {
        deadletterService.requeue(id)
    }

}
