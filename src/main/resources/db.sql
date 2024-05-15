CREATE DATABASE IF NOT EXISTS kioskdb;


USE kioskdb;


CREATE TABLE `user` (
	`user_idx` bigint primary key auto_increment,
	`user_id` varchar(255) NOT NULL unique,
	`user_pw` varchar(255) NOT NULL,
	`user_name` varchar(255) NOT NULL,
	`user_role` enum('ROLE_USER', 'ROLE_ADMIN') NOT NULL	DEFAULT 'ROLE_USER',
	`user_point` int	NOT NULL DEFAULT 0,
	`create_date`	datetime	NOT NULL	DEFAULT now(),
	`update_date`	datetime	NULL,
	`is_deleted`	boolean	NOT NULL	DEFAULT 0
);

DESC USER;

INSERT INTO USER VALUES (0, "admin", "admin", "admin", "ROLE_ADMIN", default, DEFAULT, null, DEFAULT);
INSERT INTO USER VALUES (0, "user1", "user1", "user1", "ROLE_USER", default, DEFAULT, null, DEFAULT);
INSERT INTO USER VALUES (0, "user2", "user2", "user2", "ROLE_USER", default, DEFAULT, null, DEFAULT);
INSERT INTO USER VALUES (0, "user3", "user3", "user3", "ROLE_USER", default, DEFAULT, null, DEFAULT);

SELECT * FROM USER;


--

CREATE TABLE `image` (
	`img_idx`	bigint primary key auto_increment,
	`img_name`	varchar(255)	NOT NULL,
	`img_url`	varchar(255)	NOT NULL
);

DESC IMAGE;

-- 단품 버거
INSERT INTO image VALUES (0, "더블 빅맥", "burger1.png");
INSERT INTO image VALUES (0, "더블 쿼트 파운드 치즈", "burger2.png");
INSERT INTO image VALUES (0, "더블 치즈", "burger3.png");
INSERT INTO image VALUES (0, "맥스파이스 상하이", "burger4.png");
INSERT INTO image VALUES (0, "불고기", "burger5.png");
INSERT INTO image VALUES (0, "슈슈", "burger6.png");
INSERT INTO image VALUES (0, "슈비", "burger7.png");
INSERT INTO image VALUES (0, "1995", "burger8.png");
INSERT INTO image VALUES (0, "맥치킨", "burger9.png");
INSERT INTO image VALUES (0, "에그 불고기", "burger10.png");
INSERT INTO image VALUES (0, "베이컨 토마토 디럭스", "burger11.png");
INSERT INTO image VALUES (0, "맥크리스피 디럭스", "burger12.png");

-- 세트 버거
INSERT INTO image VALUES (0, "더블 빅맥 세트", "burger_set1.png");
INSERT INTO image VALUES (0, "더블 쿼트 파운드 치즈 세트", "burger_set2.png");
INSERT INTO image VALUES (0, "더블 치즈 세트", "burger_set3.png");
INSERT INTO image VALUES (0, "맥스파이스 상하이 세트", "burger_set4.png");
INSERT INTO image VALUES (0, "불고기 세트", "burger_set5.png");
INSERT INTO image VALUES (0, "슈슈 세트", "burger_set6.png");
INSERT INTO image VALUES (0, "슈비 세트", "burger_set7.png");
INSERT INTO image VALUES (0, "1995 세트", "burger_set8.png");
INSERT INTO image VALUES (0, "맥치킨 세트", "burger_set9.png");
INSERT INTO image VALUES (0, "에그 불고기 세트", "burger_set10.png");
INSERT INTO image VALUES (0, "베이컨 토마토 디럭스 세트", "burger_set11.png");
INSERT INTO image VALUES (0, "맥크리스피 디럭스 세트", "burger_set12.png");

-- 디저티 앤 사이드

INSERT INTO image VALUES (0, "베리 스트로베리 맥플러리", "dessert1.png");
INSERT INTO image VALUES (0, "오레오 맥플러리", "dessert2.png");
INSERT INTO image VALUES (0, "초코 오레오 맥플러리", "dessert3.png");
INSERT INTO image VALUES (0, "스트로베리콘", "dessert4.png");
INSERT INTO image VALUES (0, "딸기 오레오 맥플러리", "dessert5.png");
INSERT INTO image VALUES (0, "아이스크림콘", "dessert6.png");
INSERT INTO image VALUES (0, "맥윙", "side1.png");
INSERT INTO image VALUES (0, "맥윙콤보", "side2.png");
INSERT INTO image VALUES (0, "코울슬로", "side3.png");
INSERT INTO image VALUES (0, "상하이 치킨 스냅랩", "side4.png");
INSERT INTO image VALUES (0, "골든 모짜렐라 치즈스틱", "side5.png");
INSERT INTO image VALUES (0, "후렌치 후라이", "side6.png");


