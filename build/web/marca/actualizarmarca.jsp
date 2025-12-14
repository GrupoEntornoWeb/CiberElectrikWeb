<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>CiberElectrik | Actualizar Marca</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>Actualización de Marca</h1>
        <form>
            <div class="col-6">
                <label for="txtCod" class="form-label">Código:</label>
                <input type="text" class="form-control" id="txtCod" name="txtCod" readonly>
            </div>
            <div class="col-6">
                <label for="txtNom" class="form-label">Nombre:</label>
                <input type="text" class="form-control" id="txtNom" name="txtNom" required>
            </div>
            <div class="col-6">
                <label for="chkEst" class="form-label">Estado:</label>
                <div class="mb-3 form-check">
                    <input type="checkbox" class="form-check-input" id="chkEst" name="chkEst" value="true">
                    <label class="form-check-label" for="chkEst">Habilitado</label>
                </div>
            </div>
            <button type="submit" class="btn btn-success">Actualizar</button>
            <a href="listarmarca.jsp" class="btn btn-dark">Regresar</a>
        </form>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>