package kr.happynewyear.bff.web

import kr.happynewyear.api.studynight.controller.InvitationController
import kr.happynewyear.api.studynight.controller.MatchController
import kr.happynewyear.api.studynight.controller.StudentController
import kr.happynewyear.api.studynight.controller.StudyController
import kr.happynewyear.api.studynight.dto.MatchCreateRequest
import kr.happynewyear.api.studynight.dto.StudentCreateRequest
import kr.happynewyear.api.studynight.dto.StudyCreateRequest
import kr.happynewyear.api.studynight.dto.StudyResponse
import kr.happynewyear.bff.web.dto.dictionary.dictionary
import kr.happynewyear.bff.web.dto.element.ConditionElement
import kr.happynewyear.bff.web.dto.element.OptionElement
import kr.happynewyear.bff.web.dto.element.StudySummary
import kr.happynewyear.bff.web.dto.form.BookingCreateForm
import kr.happynewyear.bff.web.dto.form.StudentCreateForm
import kr.happynewyear.bff.web.dto.form.StudyCreateForm
import kr.happynewyear.bff.web.dto.view.BookingCreateResponseView
import kr.happynewyear.bff.web.dto.view.StudentExistView
import kr.happynewyear.bff.web.dto.view.StudyListView
import kr.happynewyear.bff.web.dto.view.StudyView
import kr.happynewyear.library.security.authentication.Authenticated
import kr.happynewyear.library.security.authentication.Principal
import kr.happynewyear.library.utility.Dates
import kr.happynewyear.studynight.constant.ContactType
import kr.happynewyear.studynight.constant.EngagementRole
import kr.happynewyear.studynight.constant.EngagementRole.*
import kr.happynewyear.studynight.constant.condition.*
import kr.happynewyear.studynight.constant.condition.ConditionKey.*
import kr.happynewyear.studynight.constant.condition.Experience.*
import kr.happynewyear.studynight.constant.condition.Intensity.*
import kr.happynewyear.studynight.constant.condition.Position.*
import kr.happynewyear.studynight.constant.condition.Region.*
import kr.happynewyear.studynight.constant.condition.Scale.*
import kr.happynewyear.studynight.constant.condition.Schedule.*
import kr.happynewyear.studynight.type.MatchParameter
import kr.happynewyear.studynight.type.MatchSource
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors.groupingBy
import java.util.stream.Collectors.toMap

