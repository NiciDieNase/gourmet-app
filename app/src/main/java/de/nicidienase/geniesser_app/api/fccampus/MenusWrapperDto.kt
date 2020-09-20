package de.nicidienase.geniesser_app.api.fccampus

import androidx.annotation.Keep

@Keep
data class MenusWrapperDto(
    var dayMenus: List<DayMenuDto>
)
