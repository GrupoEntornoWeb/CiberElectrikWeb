<%@page import="pe.com.ciberelectrik.modelo.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="pe.com.ciberelectrik.modelo.Marca"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CiberElectrik | Registrar Producto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <!-- capturamos los valores que vienen del Servlet -->
        <%
            //captura los valores que vienen del Servlet y se los paso a la Lista
            List<Marca> marcas = (List<Marca>) request.getAttribute("marcas");
            List<Categoria> categorias = (List<Categoria>) request.getAttribute("categorias");
        %>
        <div class="container">
            <h1>Registro de Producto</h1>
            <%-- inicio del formulario --%>
            <form method="post" action="ProductoServlet?accion=registrar" accept-charset="UTF-8">
                <%-- inicio de la fila --%>
                <div class="row">
                    <div class="col-4">
                        <label for="txtNom" class="form-label">Nombre:</label>
                        <input type="text" class="form-control" id="txtNom" name="txtNom" required>
                    </div>
                    <div class="col-4">
                        <label for="txtDes" class="form-label">Descripcion:</label>
                        <textarea id="txtDes" name="txtDes" rows="5" cols="10" class="form-control" required></textarea>
                    </div>
                    <div class="col-4">
                        <label for="txtPre" class="form-label">Precio:</label>
                        <!-- step: permite configurar el campo input de tipo number para ello
                        podemos configurarlo asi:
                        step="0.01" -> permite 2 decimal
                        step="0.001" -> permite 3 decimales
                        step="any" -> permite cualquier cantidad de decimaales
                        -->
                        <input type="number" class="form-control" id="txtPre" name="txtPre" step="0.001" min="0" required>
                    </div>
                </div>
                <%-- fin de la fila --%>

                <%-- inicio de la fila --%>
                <div class="row">
                    <div class="col-4">
                        <label for="txtCan" class="form-label">Cantidad:</label>
                        <input type="number" class="form-control" id="txtCan" name="txtCan" required>
                    </div>
                    <div class="col-4">
                        <label for="txtFec" class="form-label">Fecha de Ingreso:</label>
                        <input type="date" class="form-control" id="txtFec" name="txtFec" required>
                    </div>

                    <div class="col-4">
                        <label for="cboMarca" class="form-label">Marca:</label>
                        <select class="form-select" id="cboMarca" name="cboMarca" required>
                            <option value="0">Seleccione una marca</option>
                            <%
                                for (Marca mar : marcas) {
                            %>
                            <option value="<%= mar.getCodigo()%>"><%= mar.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <%-- fin de la fila --%>

                <%-- inicio de la fila --%>
                <div class="row">
                    <div class="col-4">
                        <label for="cboCategoria" class="form-label">Categoria:</label>
                        <select class="form-select" id="cboCategoria" name="cboCategoria" required>
                            <option value="0">Seleccione una categoria</option>
                            <%
                                for (Categoria cat : categorias) {
                            %>
                            <option value="<%= cat.getCodigo()%>"><%= cat.getNombre()%></option>
                            <%}%>
                        </select>
                    </div>

                    <div class="col-4">
                        <label for="chkEst" class="form-label">Estado:</label>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true">
                            <label class="form-check-label" for="chkEst">Habilitado</label>
                        </div>
                    </div>
                </div>
                <%-- fin de la fila --%>
                <div class="mb-3"></div>
                <button type="submit" class="btn btn-primary">Registrar</button>
                <a href="ProductoServlet?accion=regresar" class="btn btn-dark">Regresar</a>
            </form>
            <%-- fin del formulario --%>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>
