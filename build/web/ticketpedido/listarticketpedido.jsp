<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CiberElectrik | Listar Ticket de Pedido</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>

    <body>
        <!-- capturamos los valores que vienen del Servlet -->
        <%
            //captura los valores que vienen del Servlet y se los paso a la Lista
            List<TicketPedido> ticketpedidos = (List<TicketPedido>) request.getAttribute("ticketpedidos");
        %>
        <div class="container">
            <!-- inicio de la tarjeta -->
            <div class="card">
                <div class="card-header">
                    <h1>Listado de Ticket de Pedido</h1>
                </div>
                <div class="card-body">
                    <!-- generamos un div para los botones -->
                    <!-- mb-3 -> m=Marge, b=Bottom(abajo), 3 el nivel de espaciado(0-5) - m-auto -> centrado automatico -->
                    <div class="mb-3">
                        <a href="ProductoServlet?accion=registro" class="btn btn-primary">Registrar Ticket de Pedido</a>
                        <a href="ProductoServlet?accion=menu" class="btn btn-dark">Regresar</a>
                    </div>
                    <!-- fin del div de los botones -->

                    <!-- inicio de la tabla -->
                    <div class="table-responsive">
                        <table class="table table-striped table-hover table-bordered">
                            <thead class="table-dark">
                                <tr>
                                    <th scope="col">Numero</th>
                                    <th scope="col">Fecha</th>
                                    <th scope="col">Cliente</th>
                                    <th scope="col">Empleado</th>
                                    <th scope="col">Subtotal</th>
                                    <th scope="col">Estado</th>
                                    <th scope="col">Anular</th>
                                    <th scope="col">Ver Detalle</th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- Llenamos las filas con los datos que vienen del Servlet -->
                                <%
                                    for (TicketPedido tic : ticketpedidos) {
                                %>
                                <tr>
                                    <th scope="row"><%= tic.getNumero()%></th>
                                    <td><%= tic.getFecha()%></td>
                                    <td><%= tic.getCliente().getNombre()%> 
                                        <%= tic.getCliente().getApellidopaterno()%> 
                                        <%= tic.getCliente().getApellidomaterno()%>
                                    </td>
                                    <td><%= tic.getEmpleado().getNombre()%> 
                                        <%= tic.getEmpleado().getApellidopaterno()%> 
                                        <%= tic.getEmpleado().getApellidomaterno()%></td>
                                    <td>S/. <%= tic.getSubtotal()%></td>                         
                                    <td><%= tic.isEstado() ? "Habilitado" : "Deshabilitado"%></td>
                                    <td><a href="TicketPedidoServlet?accion=anular&id=1" class="btn btn-danger">Seleccionar</a></td>
                                    <td><a href="TicketPedidoServlet?accion=detalles&id=1"  class="btn btn-dark">Seleccionar</a></td>
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

</html>----------------------------
<%@page import="pe.com.ciberelectrik.modelo.TicketPedido"%>