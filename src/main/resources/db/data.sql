USE inventory_system;

INSERT INTO categorias(nombre, descripcion) VALUES
('Audio', 'Altavoces y auriculares'),
('Consumibles', 'Cartuchos, papel y tintas'),
('Herramientas', 'Utensilios de reparación y mantenimiento'),
('Iluminación', 'Lámparas y focos LED'),
('Limpieza y Aseo', 'Suministros de higiene y limpieza'),
('Libros', 'Material de lectura y referencia'),
('Ropa', 'Uniformes y vestimenta corporativa'),
('Alimentos', 'Snacks y bebidas para personal'),
('Seguridad', 'Cámaras y alarmas'),
('Cableado', 'Cables de red y adaptadores');

INSERT INTO proveedores(nombre, telefono, direccion) VALUES
('Global Supply Corp', '90012345', 'Av. Industrial 1'),
('Audio Max SAS', '90054321', 'Calle Los Sonidos 2'),
('PaperTech SRL', '90099887', 'Bulevar Impreso 3'),
('Tool Master LTDA', '90055443', 'Av. Ferretería 4'),
('LED Solutions S.A.', '90011223', 'Calle El Foco 5'),
('CleanFast Distribuidora', '90033221', 'Ruta del Aseo 6'),
('Book Universe', '90077665', 'Plaza del Saber 7'),
('TexCorp Uniformes', '90044556', 'Polígono Textil 8'),
('Food Express EIRL', '90088990', 'Mercado Central 9'),
('SecureNet Inc.', '90010101', 'Sector de Redes 10');

INSERT INTO sucursales(nombre, direccion) VALUES
('Sucursal Este', 'Calle Principal, Edificio Este'),
('Sucursal Oeste', 'Av. Secundaria, Zona Oeste'),
('Almacén Central', 'Zona Franca, Bodega #101'),
('Tienda Express 1', 'Centro Comercial Planta Baja'),
('Tienda Express 2', 'Estación de Autobuses Local #5'),
('Oficina Corporativa', 'Piso 15 Torre Ejecutiva'),
('Mini Sucursal Apopa', 'Carretera Troncal, Km 12'),
('Mini Sucursal Santa Tecla', 'Paseo El Carmen'),
('Punto de Venta Soyapango', 'Centro Urbano'),
('Punto de Venta Lourdes', 'Frente a la Iglesia');

INSERT INTO productos(nombre, descripcion, id_categoria, id_proveedor, precio) VALUES
('Audífonos Inalámbricos', 'Cancelación de ruido, 12h batería', 1, 1, 85.00),
('Cartuchos Negro T125', 'Paquete de 3 cartuchos', 2, 3, 35.00),
('Kit de Destornilladores', 'Juego de 20 puntas de precisión', 3, 4, 50.00),
('Foco LED 10W', 'Luz cálida, E27', 4, 5, 4.50),
('Líquido Desinfectante 1L', 'Elimina 99.9% de bacterias', 5, 6, 8.75),
('Manual de Redes', 'Edición 2024, tapa dura', 6, 7, 45.00),
('Camisa Polo Azul', 'Uniforme talla M', 7, 8, 22.00),
('Barra de Cereal', 'Caja de 24 unidades', 8, 9, 18.00),
('Cámara IP 1080p', 'Vigilancia interior con WiFi', 9, 10, 65.00),
('Cable Ethernet 15m', 'Cable de red Cat 6', 10, 1, 12.50);

INSERT INTO inventario(id_producto, id_sucursal, stock) VALUES
(1, 1, 45),   
(2, 2, 120),  
(3, 3, 30),    
(4, 4, 200),   
(5, 5, 80),   
(6, 6, 15),    
(7, 7, 55),    
(8, 8, 75),    
(9, 9, 40),    
(10, 10, 110);  


INSERT INTO entradas(id_producto, id_sucursal, id_proveedor, cantidad, costo) VALUES
(1, 1, 1, 15, 1150.00),
(2, 2, 2, 20, 235.00),
(3, 3, 3, 10, 320.00),
(4, 4, 4, 50, 140.00),
(5, 5, 1, 30, 78.00),
(6, 6, 3, 60, 31.00),
(7, 7, 4, 10, 48.00),
(8, 8, 5, 100, 3.00),
(9, 9, 6, 40, 7.50),
(10, 10, 7, 5, 40.00);


INSERT INTO salidas(id_producto, id_sucursal, cantidad, destino) VALUES
(1, 1, 5, 'Venta Cliente VIP'),
(2, 2, 8, 'Venta Empresarial'),
(3, 3, 2, 'Devolución Proveedor'),
(4, 4, 15, 'Venta Retail'),
(5, 5, 10, 'Tienda Online'),
(6, 6, 20, 'Venta al Detalle'),
(7, 7, 5, 'Uso Interno de Sucursal'),
(8, 8, 30, 'Venta Rápida'),
(9, 9, 10, 'Merma por Caducidad'),
(10, 10, 1, 'Transferencia Interna');

INSERT INTO usuarios (nombre, usuario, contrasena, rol) VALUES
('Administrador General', 'admin', 'admin', 'admin'),
('Empleado', 'empleado', 'empleado', 'empleado');