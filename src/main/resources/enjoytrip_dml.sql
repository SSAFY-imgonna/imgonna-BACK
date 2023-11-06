-- 데이터베이스 사용
use enjoytrip;

 
-- 회원정보(members) 테이블에 임시 데이터 삽입
insert into members (id, email, name, password, phone, nickname, role)
values ('admin', 'admin@samsung.com', 'admin', '1234', '010-1234-5678', 'admin', 'admin');
 
insert into `members` (id, email, name, password, phone, nickname, mbti, introduction, role)
values 	('ssafy', 'ssafy@ssafy.com', '김싸피', '1234', '010-1111-2222', '가을타는 여행자', 'ENTP', '#자연 #20대 #취미는독서 #액티비티러버', 'general'), 
        ('wus', 'wus@naver.com', '서지현', '1111', '010-3333-4444', '싸피 10기 광주 5반 서젼', 'ISTP', '다양하고 새로운 사람들을 만나는 것을 좋아합니다 :)', 'general');

select * from members; 

select count(*) from members
where id='ssafy';