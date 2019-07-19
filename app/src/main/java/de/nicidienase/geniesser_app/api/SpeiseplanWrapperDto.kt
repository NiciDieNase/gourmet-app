package de.nicidienase.geniesser_app.api

import androidx.annotation.Keep

@Keep
data class SpeiseplanWrapperDto(
    var speiseplanAdvanced: SpeiseplanAdvancedDto,
    var speiseplanGerichtData: List<SpeiseplanGerichtDto>
)