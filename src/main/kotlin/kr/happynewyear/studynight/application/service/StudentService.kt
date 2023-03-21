package kr.happynewyear.studynight.application.service

import kr.happynewyear.studynight.application.dto.StudentResult
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class StudentService(

) {

    fun create(): StudentResult {
        TODO("impl")
    }

}
