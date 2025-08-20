## Bulletin Board 프로젝트 업데이트 내역

### 첫 커밋
- 서비스 출시
- 단순 CRUD 기능 구현
- 회원가입 등 기본 기능 구현

---

### 두 번째 커밋
- Exception 처리 고도화
- 기존에는 단순히 exception만 띄웠음
- 변경: GlobalExceptionHandler 도입
  - 사용자에게는 error 페이지를 안내
  - 내부적으로는 HttpServletRequest를 통해 오류 정보를 수집, 오류 내용 확인

---

### 세 번째 커밋
- error.html 파일의 div 태그 문법 오류 수정

---

### 네 번째 커밋
- GlobalExceptionHandler 수정
  - AuthorizationDeniedException을 처리할 수 있도록 메소드 추가
- TestDataInit 수정
  - Answer부분 paging 처리를 위해 테스트 데이터를 넣을 수 있도록 initLargeAnswerData 추가
