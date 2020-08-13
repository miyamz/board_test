CREATE ALIAS IF NOT EXISTS FT_INIT FOR "org.h2.fulltext.FullText.init";
CALL FT_INIT();

create table tbl_board_users(
    idx bigint not null auto_increment,
    user_id varchar(20) not null,
    password varchar(256) not null,
    name varchar(20),
    grade int not null,
    update_date datetime not null,
    constraint pk_board_user primary key(idx),
    constraint uk_board_user unique key(user_id)
);

create table tbl_board(
    idx bigint not null auto_increment,
    parent_idx bigint,
    writer_idx bigint not null,
    title varchar(100) not null,
    body clob,
    password varchar(256),
    del_flag smallint not null default '0',
    update_date datetime not null,
    primary key(idx),
    constraint idx_parent_idx index(parent_idx, del_flag),
    constraint idx_writer_idx index(writer_idx, del_flag),
    constraint idx_title index(title, del_flag),
    constraint idx_del_flag index(del_flag)
);
CALL FT_CREATE_INDEX('PUBLIC', 'TBL_BOARD', 'BODY');

INSERT INTO tbl_board_users(user_id, password, name, grade, update_date)
VALUES ('root', '6b1944947aa2ae57c54288b1381ea0c94cffb55c50cc0174d302af2a6ee8bcf4', '관리자', 1, now());
INSERT INTO tbl_board_users(user_id, password, name, grade, update_date)
VALUES ('miyam', '6b1944947aa2ae57c54288b1381ea0c94cffb55c50cc0174d302af2a6ee8bcf4', '개발자-게스트', 90, now());

-- 더미데이터 저장
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test1', 'test1', '', '2020-08-12 14:00:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test2', 'test2', '', '2020-08-12 14:01:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test3', 'test3', '', '2020-08-12 14:02:00');
insert into tbl_board (parent_idx, writer_idx, title, body, password, update_date) values (0, 1, 'test4', 'test4', '', '2020-08-12 14:03:00');
