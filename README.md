# Twosome_Kiosk

---
<br/>

- 2024-2학기 자바 프로그래밍 수업: 순수 자바 프로젝트 2인1조 ~ 3인1조
- 수행 기간: 2024.11 ~ 2024.12 (2개월)
- 사용 언어: Java
- IDE: IntelliJ IDEA
- SDK: Amazon corretto 22.0.2
- Build Tool: Maven

---
<br/>

## 키오스크 데모 실행

https://github.com/user-attachments/assets/e403eed2-5eb8-4a1b-9884-c5bb9bd6e151

---
<br/>

## 카페 키오스크 형태의 Java GUI 프로그램
> Twosome 플레이스 키오스크를 참고하여 구현하였습니다.
<br/>

## ☕ 프로젝트 개요
본 프로젝트는 Java의 GUI 라이브러리 Swing과 AWT를 활용하여 제작된 키오스크 시뮬레이터입니다. <br/>
사용자는 메인 화면에서 입장 방법을 선택한 후, 다양한 메뉴 카테고리(커피, 음료, 디저트, 샌드위치/샐러드)에서 원하는 메뉴를 선택하여 주문하고, 결제를 진행할 수 있습니다. <br/>

또한, 월별로 자동 변경되는 이벤트 배너를 상단에 노출하여 마케팅 요소도 시뮬레이션합니다. <br/>
<br/>

## Swing & AWT
- `Swing`은 Java에서 GUI 애플리케이션을 만들기 위한 라이브러리입니다. 버튼, 패널, 레이블 등 다양한 컴포넌트를 제공합니다.
- `AWT`는 더 낮은 수준의 GUI를 구성하는 라이브러리이며, Swing의 기반이 됩니다.

본 프로젝트에서는 아래와 같은 구성에 사용되었습니다:
- `JPanel`, `JLabel`, `JButton`, `JScrollPane` 등으로 UI 구성
- `BoxLayout`, `GridLayout`, `BorderLayout`, `GridBagLayout` 등 다양한 레이아웃을 혼합 사용
- `MouseListener`, `ActionListener` 등 이벤트 리스너를 활용한 사용자 인터랙션 처리
<br/>

## 🖥 주요 기능 요약
### ✅ 1. 메인 화면 (MainPanel)
- 브랜드 로고 및 `Eat Here`, `To Go` 버튼 제공
- 상단에는 월별 자동 변경 이벤트 배너 텍스트가 슬라이딩 형태로 표시됨
  → `NoticeDialog.getRandomNotice()`를 통해 문구가 일정 시간마다 변경됨

### ✅ 2. 메뉴 선택 화면 (MenuPanel)
- 카테고리별 버튼을 통해 메뉴를 필터링 가능 (Coffee, Beverage, Dessert, Sandwich/Salad)
- 각 메뉴 항목은 이미지, 이름, 가격을 포함하며 클릭 시 주문 추가 가능
- 주문 항목은 수량 조절(+/-) 및 삭제(x) 가능
- `Total Price`가 하단에 표시되며, Payment 버튼을 통해 결제 창으로 이동

### ✅ 3. 월별 이벤트 이미지 (AdvertiseManager)
- 현재 월에 따라 자동으로 배너 이미지 경로가 변경됨
  → 예: 1월은 event01.jpg, 12월은 event12.jpg 등

---
<br/>

## 👨‍💻 담당 역할
> 해당 프로젝트에서 아래와 같은 화면 및 기능 구현을 담당하였습니다:

### 1. MenuPanel (메뉴 선택 화면)
- 카테고리(커피, 음료, 디저트, 샌드위치/샐러드) 클릭 시 해당 메뉴 목록 갱신
- 메뉴 아이템 클릭 시 주문 다이얼로그(`OrderDialog`) 호출
- 주문 리스트 구성 및 수량 조절(+, -) 기능 구현
- `OrderManager`와 연동하여 주문 내역 저장 및 전체 가격 계산 처리
- `Payment` 버튼 클릭 시 결제 다이얼로그로 이동

### 2. 주요 다이얼로그(Dialog)
✅ `OrderDialog`
- 사용자가 선택한 상품에 대해 수량 지정 및 확인 가능
- 확인 시 MenuPanel의 주문 내역에 해당 상품이 반영됨

✅ `PaymentDialog`
- 주문 완료 후 결제 처리 화면
- 결제 확인 후 ReceiptDialog로 이동

✅ `ReceiptDialog` (영수증 출력)
- 주문 내역과 총 금액을 보여주는 영수증 형태의 창 구성

### 3. 패키지 구조화


