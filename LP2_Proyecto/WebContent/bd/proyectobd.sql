-- borra la bd 
DROP DATABASE IF EXISTS nuevatienda;
-- creamos la bd
CREATE DATABASE nuevatienda;
-- activamos la bd
USE nuevatienda;
-- tablas
CREATE TABLE tb_tipos(
	idTipo		int not null  primary key,
	descripcion varchar(15)
);

CREATE TABLE tb_distritos(
	codDistrito  int auto_increment,
	nomdistrito varchar(50),
	primary key (coddistrito)
);

CREATE TABLE tb_categorias(
	idcategoria	int not null primary key,
	descripcion varchar(45)
);

CREATE TABLE tb_usuarios(
	codUsuario  int  auto_increment,
	nombre varchar(15),
	apellido varchar(25),
	codDistrito  int ,
	usuario  varchar(45) NOT NULL,
	clave    varchar(12),
	idTipo    int DEFAULT 0,  -- 0:Cliente , 1:Administrador
	estado  int(1) DEFAULT 1, -- 0:Inactivo , 1:Activo
	primary key (codUsuario),
	foreign key (idTipo) references tb_tipos(idTipo),
	foreign key (codDistrito) references tb_distritos(codDistrito)
); 

/*CREATE TABLE tb_cliente(
	codcliente int auto_increment ,
	nomdistrito varchar(50),
	apellido varchar(25),
	telefono int,
	dni int ,
	correo varchar(100),
	coddistrito  int ,
	primary key (codcliente),
	foreign key (codcliente) references tb_distrito(coddistrito)
);*/

CREATE TABLE tb_boleta(
	numBoleta char(5) not null,
	codUsuario int,
	fecha datetime null,
	total decimal(8,2),
	primary key (numBoleta),
	foreign key (codUsuario) references tb_usuarios(codUsuario)
);

CREATE TABLE tb_productos(
	idprod int not null auto_increment,
    nomprod varchar(45),
	descripcion varchar(200),
	stock int,
	precio decimal(8,2),
	idcategoria int,
    estado int, -- 0:Descativado , 1:Activo
	primary key (idprod), 
	foreign key (idcategoria) references tb_categorias(idcategoria)
);

CREATE TABLE tb_detalle_boleta(
	numBoleta char(5),
	idprod int,
	cantidad int null,
	preciovta decimal (8,2),
    primary key(numBoleta, idprod),
	foreign key  (numBoleta)references tb_boleta(numBoleta),
	foreign key (idprod) references tb_productos(idprod)
);

insert into tb_distritos values(null,'San Miguel');
insert into tb_distritos values(null,'Miraflores');
insert into tb_distritos values(null,'Surquillo');
insert into tb_distritos values(null,'San Isidro');
insert into tb_distritos values(null,'Magdalena');
insert into tb_distritos values(null,'Pueblo Libre');
insert into tb_distritos values(null,'Surco');

insert into tb_tipos values(0,'Cliente');
insert into tb_tipos values(1,'Administrador');

insert into tb_usuarios values(null, 'Carlos', 'Cotrina', 1, 'i201821721', '123', 1, 1);
insert into tb_usuarios values(null, 'David', 'Antonio', 3, 'i201823296', 'dac49', 1, 1);
insert into tb_usuarios values(null, 'Carlos', 'Garcia', 2, 'carlos', '123',0,1);
insert into tb_usuarios values(null, 'Javier', 'Jimenez', 2, 'javier', '456',0,1);
insert into tb_usuarios values(null, 'Paolo', 'Guerrero', 2, 'paolin', '999',0,1);
insert into tb_usuarios values(null, 'Rodrigo', 'Garcia', 2, 'miguel', '456',0,1);
insert into tb_usuarios values(null, 'Admin', 'istrador', 1, 'admin', '123',1,1);

insert into tb_categorias values(1,'Ropa');
insert into tb_categorias values(2,'Tecnologia');
insert into tb_categorias values(3,'Electrodomesticos');
insert into tb_categorias values(4,'Otros');

