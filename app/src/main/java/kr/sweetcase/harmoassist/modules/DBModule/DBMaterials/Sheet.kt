package kr.sweetcase.harmoassist.modules.DBModule.DBMaterials

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import kr.sweetcase.harmoassist.modules.DBModule.DBhandler

class Sheet {

    var title:String=""
    var harmonic:String=""
    var tempo:Int=0
    var timeSignature:String=""
    var tempoStyle:String=""

    fun addSheet(db: SQLiteDatabase, sheet: Sheet): Boolean{
        //val handler=dBhandler
        //val db=this.writableDatabase
        val values = ContentValues()

        values.put(DBhandler.TITLE, sheet.title)
        values.put(DBhandler.HAMONIC, sheet.harmonic)
        values.put(DBhandler.TEMPO, sheet.tempo)
        values.put(DBhandler.TIME_SIGNATURE, sheet.timeSignature)
        values.put(DBhandler.TEMPO_STYLE, sheet.tempoStyle)

        val _success =db.insert(DBhandler.SHEET_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }

    fun deleteAllSheetByTitle(delTitle: String, db: SQLiteDatabase){
        db!!.delete(DBhandler.SHEET_TABLE,"title=?", arrayOf(delTitle))
        //db.delete("Sheet","title=?", arrayOf(delTitle))
    }
}