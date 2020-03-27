package kr.sweetcase.harmoassist

import android.os.Parcel
import android.os.Parcelable

/* 'name', 'speed', 'beat','chord','comment' 데이터를 가지는
    'Music' 이라는 이름의 새로운 Kotlin Class 파일을 생성
    */

class Music constructor(
    var name:String,var speed:String,
    var beat :String,var chord:String,var comment:String):Parcelable{

    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(speed)
        parcel.writeString(beat)
        parcel.writeString(chord)
        parcel.writeString(comment)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Music> {
        override fun createFromParcel(parcel: Parcel): Music {
            return Music(parcel)
        }

        override fun newArray(size: Int): Array<Music?> {
            return arrayOfNulls(size)
        }
    }

}