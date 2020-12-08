/*--------------> DEFINIENDO LA BD nuevatienda <--------------*/
-- BORRANDO LA BD
DROP DATABASE IF EXISTS nuevatienda;
-- CRENADO LA BD
CREATE DATABASE nuevatienda;
-- USANDO LA BD
USE nuevatienda;

/*--------------> CREANDO LAS TABLAS <--------------*/
-- CREANDO LA TABAL tb_tipos
DROP TABLE IF EXISTS tb_tipos;
CREATE TABLE tb_tipos(
	idTipo		int,
	descripcion varchar(15),
    CONSTRAINT PK_Tipo Primary Key(idTipo)
);

-- CREANDO LA TABAL tb_distritos
DROP TABLE IF EXISTS tb_distritos;
CREATE TABLE tb_distritos(
	codDistrito  int 		AUTO_INCREMENT,
	nomDistrito varchar(50),
	CONSTRAINT PK_Distrito Primary Key(codDistrito)
);

-- CREANDO LA TABAL tb_categorias
DROP TABLE IF EXISTS tb_categorias;
CREATE TABLE tb_categorias(
	idCategoria	int 		AUTO_INCREMENT,
	descripcion varchar(45),
    CONSTRAINT PK_Categoria Primary Key(idCategoria)
);

-- CREANDO LA TABAL tb_usuarios
DROP TABLE IF EXISTS tb_usuarios;
CREATE TABLE tb_usuarios(
	codUsuario  int 		AUTO_INCREMENT,
	nombre 		varchar(15),
	apellido 	varchar(25),
	codDistrito int,
	usuario  	varchar(45) NOT NULL,
	clave    	varchar(12),
	idTipo    	int DEFAULT 0,  -- 0:Cliente , 1:Administrador
	estado  	bit DEFAULT 1, -- 0:Inactivo , 1:Activo
	CONSTRAINT PK_Usuario Primary Key(codUsuario),
	CONSTRAINT FK_Usuario_Tipo Foreign Key(idTipo) REFERENCES tb_tipos(idTipo),
	CONSTRAINT FK_Usuario_Distrito Foreign Key(codDistrito) REFERENCES tb_distritos(codDistrito)
); 

/*-- CREANDO LA TABAL tb_cliente
DROP TABLE IF EXISTS tb_cliente;
CREATE TABLE tb_cliente(
	codcliente 	int 		AUTO_INCREMENT,
	nomdistrito varchar(50),
	apellido 	varchar(25),
	telefono 	int,
	dni 		int ,
	correo 		varchar(100),
	coddistrito int ,
	CONSTRAINT PK_Cliente Primary Key(codcliente),
	CONSTRAINT FK_Cliente_Distrito Foreign Key (codcliente) REFERENCES tb_distrito(coddistrito)
);*/

-- CREANDO LA TABAL tb_boleta
DROP TABLE IF EXISTS tb_boleta;
CREATE TABLE tb_boleta(
	numBoleta 	char(5),
	codUsuario 	int,
	fecha 		datetime,
	total 		decimal(8,2),
	CONSTRAINT PK_Boleta Primary Key(numBoleta),
	CONSTRAINT FK_Boleta Foreign Key(codUsuario) REFERENCES tb_usuarios(codUsuario)
);

-- CREANDO LA TABAL tb_productos
DROP TABLE IF EXISTS tb_productos;
CREATE TABLE tb_productos(
	idProd 		int 		AUTO_INCREMENT,
    nomProd 	varchar(45),
	descripcion varchar(200),
	stock 		int,
	precio 		decimal(8,2),
	idCategoria int,
    estado 		bit, -- 0:Descativado , 1:Activo
	CONSTRAINT PK_Productos Primary Key(idProd), 
	CONSTRAINT FK_Productos_Categoria Foreign Key(idCategoria) REFERENCES tb_categorias(idCategoria)
);
select * from tb_productos;
-- CREANDO LA TABAL tb_detalle_boleta
DROP TABLE IF EXISTS tb_detalle_boleta;
CREATE TABLE tb_detalle_boleta(
	numBoleta char(5),
	idprod 	  int,
	cantidad  int,
	preciovta decimal (8,2),
    CONSTRAINT PK_Boleta_Producto Primary Key(numBoleta, idProd),
	CONSTRAINT FK_DetalleBoleta_BOLETA Foreign Key(numBoleta) REFERENCES tb_boleta(numBoleta),
	CONSTRAINT FK_DetalleBoleta_Producto Foreign Key(idprod) REFERENCES tb_productos(idProd)
);

