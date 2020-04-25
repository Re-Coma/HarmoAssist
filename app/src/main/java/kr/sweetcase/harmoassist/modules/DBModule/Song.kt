package com.example.test

class Song {
    //곡 정보를 불러와 저장하기 위한 클래스
    var titleList= mutableListOf<TitleDBHandler>()

    var measureNum:Int =0

    var sheetInfo: SheetDBHandler= SheetDBHandler()

    var trackOneharmonicLineList= mutableListOf<HarmonicLineDBHandler>()
    var trackTwoharmonicLineList= mutableListOf<HarmonicLineDBHandler>()

    var trackOneNoteList= mutableListOf<NoteDBHandler>()
    var trackTwoNoteList= mutableListOf<NoteDBHandler>()

    var trackOnechordList= mutableListOf<OneChordDBHandler>()
    var trackTwochordList= mutableListOf<OneChordDBHandler>()
}