CREATE TABLE usuario_perfil(id_usuario int references usuario (id_usuario),
							id_perfil int references perfil(id_perfil), 
							data_criacao date, constraint pk_usuario_perfil 
							PRIMARY KEY (id_usuario, id_perfil));


