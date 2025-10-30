create table tests (
    id bigserial primary key,
    name varchar (255) not null unique,
    created_at timestamptz not null default current_timestamp,
    updated_at timestamptz not null default current_timestamp
);

create table questions (
    id bigserial primary key,
    test_id bigint not null,
    order_number int not null,
    text varchar (255) not null,
    created_at timestamptz not null default current_timestamp,
    updated_at timestamptz not null default current_timestamp,
    constraint questions_tests_fk foreign key (test_id) references tests(id) on delete cascade,
    constraint unique_question_order unique (test_id, order_number),
    constraint unique_question_text unique (test_id, text)
);

create table answers (
    id bigserial primary key,
    question_id bigint not null,
    order_number int,
    text varchar (255) not null,
    is_right boolean not null default false,
    created_at timestamptz not null default current_timestamp,
    updated_at timestamptz not null default current_timestamp,
    constraint answers_questions_fk foreign key (question_id) references questions(id) on delete cascade,
    constraint unique_answer_text unique (question_id, text)
);

create unique index unique_answer_order ON answers (question_id, order_number) where order_number is not null;

create unique index one_correct_answer_per_question on answers (question_id) where is_right = true;
