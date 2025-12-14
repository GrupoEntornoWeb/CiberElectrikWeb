<%@page import="java.util.List"%>
<%@page import="pe.com.ciberelectrik.modelo.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CiberElectrik | Listar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>

    <body>
        <!-- capturamos los valores que vienen del Servlet -->
        <%
            //captura los valores que vienen del Servlet y se los paso a la Lista
            List<Producto> productos = (List<Producto>) request.getAttribute("productos");
        %>
        <div class="container">
            <!-- inicio de la tarjeta -->
            <div class="card">
                <div class="card-header">
                    <h1>Listado de Productos</h1>
                </div>
                <div class="card-body">
                    <!-- generamos un div para los botones -->
                    <!-- mb-3 -> m=Marge, b=Bottom(abajo), 3 el nivel de espaciado(0-5) - m-auto -> centrado automatico -->
                    <div class="mb-3">
                        <a href="ProductoServlet?accion=registro" class="btn btn-primary">Registrar Producto</a>
                        <a href="ProductoServlet?accion=habilita" class="btn btn-warning">Habilitar Producto</a>
                        <a href="ProductoServlet?accion=menu" class="btn btn-dark">Regresar</a>
                    </div>
                    <!-- fin del div de los botones -->

                    <!-- inicio de la tabla -->
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-bordered">
                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">Código</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Descripcion</th>
                                    <th scope="col">Precio</th>
                                    <th scope="col">Cantidad</th>
                                    <th scope="col">Fec. Ingreso</th>
                                    <th scope="col">Marca</th>
                                    <th scope="col">Categoria</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Actualizar</th>
                                    <th scope="col">Eliminar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Llenamos las filas con los datos que vienen del Servlet -->
                                <%
                                    for (Producto pro : productos) {
                                %>
                                <tr>
                                    <th scope="row"><%= pro.getCodigo()%></th>
                                    <td><%= pro.getNombre()%></td>
                                    <td><%= pro.getDescripcion()%></td>
                                    <td><%= pro.getPrecio()%></td>
                                    <td><%= pro.getCantidad()%></td>
                                    <td><%= pro.getFechaingreso()%></td>
                                    <td><%= pro.getMarca().getNombre()%></td>
                                    <td><%= pro.getCategoria().getNombre()%></td>
                                    <td><%= pro.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
                                    <td><a href="ProductoServlet?accion=actualiza&id=<%= pro.getCodigo()%>" class="btn btn-success">Seleccionar</a></td>
                                    <td><a href="ProductoServlet?accion=eliminar&id=<%= pro.getCodigo()%>"  class="btn btn-danger">Seleccionar</a></td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                    <!-- fin del tabla -->
                </div>
            </div>
            <!-- fin de la tarjeta -->
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
        crossorigin="anonymous"></script>
    </body>

</html>
