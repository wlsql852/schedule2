create TABLE IF NOT EXISTS USER (
                                    id bigint primary key comment '유저 아이디',
                                    name varchar(100) not null comment '유저명',
    email varchar(100) not null comment '이메일',
    createDate datetime comment '작성일',
    updateDate datetime comment '수정일'
    );
ALTER TABLE USER MODIFY id bigint AUTO_INCREMENT;


CREATE TABLE IF NOT EXISTS SCHEDULE (
                                        id bigint primary key comment '일정 아이디',
                                        userId bigint not null comment '작성한 유저 아이디',
                                        title varchar(50) not null comment '할일 제목',
    content varchar(200) not null comment '할일 내용',
    createDate datetime comment '작성일',
    updateDate datetime comment '수정일',
    CONSTRAINT sch_userId_fk foreign key(userId) references USER(id)
    );
ALTER TABLE SCHEDULE MODIFY id bigint AUTO_INCREMENT;

CREATE TABLE IF NOT EXISTS MESSAGE (
                                       id bigint primary key comment '댓글 아이디',
                                       scheduleId bigint not null comment '작성된 일정 아이디',
                                       userId bigint not null comment '작성한 유저 아이디',
                                       content varchar(200) not null comment '댓글 내용',
    createDate datetime comment '작성일',
    updateDate datetime comment '수정일',
    CONSTRAINT msg_userId_fk foreign key(userId) references USER(id),
    CONSTRAINT msg_scheduleId_fk foreign key(scheduleId) references SCHEDULE(id)
    );
ALTER TABLE MESSAGE MODIFY id bigint AUTO_INCREMENT;