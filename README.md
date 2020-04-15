# HarmoAssist
작곡 입문자를 위한  AI 작곡 어플리케이션 (2020 한국산업기술대학교 졸업작품 x SweetCase PROJECT)
작곡을 처음 하는 사람들을 위한 작곡 악보 어플리케이션
* * *
* * *
## Specification
* **Android Client(Kotlin)**
    * android midi library
    * mididriver library
    * SQLite
    * Lettuce(Android-Redis-API)
* **Redis Server**
    * Redis 5.0.8
* **DeepLearning Server(Python)**
    * Tensorflow 2.1 + Keras
    * Magenta Melody Model
    * Magenta Music DataSet
* **SNS WebServer**(EXTENDED)
    * DJango(Python, HTML, JavaScript)
    * Music21
    * MariaDB
    * Redis(TimeLine)
## __ .DEVELOPERS. __
* **Score Interface**: 최정환
* **UI/UX**: 이동호
* **Back End**: 조현민
* **Music Composition/Analysis, DeepLearning Server**: 하정현(In SweetCase)
* * *
## 기능
* 악보를 활용한 수동 작곡 기능
* 상황에 따라 적잘하게 화음을 배치해 주는 화음 배치 기능
* 장르나 박자, 노트 갯수를 설정하면 이에 따른 AI작곡 기능
* 화성학에 기반한 화음배치 기능 설명 및 코드에 따른 듣기 기능
* 현재 작곡을 하고 있는 것에 대한 곡 분석 기능
* 넓은 화면을 사용하는 사용자를 위한 태블릿 지원
* 옵션으로 미디 키보드를 이용한 원활한 작곡 가능
## 개발 가능성이 있는 확장 기능
* SNS 커뮤니케이션(Only Android)
    * 자신이 작곡한 내용을 업로드, 정보수정, 삭제 가능
    * 다른 사람이 작곡한 내용을 기호에 따라 조회 가능(타임라인 추가)
    * 조회수, 좋아요, 댓글 기능, 이를 이용해 주간, 월간 좋아요, 조회수가 가장 많은 곡 조회 가능.
    * 안드로이드 기기로 바로 다운 받고 활용 가능
    * 바로 재생 기능 사용
* * *
* * *
# Systems
### 전체 구성도
* !커뮤니티 서버는 확장구현이 확정될 때 구현 예정
![main image](https://hjhr-readme.s3.ap-northeast-2.amazonaws.com/sysstructfinal.png)
* * *
* * *