-- 해피밀
INSERT INTO image VALUES (0, "베이컨 에그 맥머핀", "happymeal1.png");
INSERT INTO image VALUES (0, "소시지 에그 맥머핀", "happymeal2.png");
INSERT INTO image VALUES (0, "에그 맥머핀", "happymeal3.png");
INSERT INTO image VALUES (0, "핫케익", "happymeal4.png");
INSERT INTO image VALUES (0, "해쉬브라운", "happymeal5.png");
INSERT INTO image VALUES (0, "딸기 코코넛 푸딩", "happymeal6.png");

-- 커피 & 음료
INSERT INTO image VALUES (0, "자두 천도복숭아 칠러", "cafe1.png");
INSERT INTO image VALUES (0, "제주 한라봉 칠러", "cafe2.png");
INSERT INTO image VALUES (0, "핫 아메리카노", "cafe3.png");
INSERT INTO image VALUES (0, "아이스 아메리카노", "cafe4.png");
INSERT INTO image VALUES (0, "핫 카페라떼", "cafe5.png");
INSERT INTO image VALUES (0, "아이스 카페라떼", "cafe6.png");
INSERT INTO image VALUES (0, "핫 바닐라라떼", "cafe7.png");
INSERT INTO image VALUES (0, "아이스 바닐라라떼", "cafe8.png");

INSERT INTO image VALUES (0, "코카콜라", "drink1.png");
INSERT INTO image VALUES (0, "코카콜라 제로", "drink2.png");
INSERT INTO image VALUES (0, "스프라이트", "drink3.png");
INSERT INTO image VALUES (0, "환타", "drink4.png");
INSERT INTO image VALUES (0, "바닐라 쉐이크", "drink5.png");
INSERT INTO image VALUES (0, "딸기 쉐이크", "drink6.png");
INSERT INTO image VALUES (0, "초코 쉐이크", "drink7.png");
INSERT INTO image VALUES (0, "생수", "drink8.png");
INSERT INTO image VALUES (0, "오렌지 주스", "drink9.png");


SELECT * FROM image;

--



CREATE TABLE `category` (
	`category_idx`	bigint primary key	auto_increment,
	`category_title`	varchar(255)	NOT NULL
);

DESC category;

INSERT INTO category VALUES (0, "버거 단품");
INSERT INTO category VALUES (0, "버거 세트");
INSERT INTO category VALUES (0, "해피밀");
INSERT INTO category VALUES (0, "디저트&사이드");
INSERT INTO category VALUES (0, "음료&커피");

SELECT * FROM CATEGORY;


--


CREATE TABLE `menu` (
	`menu_idx` bigint	primary key auto_increment,
	`category_idx`	bigint NOT NULL,
	`img_idx` bigint NOT NULL,
	`menu_name`	varchar(255)	NOT NULL,
	`menu_price`	int	NOT NULL,
	`menu_calory`	int	NOT NULL,
	`menu_code`	varchar(255)	NOT NULL	unique,
	`menu_recommend`	boolean	NOT NULL,
	`create_date`	datetime	NOT NULL	DEFAULT now(),
	`update_date`	datetime	NULL,
	`is_deleted`	boolean	NOT NULL DEFAULT 0,
	foreign key (category_idx) references category(category_idx)
	on update CASCADE,
	foreign key (img_idx) references image(img_idx)
	on update cascade
);