/*--------------> INSERTANDO DATOS A LAS TABLAS <--------------*/
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
insert into tb_usuarios values(null, 'Admin', 'istrador', 1, 'admin', '123',1,0);
select * from tb_usuarios where estado = 1;
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
insert into tb_productos values(null, 'Camisas Newport', 'Newport Camisa de vestir Hombre', 20, 19.90, 1, 0);

insert into tb_boleta values('B0001', 3, '2020-06-14', 999);
insert into tb_detalle_boleta values('B0001', 17, 10, 99.90);

insert into tb_boleta values('B0002', 4, '2020-06-26', 199);
insert into tb_detalle_boleta values('B0002', 1, 10, 19.90);

insert into tb_boleta values('B0003', 5, '2020-07-03', 599.90);
insert into tb_detalle_boleta values('B0003', 10, 1, 599.90);

insert into tb_boleta values('B0004', 6, '2020-07-11', 999);
insert into tb_detalle_boleta values('B0004', 15, 1, 129.90);


/*--------------> CREANDO USP'S <--------------*/
/*
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

DELIMITER $$
DROP FUNCTION IF EXISTS initcap $$
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
*/
-- USP'S DE REGISTRO, ACTUALIZACIÓN
#PRODUCTOS
-- > Listar PRODUCTOS
DROP PROCEDURE IF EXISTS sp_listarProductos;
DELIMITER $$
CREATE PROCEDURE sp_listarProductos (
	codigo int, 
	est int
)
BEGIN
    Select p.*, c.descripcion
	From tb_productos p  
	Join tb_categorias c on p.idcategoria = c.idcategoria
    Where (p.idcategoria = codigo or codigo = 0) and p.estado = est;
END $$
DELIMITER ;

call sp_listarProductos(0,1);

-- > REGISTRAR PRODUCTO
DROP PROCEDURE IF EXISTS USP_registrarProducto;
DELIMITER $$
CREATE PROCEDURE USP_registrarProducto(
prod varchar(45),
descrip varchar(200),
stck int,
prec decimal(8,2),
categoria int,
est bit
)
BEGIN
	insert into tb_productos(nomProd,descripcion,stock,precio,idCategoria,estado)
    values (prod,descrip,stck,prec,categoria,est);
END $$
DELIMITER ;

CALL USP_registrarProducto('Play Station 5', 'Consola de videoJuegos versión 5', 30, 20.75, 3 , 1);

-- > Actualizar PRODUCTO
DROP PROCEDURE IF EXISTS USP_actualizarProducto;
DELIMITER $$
CREATE PROCEDURE USP_actualizarProducto(
prod varchar(45),
descrip varchar(200),
stck int,
prec decimal(8,2),
categoria int,
est bit,
id int
)
BEGIN
    Update tb_productos
	Set  nomprod = prod, descripcion = descrip, stock = stck, precio = prec, idcategoria = categoria, estado = est
	Where idprod = id;
END $$
DELIMITER ;

CALL USP_actualizarProducto('Play Station 5', 'Consola de videoJuegos versión 5.1', 30, 20.75, 3 , 1,23);

-- > CAMBIAR ESTADO PRODUCTO
DROP PROCEDURE IF EXISTS USP_cambiarEstadoProducto;
DELIMITER $$
CREATE PROCEDURE USP_cambiarEstadoProducto(
est bit,
id int
)
BEGIN
	Update tb_productos
	Set estado = est
	Where idprod = id;
END $$
DELIMITER ;

CALL USP_cambiarEstadoProducto(0,23);

