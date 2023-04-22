package kr.happynewyear.bff.web.dto.element

data class ConditionElement(
    val code: String,
    val name: String,
    val options: List<OptionElement>
)
