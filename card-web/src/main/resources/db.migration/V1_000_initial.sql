create table TB_STUDENT(
  id int primary key auto_increment,
  name varchar(100),
  document varchar(15)
);

create table TB_CARD(
  id int primary key auto_increment,
  student_id int, foreign key (student_id) references TB_STUDENT(id),
  label varchar(100),
  number varchar(50),
  expMonth int,
  expYear int,
  cvv string,
  brand string
);

create table TB_TRANSACTION(
 id int primary key auto_increment,
 student_id int, foreign key (student_id) references TB_STUDENT(id),
 card_id int, foreign key (card_id) references TB_CARD(id),
 description varchar(100),
 amount double
);