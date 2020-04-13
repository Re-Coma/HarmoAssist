package kr.sweetcase.harmoassist.modules.DBModule

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper
import kr.sweetcase.harmoassist.modules.DBModule.DBMaterials.*

class DBhandler(context: Context) :SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION){

    // DB Key
    companion object{
        private val DB_NAME="data"
        private val DB_VERSION=1

        public val FILEINFO_TABLE="FileInfo"
        public val SHEET_TABLE="Sheet"

        public val FIRST_MEASURE_TABLE="FirstMeasure"
        public val FIRST_HAMONICLINE_TABLE="FirstHamonicLine"
        public val FIRST_NOTE_TABLE="FirstNote"
        public val FIRST_ONECHORD_TABLE="FirstOneChord"
        public val LOG_TABLE="Log"

        /*private val SECOND_MEASURE_TABLE="SecondMeasure"
        private val SECOND_HAMONICLINE_TABLE="SecondHamonicLine"
        private val SECOND_NOTE_TABLE="SecondNote"
        private val SECOND_ONECHORD_TABLE="SecondOneChord"*/

        public val TITLE="Title"
        public val SUMMARY="Summary"

        public val HAMONIC="Hamonic"
        public val TEMPO="Tempo"
        public val TEMPO_STYLE="TempoStyle"
        public val TIME_SIGNATURE="TimeSignature"

        public val MEASURE_INDEX="MeasureIndex"
        public val TRACK_INDEX="TrackIndex"

        public val CHORD="Chord"
        public val CHORD_STYLE="ChordStyle"

        public val NOTE_INDEX="NoteIndex"
        public val NOTE_STYLE="NoteStyle"
        public val DURATION="Duration"

        public val PITCH="Pitch"
        public val ONECHORD_INDEX="OneChordIndex"
        public val OCTAVE="Octave"

        public val LOG_INDEX="LogIndex"
        public val EVENT="Event"
        public val DETAIL="Detail"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //db!!.execSQL("DROP TABLE IF EXISTS $FILEINFO_TABLE")

        /** TODO 여기에서 해당 데이터베이스가 존재하는 지 확인 하고 데이터베이스 상태가 정상인 지를 확인
         * 비정상일 경우 이에 대한 전처리 수행 필요 **/

        val createFileinfoTable =
            ("CREATE TABLE $FILEINFO_TABLE ($TITLE TEXT PRIMARY KEY,$SUMMARY TEXT)")
        val createSheetTable =
            ("CREATE TABLE $SHEET_TABLE ($TITLE TEXT PRIMARY KEY, $HAMONIC TEXT, $TEMPO TEXT, $TIME_SIGNATURE TEXT, $TEMPO_STYLE TEXT)")

        val createFirstMeasureTable =//("CREATE TABLE $FIRST_MEASURE_TABLE ($TRACK_INDEX INT, $MEASURE_INDEX INT PRIMARY KEY, $TITLE TEXT)")
            ("CREATE TABLE $FIRST_MEASURE_TABLE ($TRACK_INDEX INT, $MEASURE_INDEX INT, $TITLE TEXT, PRIMARY KEY($TRACK_INDEX, $MEASURE_INDEX, $TITLE))")
        val createFirstHamonicLineTable =
            ("CREATE TABLE $FIRST_HAMONICLINE_TABLE ($TRACK_INDEX INT, $MEASURE_INDEX INT, $TITLE TEXT, $CHORD TEXT, $CHORD_STYLE INT, PRIMARY KEY($TRACK_INDEX, $MEASURE_INDEX, $TITLE))")
        val createFirstNoteTable =
            ("CREATE TABLE $FIRST_NOTE_TABLE ($TRACK_INDEX INT, $MEASURE_INDEX INT, $NOTE_INDEX INT, $TITLE TEXT, $NOTE_STYLE INT, $DURATION INT, PRIMARY KEY($TRACK_INDEX, $MEASURE_INDEX, $TITLE, $NOTE_INDEX))")
        val creteFirstOneChordTable =
            ("CREATE TABLE $FIRST_ONECHORD_TABLE ($TRACK_INDEX INT, $MEASURE_INDEX INT, $NOTE_INDEX INT, $PITCH TEXT, $TITLE TEXT, $OCTAVE TEXT, PRIMARY KEY($TRACK_INDEX, $MEASURE_INDEX, $TITLE, $NOTE_INDEX))")

        val createLogTable =
            ("CREATE TABLE $LOG_TABLE ($LOG_INDEX INT PRIMARY KEY, $EVENT INT, $DETAIL TEXT)")

        //val createSecondMeasureTable = ("CREATE TABLE $FILEINFO_TABLE ($FILE_NAME TEXT PRIMARY KEY, $SUMMARY TEXT)")

        db!!.execSQL(createFileinfoTable)
        db!!.execSQL(createSheetTable)
        db!!.execSQL(createFirstMeasureTable)
        db!!.execSQL(createFirstHamonicLineTable)
        db!!.execSQL(createFirstNoteTable)
        db!!.execSQL(creteFirstOneChordTable)

        db!!.execSQL(createLogTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /*db!!.execSQL("DROP TABLE IF EXISTS $FILEINFO_TABLE")
        onCreate(db!!)*/
    }

    // fun createFirstTable(db: SQLiteDatabase) {
    //al createTable =("CREATE TABLE $FILEINFO_TABLE ($TITLE TEXT PRIMARY KEY, $SUMMARY TEXT)")
    //db?.execSQL(createTable)
    //}

    // 파일 데이터 추가
    fun addInfo(file: FileInfo): Boolean{
        //val handler=dBhandler
        val db=this.writableDatabase
        val values = ContentValues()

        values.put(TITLE, file.title)
        values.put(SUMMARY, file.summary)

        val _success =db.insert(FILEINFO_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }

    // TODO ??
    fun addSheetData(file: FileInfo): Boolean{
        //val handler=dBhandler
        val db=this.writableDatabase
        val values = ContentValues()

        values.put(TITLE, file.title)
        values.put(SUMMARY, file.summary)

        val _success =db.insert(SHEET_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }

    // 마디 데이터 추가
    // TODO 트랙이 두개이기 때문에 트랙 0,1번 두개에 추가
    fun addMeasureData(file: FileInfo): Boolean{
        //val handler=dBhandler
        val db=this.writableDatabase
        val values = ContentValues()

        values.put(TITLE, file.title)
        values.put(SUMMARY, file.summary)

        val _success =db.insert(FIRST_MEASURE_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }

    // TODO 반주 부분 추가
    fun addHamonicLineData(file: FileInfo): Boolean{
        //val handler=dBhandler
        val db=this.writableDatabase
        val values = ContentValues()

        values.put(TITLE, file.title)
        values.put(SUMMARY, file.summary)

        val _success =db.insert(FIRST_HAMONICLINE_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }

    fun addNoteData(file: FileInfo): Boolean{
        //val handler=dBhandler
        val db=this.writableDatabase
        val values = ContentValues()

        values.put(TITLE, file.title)
        values.put(SUMMARY, file.summary)

        val _success =db.insert(FIRST_NOTE_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }
    fun addOneChordData(file: FileInfo): Boolean{
        //val handler=dBhandler
        val db=this.writableDatabase
        val values = ContentValues()

        values.put(TITLE, file.title)
        values.put(SUMMARY, file.summary)

        val _success =db.insert(FIRST_ONECHORD_TABLE, null, values)
        db.close()

        return (Integer.parseInt("$_success")==1)
        //return true
    }

    fun addSheet(file: FileInfo): Boolean{
        val db=this.writableDatabase
        val values = ContentValues()

        return true
    }

    fun addMeasure(file: FileInfo): Boolean{
        val db=this.writableDatabase
        val values = ContentValues()

        db.close()

        return true
    }

    fun addHamonicLine(file: FileInfo): Boolean{
        val db=this.writableDatabase
        val values = ContentValues()

        db.close()

        return true
    }

    fun addNote(file: FileInfo): Boolean{
        val db=this.writableDatabase
        val values = ContentValues()

        db.close()

        return true
    }

    fun addOneChord(file: FileInfo): Boolean{
        val db=this.writableDatabase
        val values = ContentValues()

        db.close()

        return true
    }

    fun getAllFileInfo(): String{
        var allFile: String=""
        val db=readableDatabase
        val selectALLQuery=("SELECT * FROM $FILEINFO_TABLE")
        val cursor=db.rawQuery(selectALLQuery,null)
        var title: String=""
        var summary=""

        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    title=cursor.getString(cursor.getColumnIndex(TITLE))
                    summary=cursor.getString(cursor.getColumnIndex(SUMMARY))

                    allFile="$allFile \n $title $summary"
                }while(cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        //sleep(10000000000000000)

        return allFile
    }

    fun deleteFile(){
        val db=this.writableDatabase
        val values1 = ContentValues()
        val values2 = ContentValues()
        val values3 = ContentValues()

        values1.put(TITLE, "file1")
        values1.put(SUMMARY, "summary1")

        db.insert(FILEINFO_TABLE, null, values1)

        values2.put(TITLE, "file2")
        values2.put(SUMMARY, "summary2")

        db.insert(FILEINFO_TABLE, null, values2)

        values3.put(TITLE, "file3")
        values3.put(SUMMARY, "summary3")

        db.insert(FILEINFO_TABLE, null, values3)

        val values4 = ContentValues()

        values4.put(TRACK_INDEX, 1)
        values4.put(MEASURE_INDEX, 1)
        values4.put(TITLE, "title1")
        //=("INSERT INTO VALUES "+DBhandler.FIRST_MEASURE_TABLE+" ("+measure.title+","+measure.trackIndex+","+measure.MeasureIndex)

        db.insert(FIRST_MEASURE_TABLE, null, values4)

        db!!.delete(FIRST_MEASURE_TABLE,"title=? AND MeasureIndex=?", arrayOf("title1", "1"))
        //db!!.close()
        //db.close()
        /*db.delete("OneChord","title=?", arrayOf(delTitle))
        db.delete("Note","title=?", arrayOf(delTitle))
        db.delete("HarmonicLine","title=?", arrayOf(delTitle))
        db.delete("Measure","title=?", arrayOf(delTitle))
        db.delete("Sheet","title=?", arrayOf(delTitle))
        db.delete("FileInfo","title=?", arrayOf(delTitle))*/
        //val sql=("DELETE FROM FileInfo WHERE Title='file1'AND Summary='summary1'")
        //db!!.execSQL(sql)
        db!!.delete("FileInfo","title=? AND summary=?", arrayOf("file1","summary1"))

        var mea1: Measure =Measure()

        val values5 = ContentValues()
        val values6 = ContentValues()
        val values7 = ContentValues()

        values5.put(TITLE, "title1")
        values5.put(TRACK_INDEX, 1)
        values5.put(MEASURE_INDEX, 1)

        values6.put(TITLE, "title1")
        values6.put(TRACK_INDEX, 1)
        values6.put(MEASURE_INDEX, 2)

        values7.put(TITLE, "title1")
        values7.put(TRACK_INDEX, 1)
        values7.put(MEASURE_INDEX, 3)

        db.insert(FIRST_MEASURE_TABLE, null, values5)
        db.insert(FIRST_MEASURE_TABLE, null, values6)
        db.insert(FIRST_MEASURE_TABLE, null, values7)

        mea1.addMeasure(db,"title1", 1)

        mea1.delMeasure(db, "title1", 1, 2)
        /*mea1.addMeasure(db,"title1", 1)
        mea1.addMeasure(db,"title1", 1)
        mea1.addMeasure(db,"title1", 1)
        mea1.addMeasure(db,"title1", 1)
        mea1.addMeasure(db,"title1", 2)
        mea1.addMeasure(db,"title1", 2)
        mea1.addMeasure(db,"title1", 2)
        mea1.addMeasure(db,"title1", 2)
        mea1.addMeasure(db,"title1", 2)
        mea1.addMeasure(db,"title1", 2)*/



        db!!.close()
    }

    fun testdata(){
        val db=this.writableDatabase
        val e=FileInfo()
        val a=Measure()
        val b= HarmonicLine()
        val c= Note()
        val d= OneChord()
        val logm=LogModule()

        e.testdata(db)
        a.testdata(db)
        b.testdata(db)
        c.testdata(db)
        d.testdata(db)

        logm.testdata(db)
    }

    fun testLog(){
        val db=this.writableDatabase
        val logModule=LogModule()

        logModule.logCheck(db)

        db.close()
    }

    fun check(): List<String>{
        val db=this.writableDatabase
        val logModule=LogModule()

        val list=logModule.check(db)
        //logModule.check(db)

        return list
    }


}