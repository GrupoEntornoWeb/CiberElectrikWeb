<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CiberElectrik | Listar Distrito</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="card-header">
                <h1>Listado de Distritos</h1>
            </div>
            <div class="card-body">
                <div class="mb-3">
                    <a href="registrardistrito.jsp" class="btn btn-primary">Registrar Distrito</a>
                    <a href="habilitardistrito.jsp" class="btn btn-warning">Habilitar Distrito</a>
                    <a href="../menuprincipal/mainmenu.jsp" class="btn btn-dark">Regresar</a>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-hover table-bordered">
                        <thead class="table-dark">
                            <tr>
                                <th scope="col">Código</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Estado</th>
                                <th scope="col">Actualizar</th>
                                <th scope="col">Eliminar</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Lima</td>
                                <td>Activo</td>
                                <td><a href="actualizardistrito.jsp" class="btn btn-success">Seleccionar</a></td>
                                <td><a href="#" class="btn btn-danger">Seleccionar</a></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>