insert into tb_productos values(null, 'Camisas Newport', 'Newport Camisa de vestir Hombre', 20, 19.90, 1, 1); -- 1
insert into tb_productos values(null, 'Pantalon Basement', 'Basement Pantalón Hombre', 15, 69.90, 1, 1);
insert into tb_productos values(null, 'Refrigeradora LG', 'LG - 438 Lts LT44WGP Inox', 50, 1519.90, 3, 1);
insert into tb_productos values(null, 'Televisor Samsung', 'Samsung UHD 50 UN50TU8000GXPE | CRYSTAL UHD | Serie TU8000',20,1799.90, 2, 1);
insert into tb_productos values(null, 'Mochila DOO AUSTRALIA', 'DOO AUSTRALIA Militar', 10, 69.90, 4, 1);
insert into tb_productos values(null, 'Casaca MOUNTAIN GEAR', 'MOUNTAIN GEAR Marino', 35, 119.90, 1, 1);
insert into tb_productos values(null, 'Headphones JBL', 'JBL - LIVE650BT IN EAR', 70, 799.90, 2, 1);
insert into tb_productos values(null, 'Consola Nintendo', 'Nintendo Switch Neon 1.1', 68, 1785.90, 2, 1);
insert into tb_productos values(null, 'Mouse Gamer', 'CYBERTEL Hyperion CYB M506', 43, 40.90, 2 , 1);
insert into tb_productos values(null, 'Secadora INDURAMA', 'INDURAMA Eléctrica 6Kg Blanca', 30, 599.90, 3 , 1); -- 10
insert into tb_productos values(null, 'Pelota Adidas', 'Adidas Pelota de Fútbol', 81, 34.90, 4 , 1);
insert into tb_productos values(null, 'Parlante JBL', 'Parlante Inalámbrico Go2 Black', 56, 129.90, 2 , 1);
insert into tb_productos values(null, 'Cooler Master', 'Kit Gaming Teclado + Mouse Masterset 111 Rgb', 94, 209.90, 2 , 1);
insert into tb_productos values(null, 'Pantalon KAPPA', 'Pantalón Slim Hombre', 71, 179.90, 1 , 1); 
insert into tb_productos values(null, 'Abrigos University Club', 'Abrigo Mujer', 39, 129.90, 1 , 1); -- 15
insert into tb_productos values(null, 'Botas ALDO', 'Botas Ferrawia', 45, 299.90, 4 , 1);
insert into tb_productos values(null, 'Afeitadora Philips', 'Afeitadora Inalámbrica Seco/Húmedo', 60, 99.90, 3 , 1);
insert into tb_productos values(null, 'Calefactor Recco', 'Estufa Eléctrica Infrared NSB-80L4', 10, 39.90, 4 , 1);
insert into tb_productos values(null, 'Butaca Basement Home ', 'Butaca Astro', 80, 300.90, 4 , 1);
insert into tb_productos values(null, 'Smartwatch Xiaomi', 'Mi Smart Band 4 + 10000 mAh Power Bank', 30, 248.90, 2 , 1); -- 20

insert into tb_boleta values('B0001', 3, '2020-06-14', 999);
insert into tb_detalle_boleta values('B0001', 17, 10, 99.90);

insert into tb_boleta values('B0002', 4, '2020-06-26', 199);
insert into tb_detalle_boleta values('B0002', 1, 10, 19.90);

insert into tb_boleta values('B0003', 5, '2020-07-03', 599.90);
insert into tb_detalle_boleta values('B0003', 10, 1, 599.90);

insert into tb_boleta values('B0004', 6, '2020-07-11', 999);
insert into tb_detalle_boleta values('B0004', 15, 1, 129.90);



DROP PROCEDURE IF EXISTS sp_listar_prod;
DELIMITER //
CREATE PROCEDURE sp_listar_prod (
	est int
)
BEGIN
	select p.*, c.descripcion
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria 
    where p.estado = est;
END //
DELIMITER ;

call sp_listar_prod (1);


DROP PROCEDURE IF EXISTS sp_listar_prod_cod;
DELIMITER //
CREATE PROCEDURE sp_listar_prod_cod (
	codigo int, 
    est int
)
BEGIN
	select p.*, c.descripcion
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria
    where p.idcategoria = codigo
    and p.estado = est;
END //
DELIMITER ;

call sp_listar_prod_cod (1,1);



DROP PROCEDURE IF EXISTS sp_listar_usu;
DELIMITER //
CREATE PROCEDURE sp_listar_usu (
	est int
)
BEGIN
	select u.*, t.descripcion, d.nomdistrito
	from tb_usuarios u  
	join tb_tipos t 
	on u.idTipo = t.idTipo 
    join tb_distritos d
    on d.codDistrito = u.codDistrito
    where u.estado = est;
END //
DELIMITER ;

call sp_listar_usu (1);


DROP PROCEDURE IF EXISTS sp_listar_usu_tip;
DELIMITER //
CREATE PROCEDURE sp_listar_usu_tip (
	est int,
    tip int
   
)
BEGIN
	select u.*, t.descripcion, d.nomdistrito
	from tb_usuarios u  
	join tb_tipos t 
	on u.idTipo = t.idTipo 
    join tb_distritos d
    on d.codDistrito = u.codDistrito
    where u.idTipo = tip
    and u.estado = est;
END //
DELIMITER ;

call sp_listar_usu_tip (1,1);


DROP PROCEDURE IF EXISTS sp_listar_usu_dis;
DELIMITER //
CREATE PROCEDURE sp_listar_usu_dis (
	est int,
    dis int 
)
BEGIN
	select u.*, t.descripcion, d.nomdistrito
	from tb_usuarios u  
	join tb_tipos t 
	on u.idTipo = t.idTipo 
    join tb_distritos d
    on d.codDistrito = u.codDistrito
    where d.codDistrito = dis
    and u.estado = est;