@RestController
@RequestMapping("/bff-web")
class BffWebController(
    private val studentController: StudentController,
    private val studyController: StudyController,
    private val matchController: MatchController,
    private val invitationController: InvitationController
) {

    @GetMapping("/enum")
    fun enums(): List<ConditionElement> {
        val options = mapOf(
            Pair(SCHEDULE, Schedule.values()),
            Pair(REGION, Region.values()),
            Pair(EXPERIENCE, Experience.values()),
            Pair(POSITION, Position.values()),
            Pair(INTENSITY, Intensity.values()),
            Pair(SCALE, Scale.values()),
        )
        return ConditionKey.values().map {
            ConditionElement(
                it.name, dictionary[it.name]!!,
                options[it]!!.map { o -> OptionElement(o.name, dictionary[o.name]!!, false) })
        }
    }


    @GetMapping("/student/me/is_exist")
    fun isExistStudent(@Authenticated principal: Principal): StudentExistView {
        val response = studentController.isExist(principal).body!!
        return StudentExistView(
            response.isExist
        )
    }

    @PostMapping("/student")
    fun createStudent(@Authenticated principal: Principal, form: StudentCreateForm) {
        val condition = form.condition.stream()
            .collect(toMap({ it.code }, { it.options.filter { o -> o.check }.map { o -> o.code } }))
        val request = StudentCreateRequest(
            form.nickName,
            MatchSource(
                condition[SCHEDULE.name]!!.map { Schedule.valueOf(it) }.toSet(),
                condition[REGION.name]!!.map { Region.valueOf(it) }.toSet(),
                condition[EXPERIENCE.name]!!.map { Experience.valueOf(it) }.first(),
                condition[POSITION.name]!!.map { Position.valueOf(it) }.first(),
                condition[INTENSITY.name]!!.map { Intensity.valueOf(it) }.first(),
                condition[SCALE.name]!!.map { Scale.valueOf(it) }.first(),
            )
        )
        studentController.create(principal, request)
    }


    @PostMapping("/study")
    fun createStudy(@Authenticated principal: Principal, form: StudyCreateForm): String {
        val condition = form.condition.stream()
            .collect(toMap({ it.code }, { it.options.filter { o -> o.check }.map { o -> o.code } }))
        val request = StudyCreateRequest(
            form.title, form.introduction,
            ContactType.KAKAOTALK_OPENCHAT, form.contactURL,
            MatchParameter(
                condition[SCHEDULE.name]!!.map { Schedule.valueOf(it) }.first(),
                condition[REGION.name]!!.map { Region.valueOf(it) }.first(),
                condition[EXPERIENCE.name]!!.map { Experience.valueOf(it) }.toSet(),
                condition[POSITION.name]!!.map { Position.valueOf(it) }.toSet(),
                condition[INTENSITY.name]!!.map { Intensity.valueOf(it) }.first(),
                condition[SCALE.name]!!.map { Scale.valueOf(it) }.first(),
            )
        )
        val response = studyController.create(principal, request)
        return response.headers.location.toString().split("/").last()
    }

    @GetMapping("/study/{studyId}")
    fun getStudy(@Authenticated principal: Principal, @PathVariable studyId: UUID): StudyView {
        val response = studyController.get(studyId).body!!
        val condition = mutableListOf<String>()
        condition.add(response.condition.schedule.name)
        condition.add(response.condition.region.name)
        condition.addAll(response.condition.experiences.map { it.name })
        condition.addAll(response.condition.positions.map { it.name })
        condition.add(response.condition.intensity.name)
        condition.add(response.condition.scale.name)
        val role = roleOf(principal, response)
        return StudyView(
            response.title, response.description, response.contactAddress,
            condition.map { dictionary[it]!! },
            role?.name
        )
    }

    @GetMapping("/study/condition/{studyId}")
    fun getStudyCondition(@PathVariable studyId: UUID): List<ConditionElement> {
        val response = studyController.get(studyId).body!!
        val condition = response.condition
        val view = enums()
        view.forEach {
            val selected = when (ConditionKey.valueOf(it.code)) {
                SCHEDULE -> setOf(condition.schedule.name)
                REGION -> setOf(condition.region.name)
                EXPERIENCE -> condition.experiences.map { v -> v.name }.toSet()
                POSITION -> condition.positions.map { v -> v.name }.toSet()
                INTENSITY -> setOf(condition.intensity.name)
                SCALE -> setOf(condition.scale.name)
            }
            it.options.forEach { o -> o.check = selected.contains(o.code) }
        }
        return view
    }

    @GetMapping("/study/list")
    fun listStudy(@Authenticated principal: Principal): StudyListView {
        val response = studyController.list(principal).body!!
        val managingStudies = response.studies.stream()
            .collect(groupingBy { roleOf(principal, it) == MANAGER })
        val mapper: (StudyResponse) -> StudySummary = {
            StudySummary(
                it.title,
                listOf(it.condition.schedule, it.condition.region).map { c -> dictionary[c.name]!! },
                Dates.from(it.createdAt).time
            )
        }
        return StudyListView(
            managingStudies[true]?.map { mapper.invoke(it) } ?: emptyList(),
            managingStudies[false]?.map { mapper.invoke(it) } ?: emptyList()
        )
    }

    fun roleOf(principal: Principal, study: StudyResponse): EngagementRole? {
        val student = studentController.me(principal).body!!

        if (study.managers.map { it.id }.contains(student.id)) return MANAGER
        if (study.members.map { it.id }.contains(student.id)) return MEMBER
        if (study.guests.map { it.id }.contains(student.id)) return GUEST
        return null
    }


    @PostMapping("/booking")
    fun booking(@Authenticated principal: Principal, form: BookingCreateForm): BookingCreateResponseView {
        val condition = form.condition.stream()
            .collect(toMap({ it.code }, { it.options.filter { o -> o.check }.map { o -> o.code } }))
        val request = MatchCreateRequest(
            form.studyId,
            MatchParameter(
                condition[SCHEDULE.name]!!.map { Schedule.valueOf(it) }.first(),
                condition[REGION.name]!!.map { Region.valueOf(it) }.first(),
                condition[EXPERIENCE.name]!!.map { Experience.valueOf(it) }.toSet(),
                condition[POSITION.name]!!.map { Position.valueOf(it) }.toSet(),
                condition[INTENSITY.name]!!.map { Intensity.valueOf(it) }.first(),
                condition[SCALE.name]!!.map { Scale.valueOf(it) }.first(),
            )
        )
        val createResponse = matchController.create(request)
        val matchId = createResponse.headers.location.toString().split("/").last()
        val getResponse = matchController.get(UUID.fromString(matchId)).body!!
        return BookingCreateResponseView(
            getResponse.invitations.map { it.student.nickname }.first(),
            getResponse.invitations.count()
        )
    }

}
