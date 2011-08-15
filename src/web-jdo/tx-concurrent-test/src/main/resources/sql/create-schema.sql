
create table USER_ACCOUNT (
  ID bigint generated by default as identity primary key,

  USER_NAME varchar(256) not null,

  BALANCE double not null,

  CREATED timestamp not null
);

create table BALANCE_CHANGE (
  ID bigint generated by default as identity primary key,

  AMOUNT double not null,

  CODE integer not null,

  USER_ID bigint not null,

  CREATED timestamp not null
);


-- constraints

alter table BALANCE_CHANGE add constraint
  FK_BALANCE_CHANGE_USER_ID foreign key (USER_ID) references USER_ACCOUNT(ID) on delete cascade;
