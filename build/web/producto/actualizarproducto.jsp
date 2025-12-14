<%@page import="java.text.SimpleDateFormat"%>
<%@page import="pe.com.ciberelectrik.modelo.Producto"%>
<%@page import="pe.com.ciberelectrik.modelo.Marca"%>
<%@page import="pe.com.ciberelectrik.modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CiberElectrik | Actualizar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <%
            List<Marca> marcas = (List<Marca>) request.getAttribute("marcas");
            List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
            Producto productos = (Producto) request.getAttribute("productos");
        %>
        <div class="container">
            <h1>Actualizacion de Producto</h1>
            <%-- inicio del formulario --%>
            <form method="post" action="ProductoServlet?accion=actualizar" accept-charset="UTF-8">
                <%-- inicio de la fila --%>
                <div class="row">
                    <div class="col-4">
                        <label for="txtCod" class="form-label">Codigo:</label>
                        <input type="text" class="form-control" id="txtCod" name="txtCod" readonly value="<%= productos.getCodigo()%>">
                    </div>
                    <div class="col-4">
                        <label for="txtNom" class="form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtNom" name="txtNom" value="<%= productos.getNombre()%>">
                    </div>
                    <div class="col-4">
                        <label for="txtDes" class="form-label">Descripcion:</label>
                        <textarea id="txtDes" name="txtDes" rows="5" cols="10" class="form-control"><%= productos.getDescripcion()%></textarea>
                    </div>
                </div>
                <%-- fin de la fila --%>

                <%-- inicio de la fila --%>
                <div class="row">
                    <div class="col-4">
                        <label for="txtPre" class="form-label">Precio:</label>
                        <!-- step: permite configurar el campo input de tipo number para ello
                        podemos configurarlo asi:
                        step="0.01" -> permite 2 decimal
                        step="0.001" -> permite 3 decimales
                        step="any" -> permite cualquier cantidad de decimaales
                        -->
                        <input type="number" class="form-control" id="txtPre" name="txtPre" step="0.001" min="0" value="<%= productos.getPrecio()%>">
                    </div>
                    <div class="col-4">
                        <label for="txtCan" class="form-label">Cantidad:</label>
                        <input type="number" class="form-control" id="txtCan" name="txtCan" value="<%= productos.getCantidad()%>">
                    </div>
                    <div class="col-4">
                        <label for="txtFec" class="form-label">Fecha de Ingreso:</label>
                        <%
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaformateada = "";
                            if (productos.getFechaingreso() != null) {
                                fechaformateada = sdf.format(productos.getFechaingreso());
                            }
                        %>
                        <input type="date" class="form-control" id="txtFec" name="txtFec" value="<%= fechaformateada%>">
                    </div>


                </div>
                <%-- fin de la fila --%>

                <%-- inicio de la fila --%>
                <div class="row">
                    <div class="col-4">
                        <label for="cboMarca" class="form-label">Marca:</label>
                        <select class="form-select" id="cboMarca" name="cboMarca" >
                            <option value="0">Seleccione una marca</option>
                            <%
                                for (Marca mar : marcas) {
                                    if (mar.getCodigo() == productos.getMarca().getCodigo()) {
                            %>
                            <option value="<%= mar.getCodigo()%>" selected="true"><%= mar.getNombre()%></option>
                            <%} else {%>
                            <option value="<%= mar.getCodigo()%>"><%= mar.getNombre()%></option>
                            <% }
                                }
                            %>
                        </select>
                    </div>
                    <div class="col-4">
                        <label for="cboCategoria" class="form-label">Categoria:</label>
                        <select class="form-select" id="cboCategoria" name="cboCategoria" >
                            <option value="0">Seleccione una categoria</option>
                            <%
                                for (Categoria cat : categorias) {
                                    if (cat.getCodigo() == productos.getCategoria().getCodigo()) {
                            %>
                            <option value="<%= cat.getCodigo()%>" selected="true"><%= cat.getNombre()%></option>
                            <%} else {%>
                            <option value="<%= cat.getCodigo()%>"><%= cat.getNombre()%></option>
                            <% }
                                }
                            %>
                        </select>
                    </div>

                    <div class="col-4">
                        <label for="chkEst" class="form-label">Estado:</label>
                        <div class="mb-3 form-check">
                            <% if (productos.isEstado()) {%>
                            <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true" checked="true">
                            <%} else {%>
                            <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="false" checked="false">
                            <% }%>
                            <label class="form-check-label" for="chkEst">Habilitado</label>
                        </div>
                    </div>
                </div>
                <%-- fin de la fila --%>
                <div class="mb-3"></div>
                <button type="submit" class="btn btn-success">Actualizar</button>
                <a href="ProductoServlet?accion=regresa" class="btn btn-dark">Regresar</a>
            </form>
            <%-- fin del formulario --%>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>