-- > BUSCAR PRODUCTO
DROP PROCEDURE IF EXISTS USP_buscarProducto;
DELIMITER $$
CREATE PROCEDURE USP_buscarProducto(
id int
)
BEGIN
	Select * 
    From tb_productos 
    Where idprod = id;
END $$
DELIMITER ;

CALL USP_buscarProducto(14);

#CATEGORIA
-- > LISTAR CATEGORIAS
DROP PROCEDURE IF EXISTS sp_listarCategoria;
DELIMITER $$
CREATE PROCEDURE sp_listarCategoria()
BEGIN
	Select *
    From tb_categorias;
END $$
DELIMITER ;
CALL sp_listarCategoria();

#REPORTES
-- > LISTAR CANTIDAD DE STOCK
DROP PROCEDURE IF EXISTS sp_listarStock;
DELIMITER //
CREATE PROCEDURE sp_listarStock(
	sto int
)
BEGIN
	select p.*, c.descripcion as categoria
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria
    where stock <= sto
    order by stock asc;
END //
DELIMITER ;
CALL sp_listarStock(20);

-- > LISTAR VENTAS POR MES
DROP PROCEDURE IF EXISTS sp_listarVentas; -- por mes #CONSULTAR
DELIMITER //
CREATE PROCEDURE sp_listarVentas(
	anio int
)
BEGIN
	select (DATE_FORMAT(b.fecha, "%M")) mes,year(b.fecha) anio, sum(b.total) as total, sum(db.cantidad) as productos, count(distinct(b.codUsuario)) clientes, users.users
	from tb_boleta b
    join tb_detalle_boleta db
    on db.numBoleta = b.numBoleta,
	(select count(codUsuario) as users from tb_usuarios
     where idTipo = 0) as users
	where year(b.fecha) = anio
	group by mes; 
END //
DELIMITER ;
CALL sp_listarVentas(2020);

-- > LISTAR VENTAS DE PRODUCTO POR MES
DROP PROCEDURE IF EXISTS sp_listarProductosVentas; -- por mes
DELIMITER //
CREATE PROCEDURE sp_listarProductosVentas(
	fecha1 date,
    fecha2 date
)
BEGIN
	select p.idprod,
    p.nomprod as nombre, 
    p.stock, 
    c.descripcion as categoria,
    sum(db.cantidad) as cantidad,
    sum(db.cantidad*db.preciovta) as total,
    p.estado
    from tb_detalle_boleta db
    join tb_boleta b on db.numBoleta = b.numBoleta
    join tb_productos p on db.idprod = p.idprod
    join tb_categorias c on p.idcategoria = c.idcategoria
    where b.fecha between fecha1 and fecha2
    group by p.nomprod;
END //
DELIMITER ;
CALL sp_listarProductosVentas('2020-01-10', '2020-10-10');

SET lc_time_names = 'es_PE';

#USUARIO
-- > VALIDAR USUARIO
DROP PROCEDURE IF EXISTS sp_validaUsuario;
DELIMITER //
CREATE PROCEDURE sp_validaUsuario(usr varchar(45), psw varchar(12))
BEGIN
	SELECT*FROM tb_usuarios
	WHERE usuario=usr AND clave=psw and estado = 1;
END //
DELIMITER ;

CALL sp_validaUsuario('i201823296','dac49');

-- > BUSCAR USUARIO
DROP PROCEDURE IF EXISTS USP_buscarUsuario;
DELIMITER $$
CREATE PROCEDURE USP_buscarUsuario(
id int
)
BEGIN
	Select * 
    From tb_usuarios 
    Where codUsuario = id;
END $$
DELIMITER ;

CALL USP_buscarUsuario(1);

-- > ACTUALIZAR USUARIO
DROP PROCEDURE IF EXISTS USP_ActualizarUsuario;
DELIMITER $$
CREATE PROCEDURE USP_ActualizarUsuario(
nomb varchar(15),
ape varchar(25),
dist int,
usu varchar(45),
cla varchar(12),
tipo int,
est bit,
id int
)
BEGIN
	 Update tb_usuarios 
     Set  nombre = nomb, apellido = ape, codDistrito = dist, usuario = usu, clave = cla, idTipo = tipo, estado = est
     Where codUsuario = id;
