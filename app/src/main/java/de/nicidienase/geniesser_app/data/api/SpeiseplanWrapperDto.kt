package de.nicidienase.geniesser_app.data.api

data class SpeiseplanWrapperDto(
    var speiseplanAdvanced: SpeiseplanAdvancedDto,
    var speiseplanGerichtData: List<SpeiseplanGerichtDto>
)