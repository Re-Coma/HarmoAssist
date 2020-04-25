package com.example.test

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class SheetDBHandler {
    var harmonic: String=""
    var temp: Int=0
    var timeSignature: Int=0
    var tempoStyle: String=""

    //DB에 Sheet 정보를 추가하는 함수
    fun addSheet(db: SQLiteDatabase, title:String, harmonic: String, tempo: Int, timeSignature: String, tempoStyle: String){
        val values = ContentValues()    //추가할 정보들을 저장하기 위한 변수

        values.put(DBHandler.TITLE, title)
        values.put(DBHandler.HAMONIC, harmonic)
        values.put(DBHandler.TEMPO, tempo)
        values.put(DBHandler.TIME_SIGNATURE, timeSignature)
        values.put(DBHandler.TEMPO_STYLE, tempoStyle)

        db.insert(DBHandler.SHEET_TABLE, null, values)
    }

    //Sheet 테이블에서 해당하는 파일 이름을 가진 정보를 삭제하는 함수
    fun deleteAllSheetByTitle(delTitle: String, db: SQLiteDatabase){
        db!!.delete(DBHandler.SHEET_TABLE,"title=?", arrayOf(delTitle))
    }
}