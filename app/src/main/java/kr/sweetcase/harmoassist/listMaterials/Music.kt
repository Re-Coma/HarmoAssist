package kr.sweetcase.harmoassist.listMaterials

/**
 * 음악 정보
 * titile : 제목
 * content : 설명
 * chord : 화음
 * tempo : 템포
 * timeSiganture : 박자표
 *
 * 이들은 차후에 백엔드를 포함시킬 때 타입을 다시 적용할 예정
 */
data class Music(
    val title: String,
    var summary:String,
    val chord : String,
    val tempo : Int,
    val timeSignature : String)