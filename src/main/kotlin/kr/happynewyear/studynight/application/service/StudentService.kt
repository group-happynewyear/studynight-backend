package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudentResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional(readOnly = true)
class StudentService(

) {

    fun isExist(userId: UUID): Boolean {
        TODO("impl")
    }

    fun create(): StudentResult {
        TODO("impl")
    }

}