END //
DELIMITER ;

call sp_listar_usu_dis (1,1);


DROP PROCEDURE IF EXISTS sp_listar_usu_dis_tip;
DELIMITER //
CREATE PROCEDURE sp_listar_usu_dis_tip (
	est int,
    dis int, 
    tip int    
)
BEGIN
	select u.*, t.descripcion, d.nomdistrito
	from tb_usuarios u  
	join tb_tipos t 
	on u.idTipo = t.idTipo 
    join tb_distritos d
    on d.codDistrito = u.codDistrito
    where d.codDistrito = dis
    and u.estado = est
    and u.idTipo = tip;
END //
DELIMITER ;

call sp_listar_usu_dis_tip (1,1,1);


DROP PROCEDURE IF EXISTS sp_listar_tienda;
DELIMITER //
CREATE PROCEDURE sp_listar_tienda ()
BEGIN
	select p.*, c.descripcion
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria 
    where p.stock > 0
    and estado = 1;
END //
DELIMITER ;

call sp_listar_tienda ();


DROP PROCEDURE IF EXISTS sp_listar_tienda_cod;
DELIMITER //
CREATE PROCEDURE sp_listar_tienda_cod (
	cod int
)
BEGIN
	select p.*, c.descripcion
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria 
    where p.estado = 1
    and p.stock > 0
    and p.idcategoria = cod;
END //
DELIMITER ;

call sp_listar_tienda_cod (1);

/*validar Usuario*/
DROP PROCEDURE IF EXISTS sp_validaUsuario;
DELIMITER //
CREATE PROCEDURE sp_validaUsuario(usr varchar(45), psw varchar(12))
BEGIN
SELECT*FROM tb_usuarios
WHERE usuario=usr AND clave=psw and estado = 1;

END //
DELIMITER ;

CALL sp_validaUsuario('i201823296','dac49');

/*listar distritos */
DROP PROCEDURE IF EXISTS sp_listarDistritos;
DELIMITER //
CREATE PROCEDURE sp_listarDistritos()
BEGIN
SELECT*FROM tb_distritos;
END //
DELIMITER ;
CALL sp_listarDistritos();

DROP PROCEDURE IF EXISTS sp_listarStock;
DELIMITER //
CREATE PROCEDURE sp_listarStock(
	sto int
)
BEGIN
	select p.*, c.descripcion
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria
    where stock <= sto
    order by stock asc;
END //
DELIMITER ;
CALL sp_listarStock(20);

DELIMITER $$
DROP FUNCTION IF EXISTS `test`.`initcap`$$
CREATE FUNCTION `initcap`(x char(30)) RETURNS char(30) CHARSET utf8
BEGIN
SET @str='';
SET @l_str='';
WHILE x REGEXP ' ' DO
SELECT SUBSTRING_INDEX(x, ' ', 1) INTO @l_str;
SELECT SUBSTRING(x, LOCATE(' ', x)+1) INTO x;
SELECT CONCAT(@str, ' ', CONCAT(UPPER(SUBSTRING(@l_str,1,1)),LOWER(SUBSTRING(@l_str,2)))) INTO @str;
END WHILE;
RETURN LTRIM(CONCAT(@str, ' ', CONCAT(UPPER(SUBSTRING(x,1,1)),LOWER(SUBSTRING(x,2)))));
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS sp_listarVentas; -- por mes
DELIMITER //
CREATE PROCEDURE sp_listarVentas(
	year int
)
BEGIN
	select initcap(DATE_FORMAT(b.fecha, "%c - %M")) mes, year(b.fecha) anio, sum(b.total), sum(db.cantidad) as productos, count(distinct(b.codUsuario)) clientes, users.users
	from tb_boleta b
    join tb_detalle_boleta db
    on db.numBoleta = b.numBoleta,
	(select count(codUsuario) as users from tb_usuarios
     where idTipo = 0) as users
	where year(b.fecha) = year
	group by mes; 
END //
DELIMITER ;
CALL sp_listarVentas(2020);


DROP PROCEDURE IF EXISTS sp_listarProductosVentas; -- por mes
DELIMITER //
CREATE PROCEDURE sp_listarProductosVentas(
	fecha1 date,
    fecha2 date
)
BEGIN
	select p.idprod, p.nomprod, p.stock, c.descripcion,sum(db.cantidad), sum(db.cantidad*db.preciovta), p.estado
    from tb_detalle_boleta db
    join tb_boleta b on db.numBoleta = b.numBoleta
    join tb_productos p on db.idprod = p.idprod
    join tb_categorias c on p.idcategoria = c.idcategoria
    where b.fecha between fecha1 and fecha2
    group by p.nomprod;
END //
DELIMITER ;
CALL sp_listarProductosVentas('2020-01-10', '2020-10-10');