create table tbl_board_users(
    idx bigint not null auto_increment,
    user_id varchar(20) not null,
    password varchar(30) not null,
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
    password varchar(30),
    update_date datetime not null,
    primary key(idx),
    constraint idx_parent_idx index(parent_idx),
    constraint idx_writer_idx index(writer_idx),
    constraint idx_title index(title)
);

INSERT INTO tbl_board_users(user_id, password, name, grade, update_date)
VALUES ('root', 'password!1', '관리자', 1, now());