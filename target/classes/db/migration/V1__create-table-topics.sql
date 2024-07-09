CREATE TABLE topicos (
    id bigint not null auto_increment,
    titulo varchar(100) not null ,
    mensaje varchar(200) not null ,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status tinyint not null default 1,
    autor bigint not null ,
    curso varchar(100) not null ,
    primary key (id),
    CONSTRAINT  title_message UNIQUE (titulo, mensaje)
)