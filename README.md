# 專案簡介 
Path-Finder 是一個旨在幫助打工仔、學生及轉行人士探索自我、提升職業技能的一站式平台。針對現代社會變化快速、資訊過載的痛點，本平台提供職業技能提升課程及相關職位資訊，協助用戶重新出發，尋找真正適合自己的道路。  

# 技術棧 (Tech Stack)
本專案採用以下核心技術構建：前端 (Frontend): React 後端 (Backend): Spring Boot 資料庫 (Database): MySQL

# 畫面預覽
<img width="2238" height="2668" alt="Home" src="https://github.com/user-attachments/assets/ab1cab41-5663-4283-b377-86a82ec58a23" />

<img width="1536" height="2772" alt="Cart" src="https://github.com/user-attachments/assets/0dfc8e51-a7ed-475f-842b-78f607699b5e" />
<img width="1536" height="8540" alt="Network" src="https://github.com/user-attachments/assets/e087ed9f-d957-454f-9746-3f2a5644364d" />



# 專案架構
專案採用 MVC (Model-View-Controller) 設計模式，將系統劃分為三個部分，以提高程式的可管理性與維護性：Model: 負責資料處理與商業邏輯（如資料庫操作、使用者資料、計算規則）。View: 負責使用者介面展示（如網頁佈局、按鈕與互動元素）。Controller: 負責協調中間邏輯，接收用戶請求並調用對應的 Model 處理資料，最後將結果返回給 View。  

# 主要功能用戶系統: 
- 支援註冊、登入及忘記密碼功能。
- 課程瀏覽: 提供課程瀏覽、分類排序，並可檢視詳細課程資訊（含導師介紹與學員評價）。
- 購物體驗: 完整的購物車流程，包含項目增減、總計計算、結帳確認及歷史訂單查詢。
- 支付與整合: 整合多種支付方式（如 PayMe, Alipay, 信用卡），並在付款成功後更新訂單狀態。
- 職位關聯 (Jobs Referral): 會員在瀏覽課程時，可同時查看與該課程相關的工作空缺。

專案延伸規劃
 為了提升系統的擴展性與用戶體驗，未來將進行以下優化：
 - 後台管理系統 (Admin Dashboard): 讓管理員能管理商品庫存、審核用戶資料及進行數據分析。
 - 個人化推薦: 引入會員等級與積分制度，並透過演算法或 AI 提供個人化課程推薦。
 - 智慧客服: 引入聊天機械人 (Chatbot) 即時解答用戶疑問。
 - 營銷機制: 加入優惠券、限時折扣及推薦好友機制，以提升轉換率。  
