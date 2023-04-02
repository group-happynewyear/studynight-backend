package kr.happynewyear.studynight.domain.service

import kr.happynewyear.studynight.constant.EngagementRole
import kr.happynewyear.studynight.domain.model.Engagement
import kr.happynewyear.studynight.domain.model.Student
import kr.happynewyear.studynight.domain.model.Study

class EngagementRegistrationService {
    companion object {

        fun register(study: Study, student: Student, role: EngagementRole) {
            val engagement = Engagement.create(study, student, role)
            study.add(engagement)
            student.add(engagement)
        }

    }
}