-- 버거 단품
INSERT INTO menu VALUES (0, 1, 1, "더블 빅맥 버거", 8300, 802, "1eeb7765-2ad0-4ddf-bb2a-4eb5697f85b4", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 2, "더블 쿼트 파운드 치즈 버거", 7400, 770, "2b4bacad-b7c4-4855-8cc0-ef0f00dd9f61", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 3, "더블 치즈 버거", 4700, 479, "809049db-1d67-4192-b935-e8e161eb92c6", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 4, "맥스파이스 상하이 버거", 5500, 501, "e9646f50-f9ad-48f1-adf7-b63bac045179", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 5, "불고기 버거", 3100, 409, "53abd5d7-72f7-4e03-b72a-2a374dc92a6e", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 6, "슈슈 버거", 4700, 409, "94c3fe81-de1f-4e55-90d3-918380516508", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 7, "슈비 버거", 5800, 540, "c63c478c-b338-4c9f-954c-adc19f2c59f5", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 8, "1995 버거", 6400, 572, "d47f49a7-bb17-4afe-afbd-c991ed577f17", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 9, "맥치킨 버거", 3500, 523, "d2c4104b-c7eb-4904-ab33-e0e9d4e78cb9", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 10, "에그 불고기 버거", 3900, 491, "56f770a0-91c0-41c4-a5ce-71402c05e742", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 11, "베이컨 토마토 디럭스 버거", 5800, 570, "781a61e4-f8bd-4d45-b472-6db07cf2ab34", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 1, 12, "맥크리스피 디럭스 버거", 6800, 600, "b03c6d5d-e92b-44b7-a34c-5d272b35896d", FALSE, DEFAULT, NULL, DEFAULT);

-- 버거 세트
INSERT INTO menu VALUES (0, 2, 13, "더블 빅맥 버거 세트", 9700, 1125, "0cf3bf5d-8543-48ef-af47-c7c54820d364", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 14, "더블 쿼트 파운드 치즈 버거 세트", 9100, 1094, "99f2d05b-e541-4b47-91b5-1ed27602286e", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 15, "더블 치즈 버거 세트", 6000, 802, "36dd2358-1203-4037-8459-8c8c15f3851a", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 16, "맥스파이스 상하이 버거 세트", 6900, 825, "6a38dacd-d744-425d-93b4-17940ffc2f57", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 17, "불고기 버거 세트", 4900, 732, "6d96e2aa-481a-4f5e-ad47-2152f92b6156", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 18, "슈슈 버거 세트", 6000, 732, "27d2b58d-2364-45d9-95ac-d7b3954019cc", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 19, "슈비 버거 세트", 7800, 863, "5be69e38-f9c0-4cff-9e93-9a15a6cd20e0", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 20, "1995 버거 세트", 7800, 896, "451e80a8-4679-471f-bae7-2bbdcc52f1fe", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 21, "맥치킨 버거 세트", 5000, 847, "1a8c441d-bc54-47c8-8e0c-1e583e62f36c", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 22, "에그 불고기 버거 세트", 5600, 815, "0c30b0cb-aa8d-4761-bb5e-f861846ead68", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 23, "베이컨 토마토 디럭스 버거 세트", 7700, 894, "e6156a06-a8bc-436b-9bd6-9ab74b6dae0f", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 2, 24, "맥크리스피 디럭시 버거 세트", 7900, 923, "9ea07473-f5e5-437f-98a8-33fbb5aa74de", FALSE, DEFAULT, NULL, DEFAULT);

-- 해피밀
INSERT INTO menu VALUES (0, 3, 37, "베이컨 에그 맥머핀", 5000, 327, "5f7df82a-9633-4d03-839c-c7ce39c5eb34", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 3, 38, "소시지 에그 맥머핀", 5000, 463, "29d46e58-9520-442a-895c-c1de27ebbae3", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 3, 39, "에그 맥머핀", 4000, 303, "168e2eb8-ca3d-4e76-a2f2-6c1cf782b41e", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 3, 40, "핫케익", 3500, 223, "fe1dfc68-bb72-414f-8aa2-1e072cd90f84", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 3, 41, "해쉬브라운", 1000, 162, "bec27a6b-25ae-4d9e-b1d8-181a99872c47", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 3, 42, "딸기 코코넛 푸딩", 1500, 153, "cff3a4f2-07aa-4aaa-b39d-3ba9f5aacf8b", FALSE, DEFAULT, NULL, DEFAULT);

