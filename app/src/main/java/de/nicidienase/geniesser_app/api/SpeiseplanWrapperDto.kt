package de.nicidienase.geniesser_app.api

data class SpeiseplanWrapperDto(
    var speiseplanAdvanced: SpeiseplanAdvancedDto,
    var speiseplanGerichtData: List<SpeiseplanGerichtDto>
)