# redis-cache-exam

해당 Repo는 게시글 [**Spring boot에서 Redis Cache 사용하기**](https://deveric.tistory.com/98) 의 예제 코드입니다.<br>
Spring boot에서 Redis Cache를 설정하고 간단하게 테스트하는 튜토리얼 코드입니다.<br>

더 많은 게시글은 제 블로그 [DevEric](https://deveric.tistory.com/)을 참고해주세요.<br>


1) count 확인
http://192.168.35.101/count

2) count 올리기
http://localhost/?size=5     // 동일한 파라미터로 호출하면 count가 올라가지 않음

http://localhost/?size=6     // count 다시 확인
                             