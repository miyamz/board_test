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

INSERT INTO tbl_board_users(user_id, password, name, grade, update_date)
VALUES ('root', '6b1944947aa2ae57c54288b1381ea0c94cffb55c50cc0174d302af2a6ee8bcf4', '관리자', 1, now());