END $$
DELIMITER ;

CALL USP_ActualizarUsuario('Rodrigo','Garcia',3,'miguel','123456',0,1,6);

-- > ACTUALIZAR ESTADO USUARIO
DROP PROCEDURE IF EXISTS USP_ActualizarEstadoUsuario;
DELIMITER $$
CREATE PROCEDURE USP_ActualizarEstadoUsuario(
est bit,
id int
)
BEGIN
	 Update tb_usuarios 
     Set estado = est
     Where codUsuario = id;
END $$
DELIMITER ;

CALL USP_ActualizarEstadoUsuario(0,6);

-- > LISTAR USUARIOS 
DROP PROCEDURE IF EXISTS USP_ListarUsuarios;
DELIMITER $$
CREATE PROCEDURE USP_ListarUsuarios(est bit, dist int, tipo int)
BEGIN
	Select *
    From tb_usuarios
    Where estado = est
    and (codDistrito = dist or dist = 0)
    and (idTipo = tipo or tipo = -1);
END $$
DELIMITER ;	

CALL USP_ListarUsuarios(1,0,-1);

-- > LISTAR DISTRITOS
DROP PROCEDURE IF EXISTS sp_listarDistritos;
DELIMITER //
CREATE PROCEDURE sp_listarDistritos()
BEGIN
	SELECT *
    FROM tb_distritos;
END //
DELIMITER ;
CALL sp_listarDistritos();

-- > REGISTRAR USUARIO
DROP PROCEDURE IF EXISTS USP_RegistrarUsuario;
DELIMITER $$
CREATE PROCEDURE USP_RegistrarUsuario(
	nombre 		varchar(15),
	apellido 	varchar(25),
	codDistrito int,
	usuario  	varchar(45),
	clave    	varchar(12),
	idTipo    	int ,  
	estado  	bit 
)
BEGIN
	insert into tb_usuarios values(null,nombre,apellido,codDistrito,usuario,clave,idTipo,estado);
END $$
DELIMITER ;

#BOLETA
-- > GENERANDO EL CODIGO CORRELATIVO DE LA BOLETA
DROP FUNCTION IF EXISTS UF_generatePKBoleta;
DELIMITER $$
CREATE FUNCTION UF_generatePKBoleta() RETURNS CHAR(5) CHARACTER SET utf8
READS SQL DATA
DETERMINISTIC
BEGIN
    SET @v_code = (Select (CAST(IFNULL(RIGHT(MAX(numBoleta),4),0) as SIGNED) + 1) From tb_boleta);
	SET @v_codeCorrlative = CAST(CONCAT('B',REPEAT('0',4 - LENGTH(@v_code)),@v_code) as Char(5));
    RETURN @v_codeCorrlative;
END $$
DELIMITER ;

SELECT UF_generatePKBoleta();

-- > REGISTRAR BOLETA
DROP PROCEDURE IF EXISTS USP_RegistrarBoleta;
DELIMITER $$
CREATE PROCEDURE USP_RegistrarBoleta(
id char(5),
idUsuario int,
tot decimal(8,2)
)
BEGIN
	insert tb_boleta(numBoleta,codUsuario,fecha,total)
    values(id,idUsuario,CURDATE(),tot);
END $$
DELIMITER ;

-- > LISTAR TIENDAS
DROP PROCEDURE IF EXISTS USP_ListarProductosXCategoria;
DELIMITER $$
CREATE PROCEDURE USP_ListarProductosXCategoria(categoria int)
BEGIN
	select p.*, c.descripcion
	from tb_productos p  
	join tb_categorias c 
	on p.idcategoria = c.idcategoria 
    where p.stock > 0
    and (p.idcategoria = categoria or categoria = 0)
    and estado = 1;
END $$
DELIMITER ;

CALL USP_ListarProductosXCategoria(1);