-- 디저트 앤 사이드 메뉴
INSERT INTO menu VALUES (0, 4, 25, "베리 스트로베리 맥플러리", 2500, 320, "6209edfe-876f-4c7e-942e-9c6aab771442", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 26, "오레오 맥플러리", 2000, 345, "ee1d1c43-a8df-4553-aedd-4afb13484916", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 27, "초코 오레오 맥플러리", 2500, 234, "399a506c-049f-45d2-ba43-a8b15e5e25bd", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 28, "스트로베리콘", 1000, 119, "19a2fc74-e45d-4504-a5f6-c8bcf9ac4f00", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 29, "딸기 오레오 맥플러리", 2000, 248, "91dd73f6-afac-4e71-991f-05fec51bbb71", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 30, "아이스크림콘", 1000, 100, "7616f5ee-ee53-4f80-befe-3e8bf6aa6601", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 31, "맥윙", 2300, 180, "406dc8ad-a5ef-4a97-b304-18c2d4af2655", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 32, "맥윙콤보", 3500, 220, "5d23e357-8c09-4521-92a3-ff2dc4946890", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 33, "코울슬로", 1900, 120, "e93921e9-efd4-4c69-a9a7-5e950d5d0995", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 34, "상하이 치킨 스냅랩", 3400, 480, "ba661799-4957-424d-a45e-dc5874f8fed4", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 35, "골든 모짜렐라 치즈스틱", 3000, 439, "a2246689-a787-4fa8-8b17-f94a6d9191c5", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 4, 36, "후렌치 후라이", 1700, 415, "9b64530a-939a-4032-bb2f-b694b5ad5a30", FALSE, DEFAULT, NULL, DEFAULT);

-- 음료&커피
INSERT INTO menu VALUES (0, 5, 43, "자두 천도복숭아 칠러", 3000, 60, "a5161e3b-4acf-4d1f-abc9-6ccef3a63d4d", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 44, "자두 천도복숭아 칠러", 3000, 70, "619ac3bb-592f-45e1-90d9-63206d9ee3d0", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 45, "핫 아메리카노", 1500, 20, "32595def-eb94-4fc1-bb95-7557d3c5107a", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 46, "아이스 아메리카노", 1500, 20, "53c1d4c5-7847-4122-835e-bff391b770fe", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 47, "핫 카페라떼", 2000, 40, "5c17b171-75d6-4dd2-bf9c-43b1748692d6", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 48, "아이스 카페라떼", 2000, 40, "8dbe9886-f554-490d-a8e3-de3e48157f06", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 49, "핫 바닐라라떼", 2500, 70, "9559e5f0-e137-4a86-857c-ef5eba043c56", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 50, "아이스 바닐라라떼", 2500, 70, "4bc3e4d4-bd69-411f-8522-636513e4b704", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 51, "코카콜라", 1000, 30, "9e21c78d-2946-4241-a33b-f79a8003af23", TRUE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 52, "코카콜라 제로", 1000, 0, "10e8209c-cf76-4c23-9fa4-0927b36b51f1", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 53, "스프라이트", 1000, 30, "77655cae-8470-4496-95b0-36754fed0f6f", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 54, "환타", 1000, 30, "1f65e23a-e19e-4c73-bc2b-113ecbd831db", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 55, "바닐라 쉐이크", 3000, 90, "4debcd10-d125-4e2d-9b7f-842782b9a98b", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 56, "딸기 쉐이크", 3000, 90, "63ef849f-894c-4243-a314-f3f6463dc95c", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 57, "초코 쉐이크", 3000, 90, "49af93ca-b371-4b2d-a5f5-42ff6bffacbc", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 58, "생수", 500, 0, "d97f2ed2-f41a-405f-a545-6241d08a8c1e", FALSE, DEFAULT, NULL, DEFAULT);
INSERT INTO menu VALUES (0, 5, 59, "오렌지 주스", 1500, 20, "a2a7fdc5-a9b4-4031-832a-04cd96594cf6", FALSE, DEFAULT, NULL, DEFAULT);


SELECT * FROM menu;


--


CREATE TABLE `order_menu` (
	`order_idx`	bigint	primary key auto_increment,
	`order_code`	varchar(255)	NOT NULL unique,
	`order_price`	int	NOT NULL,
	`order_count`	int	NOT NULL,
	`order_number`	int	NOT NULL	DEFAULT 1,
	`order_status`	enum('결제완료', '결제중')	NOT NULL	DEFAULT '결제완료',
	`create_date`	datetime	NOT NULL	DEFAULT now(),
	`update_date`	datetime	NULL,
	`is_deleted`	boolean	NOT NULL	DEFAULT 0
);

INSERT INTO `order_menu` VALUES (0, "ea7fbe75-db66-42ce-9498-81675ce6e6ce", 10000, 2, default, DEFAULT, DEFAULT, NULL, default);



SELECT * FROM `order_menu` ;


DROP TABLE `order_menu` CASCADE;
DROP TABLE `menu` CASCADE;
DROP TABLE `category` CASCADE;
DROP TABLE `image` CASCADE;
DROP TABLE `user` CASCADE;
