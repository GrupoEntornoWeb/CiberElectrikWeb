<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CiberElectrik | Menú Principal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    </head>
    <body>
        <div class="container">
            <!-- Inicio barra menu -->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">
                        <img src="../img/logo.png" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                        CiberElectrik
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavDropdown">
                        <ul class="navbar-nav me-auto">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="../index.jsp">Login</a>
                            </li>

                            <!-- Mantenimientos Simples -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Mantenimientos Simples
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="../sexo/listarsexo.jsp">Sexo</a></li>
                                    <li><a class="dropdown-item" href="../distrito/listardistrito.jsp">Distrito</a></li>
                                    <li><a class="dropdown-item" href="../tipodocumento/listartipodocumento.jsp">Tipo Documento</a></li>
                                    <li><a class="dropdown-item" href="../rol/listarrol.jsp">Rol</a></li>
                                    <li><a class="dropdown-item" href="../CategoriaServlet">Categoría</a></li>
                                    <li><a class="dropdown-item" href="../marca/listarmarca.jsp">Marca</a></li>
                                </ul>
                            </li>

                            <!-- Mantenimientos Complejos -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Mantenimientos Complejos
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="../cliente/listarcliente.jsp">Cliente</a></li>
                                    <li><a class="dropdown-item" href="../empleado/listarempleado.jsp">Empleado</a></li>
                                    <li><a class="dropdown-item" href="../ProductoServlet">Producto</a></li>
                                </ul>
                            </li>

                            <!-- Operaciones -->
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Operaciones
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="../TicketPedidoServlet">Ticket Pedido</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- fin barra menu -->

            <div class="mt-4"></div>

            <!-- inicio de un card -->
            <div class="card mx-auto mt-5" style="width: 25rem;">
                <img src="../img/logo.png" class="card-img-top p-4" alt="Logo ciber electrik">
                <div class="card-body text-center">
                    <h2 class="card-title">Sistema de Gestión</h2>
                    <p class="card-text">Bienvenido al sistema de gestión de la empresa Ciber Electrik</p>
                    <p class="text-muted">Seleccione una opción del menú superior para comenzar</p>
                </div>
            </div>
            <!-- fin del card -->

            <!-- Información adicional -->
            <div class="row mt-5">
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                </div>
                <div class="col-md-4">
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </body>
</html>