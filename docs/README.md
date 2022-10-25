# Model 구현

---

## 현재 회차의 각 자동차 전진/멈춤 여부를 도출한다.
1. Car
    * 랜덤번호를 받게 되면 자동차 전진/멈춤 여부 도출
2. Cars
    * 자동차 이름목록, 반복횟수, 랜덤객체를 받아 각 자동차별 매회차 전진상태 누적
3. Result
    * 자동차 목록(Map<String, List<CarStatus>> cars) 을  
      각 회차별 누적된 결과(Map<String, String> round) 로 집계,  
      누적된 회차 결과(List<Map<String, String>> roundResults)를 저장
    * 누적된 회차 결과의 가장 마지막 회차(getRoundResults.get(lastRoundIndex))의 결과로  
      우승자 목록(List<String> winners) 도출
4. RandomNumberGenerator
    * Car 에 1의자리 랜덤번호(0~9) 생성

# View 구현

---

## 입력
1. InputView
    * 콘솔 화면 입력 값 저장: names, finalRoundIndex
    * 입력 값 유효성체크